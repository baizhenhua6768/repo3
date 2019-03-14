package com.suning.sntk.admin.service.common;

import java.util.List;
import java.util.Map;

import com.suning.sntk.vo.QueryResult;

public interface CustomService {

	public List<String> queryTables();

	public List<String> showColumnByTbName(String tbName);

	public QueryResult<Map<String, Object>> queryDataByTableName(Map<String, Object> params);

	public boolean update(Map<String, String> params);

	public boolean delete(Map<String, String> params);

}
