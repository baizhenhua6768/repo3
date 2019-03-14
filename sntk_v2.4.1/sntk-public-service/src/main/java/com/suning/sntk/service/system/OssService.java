/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OssService
 * Author:   88396455_白振华
 * Date:     2018-8-16 17:48
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.system;

import java.io.InputStream;

import com.suning.sntk.dto.system.OssFileInfo;

/**
 * 功能描述：图片上传服务
 *
 * @author 88396455_白振华
 * @since 2018-8-16
 */
public interface OssService {

    /**
     * 获取导入文件流
     *
     * @param objectId   OSS文件对象Id
     * @param bucketName 存储区名称
     * @author 88396455_白振华
     * @since 19:04  2018-8-16
     */
    InputStream getFileStream(String bucketName, String objectId);

    /**
     * 上传文件
     *
     * @param filename    文件名称
     * @param contentType 文件媒体类型
     * @param in          输入流
     * @param bucketName  存储区名称
     * @author 88396455_白振华
     * @since 19:10  2018-8-16
     */
    OssFileInfo uploadFile(String bucketName, String filename, String contentType, InputStream in);


    /**
     * 文件分片上传
     *
     * @param bucketName 存储区名称
     * @param filename   文件名
     * @param path       文件路径
     * @author 88396455_白振华
     * @since 19:25  2018-8-28
     */
    OssFileInfo uploadMultiPartFile(String bucketName, String filename, String path);
}
