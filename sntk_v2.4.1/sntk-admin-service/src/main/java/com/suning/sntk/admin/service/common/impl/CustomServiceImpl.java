package com.suning.sntk.admin.service.common.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.sntk.admin.dao.common.CustomDao;
import com.suning.sntk.admin.service.common.CustomService;
import com.suning.sntk.vo.QueryResult;

@Service("customService")
public class CustomServiceImpl implements CustomService {

	@Autowired
	CustomDao customDao;

	@Override
	public List<String> queryTables() {
		return customDao.queryTables();
	}

	@Override
	public List<String> showColumnByTbName(String tbName) {
		return customDao.showColumnByTbName(tbName);
	}

	@Override
	public QueryResult<Map<String, Object>> queryDataByTableName(Map<String, Object> params) {
		return customDao.queryDataByTableName(params);
	}

	@Override
	public boolean update(Map<String, String> params) {
		return customDao.update(params);
	}

	@Override
	public boolean delete(Map<String, String> params) {
		return customDao.delete(params);
	}

}
