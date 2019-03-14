/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ExportFileService
 * Author:   88396455_白振华
 * Date:     2018-8-23 15:41
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.common;

import java.io.IOException;

import com.suning.sntk.entity.ExportFile;

/**
 * 功能描述：导出文件服务
 *
 * @author 88396455_白振华
 * @since 2018-8-23
 */
public interface ExportFileService {

    /**
     * 导出文件
     *
     * @param custNo   客户编码
     * @param provider 内容提供者
     * @throws IOException
     * @author 88396455_白振华
     * @since 16:52  2018-8-24
     */
    <T> ExportFile exportFile(String custNo, ExportProvider<T> provider) throws IOException;

}
