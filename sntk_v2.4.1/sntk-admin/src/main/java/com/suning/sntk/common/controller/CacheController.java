package com.suning.sntk.common.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.suning.framework.web.gson.GsonView;
import com.suning.sntk.admin.service.common.CustomService;
import com.suning.sntk.admin.vo.DataResponse;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.exception.SntkServiceException;
import com.suning.sntk.support.exception.enums.CommonErrorEnum;
import com.suning.sntk.vo.QueryResult;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 〈缓存清除〉<br>
 *
 * @author 10070706
 */
@Controller
@RequestMapping(value = "/cache")
public class CacheController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);

    private static final String STRING = "string";
    private static final String LIST = "list";
    private static final String SET = "set";
    private static final String HASH = "hash";
    private static final String ZSET = "zset";

    private final static Set<String> adminIdSet = Sets.newHashSet("14080292", "12061818", "17061157");

    @Autowired
    RedisCacheUtils redisCacheUtils;

    @Autowired
    private CustomService customService;

    /**
     * 功能描述: <br>
     * 〈http://localhost:8080/pbs-web/cache/clearCacheByKeyAndField.do?key=&
     * field=?〉
     *
     * @param key 键
     * @param field 属性
     * @return
     */
    @RequestMapping("/clearCacheByKeyAndField")
    public GsonView clearCacheByKeyAndField(@RequestParam("key") String key, @RequestParam(name = "field", required = false) String field) {
        GsonView view = new GsonView();
        try {
            if (StringUtils.isNotBlank(field)) {
                redisCacheUtils.hdel(key, field);
                LOGGER.info("delete key {} field{} success", new Object[] { key, field });
                view.addStaticAttribute("clearCacheByKeyAndField", true);
            } else {
                redisCacheUtils.del(key);
                LOGGER.info("delete key {} success", new Object[] { key });
                view.addStaticAttribute("clearCacheByKey", true);
            }
        } catch (Exception e) {
            view.addStaticAttribute("clearCacheByKeyAndField", false);
            LOGGER.error("clearCacheByKeyAndField error", e);
        }
        return view;
    }

    /**
     * 功能描述: <br>
     * 〈http://localhost:8080/pbs-web/cache/clearCacheByKeyAndField.do?key=&
     * field=?〉
     *
     * @param key 键
     * @param field 属性
     * @return
     */
    @RequestMapping("/queryCacheByKeyAndField")
    public GsonView queryCacheByKeyAndField(@RequestParam("key") String key, @RequestParam(name = "field", required = false) String field) {
        GsonView view = new GsonView();
        String value = null;
        try {
            if (StringUtils.isNotBlank(field)) {
                value = redisCacheUtils.hget(key, field);
                view.addStaticAttribute("queryCacheByKeyAndField", value);
            } else {
                value = redisCacheUtils.get(key);
                view.addStaticAttribute("queryCacheByKey", value);
            }
        } catch (Exception e) {
            view.addStaticAttribute("queryCacheByKeyAndField", false);
            LOGGER.error("queryCacheByKeyAndField error", e);
        }
        return view;
    }

    @RequestMapping("/queryListCacheByKey")
    public GsonView queryListCacheByKey(@RequestParam("key") String key) {
        GsonView view = new GsonView();
        try {
            List<String> value = redisCacheUtils.lrange(key);
            view.addStaticAttribute("queryCacheByKeyAndField", value);
        } catch (Exception e) {
            view.addStaticAttribute("queryCacheByKeyAndField", false);
            LOGGER.error("queryCacheByKeyAndField error", e);
        }
        return view;
    }

    @RequestMapping(value = "/queryCacheDetailByKey", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String queryCacheDetailByKey(String key) {
        Map<String, Object> view = new HashMap<>();
        boolean exists = redisCacheUtils.exists(key);
        if (!exists) {
            view.put("error", "[key:" + key + "]不存在!");
            return JsonUtils.object2Json(view);
        }

        String type = redisCacheUtils.type(key);

        String leftTime = String.valueOf(this.redisCacheUtils.ttl(key));
        if (STRING.equals(type)) {
            CacheDmo cache = new CacheDmo(key, null, redisCacheUtils.get(key), type, leftTime, null);
            view.put("cache", cache);
        } else if (LIST.equals(type)) {
            view.put("value", redisCacheUtils.lrange(key));
        } else if (SET.equals(type)) {
            view.put("value", redisCacheUtils.smembers(key));
        } else if (ZSET.equals(type)) {
            view.put("value", redisCacheUtils.zrange(key));
        } else if (HASH.equals(type)) {
            List<CacheDmo> cacheList = new ArrayList<CacheDmo>();
            Set<String> fields = redisCacheUtils.hkeys(key);
            for (String field : fields) {
                CacheDmo cache = new CacheDmo(key, field, redisCacheUtils.hget(key, field), type, leftTime, null);
                cacheList.add(cache);
            }
            view.put("cacheList", cacheList);
        }
        view.put("key", key);
        view.put("type", type);
        view.put("leftTime", leftTime);

        return JsonUtils.object2Json(view);
    }

    @RequestMapping("/clearCacheByKey")
    @ResponseBody
    public JSONObject clearCacheByKey(String key) {
        long result = redisCacheUtils.del(key);
        JSONObject json = new JSONObject();
        json.put("result", result);
        return json;
    }

    @RequestMapping("/clearHashByKeyAndField")
    @ResponseBody
    public JSONObject clearHashByKeyAndField(String key, String field) {
        long result = redisCacheUtils.hdel(key, field);
        JSONObject json = new JSONObject();
        json.put("result", result);
        return json;
    }

    @RequestMapping("/showTablesBySuperAdmin")
    @ResponseBody
    public JSONObject showTablesPage(@RequestParam(value = "selectIndex", required = false, defaultValue = "0") int selectIndex,
            HttpServletRequest request) {
        String user = request.getRemoteUser();
        //		Validators.ifInValid(!("14080292".equals(user) || "15050536".equals(user))).thenThrow(CommonErrorEnum.SYS_ERROR);
        Validators.ifInValid(!adminIdSet.contains(user)).thenThrow(CommonErrorEnum.SYS_ERROR);

        JSONObject json = new JSONObject();
        List<String> list = customService.queryTables();
        String tbName = list.get(selectIndex);
        Map<String, Object> parmas = new HashMap<>();
        parmas.put("tableName", tbName);
        parmas.put("start", 1);

        List<String> columnList = customService.showColumnByTbName(tbName);

        json.put("columns", columnList);
        json.put("selectIndex", selectIndex);
        json.put("tables", list);
        return json;
    }

    /**
     * 是否有非法字符
     */
    private boolean hasInvalidWord(Object field) {
        if (field == null) {
            return false;
        }
        String f = String.valueOf(field).toLowerCase();
        return ifInValidField(f);
    }

    private boolean ifInValidField(String f) {
        List<String> list = Lists.newArrayList("delete ", "update ", "drop ", "truncate ", "alter ", "select ", "or ", ";");
        for (String invalidStr : list) {
            LOGGER.warn("queryDataByName 传入非法字符：{}", f);
            return f.indexOf(invalidStr) != -1;
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryDataByName")
    @ResponseBody
    public DataResponse queryDataByName(DataResponse response, @RequestParam Map<String, Object> params, HttpServletRequest request) {
        String user = request.getRemoteUser();
        if (!("14080292".equals(user) || "15050536".equals(user))) {
            return response;
        }
        Object field = params.get("field");
        Object tableName = params.get("tableName");
        if (tableName == null || hasInvalidWord(field) || hasInvalidWord(tableName)) {
            response.setRecordsTotal(0l);
            response.setRecordsFiltered(0l);
            response.setData(new ArrayList());
            return response;
        }

        QueryResult<Map<String, Object>> result = customService.queryDataByTableName(params);
        response.setData(result.getResultObject());
        response.setRecordsTotal(result.getTotalCount());
        return response;
    }

    @RequestMapping(value = "/updataByPk")
    @ResponseBody
    public boolean updataByPk(@RequestParam Map<String, String> params, HttpServletRequest request) {
        String user = request.getRemoteUser();
        Validators.ifInValid(!("14080292".equals(user) || "15050536".equals(user))).thenThrow(CommonErrorEnum.SYS_ERROR);

        String paramsString = params.toString();
        // 含有非法字符
        if (paramsString.indexOf(" or ") > -1 || paramsString.indexOf(" and ") > -1 || paramsString.indexOf(" delete ") > -1) {
            throw new SntkServiceException(CommonErrorEnum.SYS_ERROR);
        }

        boolean result = customService.update(params);
        if (result) {
            return result;
        }
        LOGGER.debug("修改数据失败:{}", params);
        throw new SntkServiceException(CommonErrorEnum.SYS_ERROR);
    }

    @RequestMapping(value = "/delByPk")
    @ResponseBody
    public boolean delByPk(@RequestParam Map<String, String> params, HttpServletRequest request) {
        String user = request.getRemoteUser();
        Validators.ifInValid(!("14080292".equals(user) || "15050536".equals(user))).thenThrow(CommonErrorEnum.SYS_ERROR);

        String paramsString = params.toString();
        // 含有非法字符
        if (paramsString.indexOf(" or ") > -1 || paramsString.indexOf(" and ") > -1 || paramsString.indexOf(" delete ") > -1) {
            throw new SntkServiceException(CommonErrorEnum.SYS_ERROR);
        }

        boolean result = customService.delete(params);
        if (result) {
            return result;
        }
        LOGGER.debug("删除数据失败:{}", params);
        throw new SntkServiceException(CommonErrorEnum.SYS_ERROR);
    }

}

class CacheDmo implements Serializable {

    /**
     */
    private static final long serialVersionUID = -3299240916127230965L;
    /**
     * redis key
     */
    private String key;
    /**
     * redis field
     */
    private String field;
    /**
     * redis value
     */
    private String value;

    /**
     * redis type
     */
    private String type;

    /**
     * 剩余过期时间
     */
    private String leftTime;

    /**
     * redis 描述
     */
    private String desc;

    /**
     * 默认构造器
     */
    public CacheDmo() {
        // 写点注释，省的sonar犯二
    }

    /**
     * 全属性构造器
     *
     * @param key      key
     * @param field    field
     * @param value    value
     * @param type     type
     * @param leftTime 剩余时间
     * @param desc     desc
     */
    public CacheDmo(String key, String field, String value, String type, String leftTime, String desc) {
        super();
        this.key = key;
        this.field = field;
        this.value = value;
        this.type = type;
        this.leftTime = leftTime;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
