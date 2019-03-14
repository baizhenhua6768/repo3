/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OssServiceImpl
 * Author:   88396455_白振华
 * Date:     2018-8-16 17:49
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.system.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.suning.sntk.dto.system.OssFileInfo;
import com.suning.sntk.service.system.OssService;
import com.suning.sntk.support.enums.SysErrorEnum;
import com.suning.store.commons.lang.validator.Validators;
import net.oss.client.OSSClient;
import net.oss.result.SdossResult;

/**
 * 功能描述：文件上传服务
 *
 * @author 88396455_白振华
 * @since 2018-8-16
 */
@Service
public class OssServiceImpl implements OssService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OssServiceImpl.class);

    private static final Map<String, String> EMPTY_HEADER_ARGS = Collections.emptyMap();

    @Value("${account_name}")
    private String accountName;

    @Value("${down_address}")
    private String downloadUrlBase;

    @Autowired
    private OSSClient client;

    @Override
    public InputStream getFileStream(String bucketName, String objectId) {
        return client.downloadObject(bucketName, objectId, EMPTY_HEADER_ARGS);
    }

    @Override
    public OssFileInfo uploadFile(String bucketName, String filename, String contentType, InputStream in) {
        LOGGER.info("上传文件：filename = {},contentType = {}", filename, contentType);
        //1.生成签名
        SdossResult signResult = sign(bucketName, filename);
        //2.上传文件，objectName必须和生成签名时一致
        SdossResult uploadResult = doUpload(bucketName, signResult, filename, contentType, in);
        //3.生成导入文件实体
        String downloadUrl = getDownloadUrl(bucketName, uploadResult);
        OssFileInfo info = new OssFileInfo();
        info.setObjectId(uploadResult.getObjectId());
        info.setDownloadUrl(downloadUrl);
        return info;
    }

    private String getDownloadUrl(String bucketName, SdossResult uploadResult) {
        return String.format("%s/%s/%s/%s", downloadUrlBase, accountName, bucketName, uploadResult.getObjectId());
    }

    private SdossResult doUpload(String bucketName, SdossResult signResult, String filename, String contentType, InputStream in) {
        String url = getUploadUrl(bucketName, signResult, filename);

        String authorization = signResult.getAuthorization();
        String current_time = signResult.getCurrent_time();
        SdossResult result = client.putObject(in, filename, contentType, EMPTY_HEADER_ARGS, authorization, current_time, url);
        Validators.ifInValid(!result.isSuccess()).thenThrow(SysErrorEnum.OSS_UPLOAD_FAIL);
        return result;
    }

    private String getUploadUrl(String bucketName, SdossResult signResult, String filename) {
        return String.format("%s/%s/%s/%s", signResult.getFileraddr(), signResult.getAccount_id(), bucketName, filename);
    }

    private SdossResult sign(String bucketName, String filename) {
        SdossResult sdossResult = client.putObject(bucketName, filename, EMPTY_HEADER_ARGS);
        Validators.ifInValid(!sdossResult.isSuccess()).thenThrow(SysErrorEnum.OSS_SIGN_FAIL);
        return sdossResult;
    }

    @Override
    public OssFileInfo uploadMultiPartFile(String bucketName, String filename, String path) {
        Map<String, String> headerArgument = Collections.emptyMap();
        File file = new File(path);
        SdossResult r = client.MutiPartUpload(bucketName, filename, file, headerArgument);
        OssFileInfo info = new OssFileInfo();
        info.setDownloadUrl(getDownloadUrl(bucketName, r));
        info.setObjectId(r.getObjectId());
        return info;
    }

}
