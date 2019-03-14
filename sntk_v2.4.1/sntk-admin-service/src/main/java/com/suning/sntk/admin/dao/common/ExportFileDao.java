/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ExportFileDao
 * Author:   88396455_白振华
 * Date:     2018-8-24 17:39
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.dao.common;

import com.suning.sntk.entity.ExportFile;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：文件导出数据库操作类
 *
 * @author 88396455_白振华
 * @since 2018-8-24
 */
@DalMapper("export_file")
public interface ExportFileDao extends DalBaseDao<ExportFile, Long> {

}
