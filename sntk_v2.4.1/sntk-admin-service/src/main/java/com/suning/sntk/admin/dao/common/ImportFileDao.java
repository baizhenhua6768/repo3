/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ImportFileDao
 * Author:   88397670_张辉
 * Date:     2018-7-6 15:25
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.dao.common;

import java.util.List;

import com.suning.sntk.entity.ImportFile;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-7-6
 */
@DalMapper(namespace = "uploadFile")
public interface ImportFileDao extends DalBaseDao<ImportFile,Long> {

    List<ImportFile> queryProcessedFile();
}
