/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ExportProvider
 * Author:   88396455_白振华
 * Date:     2018-8-24 15:16
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.common;

import com.suning.sntk.enums.FileType;

/**
 * 功能描述：导出数据提供器
 *
 * @author 88396455_白振华
 * @since 2018-8-24
 */
public interface ExportProvider<T> {

    /**
     * 需要导出的数据总量
     *
     * @author 88396455_白振华
     * @since 15:16  2018-8-24
     */
    long count();

    /**
     * 获取数据类型
     *
     * @author 88396455_白振华
     * @since 15:28  2018-8-24
     */
    FileType getFileType();

    /**
     * 内存中存的最大行数
     *
     * @author 88396455_白振华
     * @since 15:31  2018-8-24
     */
    int getRowAccessWindowSize();

    /**
     * 列头数组
     *
     * @author 88396455_白振华
     * @since 16:43  2018-8-24
     */
    String[] getColumnHeaders();

    /**
     * 是否还有数据
     *
     * @author 88396455_白振华
     * @since 16:43  2018-8-24
     */
    boolean hasNext();

    /**
     * 下个数据
     *
     * @author 88396455_白振华
     * @since 16:43  2018-8-24
     */
    T next();

    /**
     * 获取单元格值
     *
     * @param data   行数据
     * @param row    行号， 从0开始，不包含列头编号
     * @param column 列号
     * @author 88396455_白振华
     * @since 16:43  2018-8-24
     */
    Object getCellValue(T data, int row, int column);

    /**
     * 是否是异步导出，默认是异步 异步返回true
     *
     * @author 88396455_白振华
     * @since 16:49  2018-8-24
     */
    boolean isAsync();
}
