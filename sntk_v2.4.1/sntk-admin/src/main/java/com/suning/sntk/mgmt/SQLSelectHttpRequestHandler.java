package com.suning.sntk.mgmt;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLNumberExpr;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock.Limit;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.util.JdbcUtils;
import com.alibaba.druid.util.Utils;
import com.alibaba.druid.wall.Violation;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallVisitor;
import com.alibaba.druid.wall.spi.MySqlWallProvider;
import com.alibaba.druid.wall.violation.SyntaxErrorViolation;

/**
 * SQL 语句执行网关,校验通过的SQL才能够执行
 */
public class SQLSelectHttpRequestHandler implements Controller, InitializingBean {

    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(SQLSelectHttpRequestHandler.class);
    private DataSource dataSource;

    private Properties defaultProps;

    private String configPath;

    private MySqlWallProvider sqlWallProvider;

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public void setDefaultProps(Properties defaultProps) {
        this.defaultProps = defaultProps;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * SQL 语句执行网关
     * 
     */
    private SQLStatement filter(String sql, List<Violation> violations) {
        try {
            MySqlStatementParser parser = new MySqlStatementParser(sql);
            List<SQLStatement> stmtList = parser.parseStatementList();
            if (stmtList.size() != 1) {
                throw new IllegalArgumentException("not support multi-statment");
            }
            SQLStatement stmt = stmtList.get(0);

            WallVisitor wallVisitor = sqlWallProvider.createWallVisitor();
            stmt.accept(wallVisitor);
            if (!wallVisitor.getViolations().isEmpty()) {
                violations.addAll(wallVisitor.getViolations());
                return null;
            } else {
                return stmt;
            }
        } catch (ParserException e) {
            LOGGER.error("filter createWallVisitor error", e);
            violations.add(new SyntaxErrorViolation(e, sql));
        }
        return null;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        modelAndView.setView(mappingJackson2JsonView);
        try {
            MediaType mediaType = MediaType.parseMediaType(request.getContentType());
            if (MediaType.parseMediaType("application/sql").includes(mediaType)) {
                Reader reader = new InputStreamReader(request.getInputStream(), mediaType.getCharSet());
                String input = Utils.read(reader);
                JdbcUtils.close(reader);
                List<Violation> violations = new ArrayList<Violation>();
                SQLStatement stmt = filter(input, violations);
                if (!violations.isEmpty()) {
                    modelAndView.addObject("error", violations);
                    return modelAndView;
                }
                StringBuilder out = new StringBuilder();
                MySqlOutputVisitor visitor = new MySqlOutputVisitor(out) {
                    @Override
                    public boolean visit(MySqlSelectQueryBlock queryBlock) {
                        Limit limit = queryBlock.getLimit();
                        if (null == limit) {
                            // 默认配置值
                            limit = new Limit();
                            int offset = 0;
                            int rowCount = 200;
                            if (offset > 0) {
                                limit.setOffset(new SQLNumberExpr(offset));
                            }
                            limit.setRowCount(new SQLNumberExpr(rowCount));
                            queryBlock.setLimit(limit);
                        }
                        return super.visit(queryBlock);
                    }
                };
                if (null != stmt) {
                    stmt.accept(visitor);
                }
                if (stmt instanceof SQLSelectStatement) {
                    modelAndView.addObject("data", jdbcTemplate.queryForList(out.toString()));
                } else if (stmt instanceof SQLDeleteStatement || stmt instanceof SQLUpdateStatement) {
                    modelAndView.addObject("affected", jdbcTemplate.update(out.toString()));
                } else {
                    jdbcTemplate.execute(out.toString());
                }
            } else {
                throw new HttpMediaTypeNotSupportedException(mediaType + "not supported");
            }

        } catch (Exception e) {
            modelAndView.addObject("exception", e.getClass().getSimpleName() + ":" + e.getMessage());
            LOGGER.error("error.", e);
        }
        return modelAndView;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        WallConfig config = new WallConfig();
        config.loadConfig(configPath);
        config.configFromProperties(defaultProps);
        sqlWallProvider = new MySqlWallProvider(config);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

}
