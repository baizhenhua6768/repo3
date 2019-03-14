package com.suning.sntk.admin.dao.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suning.framework.dal.client.DalClient;
import com.suning.sntk.admin.dao.common.CustomDao;
import com.suning.sntk.vo.QueryResult;

@Repository
public class CustomDaoImpl implements CustomDao {

	@Autowired
	protected DalClient dalClient;

	@Override
	public List<String> queryTables() {
		List<String> tables = new ArrayList<>();
		Map<String, String> params = new HashMap<>();
		List<Map<String, Object>> list = dalClient.queryForList("custom.queryTables", params);
		for (Map<String, Object> map : list) {
			String tableName = (String) map.entrySet().iterator().next().getValue();
			tables.add(tableName);
		}
		return tables;
	}

	@Override
	public List<String> showColumnByTbName(String tbName) {
		List<String> columns = new ArrayList<>();
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tbName);
		List<Map<String, Object>> result = dalClient.queryForList("custom.showColumnByTbName", params);
		for (Map<String, Object> map : result) {
			String field = (String) map.get("Field");
			columns.add(field);
		}
		return columns;
	}

	@Override
	public QueryResult<Map<String, Object>> queryDataByTableName(Map<String, Object> params) {
		int pageSize = 10;
		int pageNo = 1 + (Integer.parseInt(String.valueOf(params.get("start"))) / pageSize);

		QueryResult<Map<String, Object>> queryResult = new QueryResult<Map<String, Object>>();

		Number number = dalClient.queryForObject("custom.countDataByTableName", params, Number.class);
		long total = number.longValue();
		if (total == 0) {
			queryResult.setResultObject(new ArrayList<Map<String, Object>>());
			return queryResult;
		}
		queryResult.setTotalCount(total);
		// 设置总页数
		int maxPage = (int) (total + pageSize - 1) / pageSize;
		queryResult.setTotalPageCount(maxPage);
		// 设置当前页
		queryResult.setCurrentPage(pageNo);
		int start = 0;
		if (pageNo > maxPage) {
			pageNo = maxPage;
		}
		if (pageNo > 0) {
			start = (pageNo - 1) * pageSize;
		}
		params.put("start", start);
		params.put("end", pageSize);

		List<Map<String, Object>> list = dalClient.queryForList("custom.queryDataByTableName", params);
		queryResult.setResultObject(list);
		return queryResult;
	}

	@Override
	public boolean update(Map<String, String> params) {
		return dalClient.execute("custom.update", params) > 0;
	}

	@Override
	public boolean delete(Map<String, String> params) {
		return dalClient.execute("custom.delete", params) > 0;
	}

}
