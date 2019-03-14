/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SynMossPicToSntkJob
 * Author:   88396455_白振华
 * Date:     2018-9-13 10:45
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.job.vgo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.suning.sntk.admin.dao.vgo.GuideInfoAdminDao;
import com.suning.sntk.dao.vgo.O2oGuideInfoDao;
import com.suning.sntk.dto.system.OssFileInfo;
import com.suning.sntk.entity.vgo.GuideInfo;
import com.suning.sntk.entity.vgo.O2oGuideInfo;
import com.suning.sntk.service.system.OssService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.exception.vgo.SntkRedisJobException;
import com.suning.sntk.support.util.CommonUtils;

/**
 * 功能描述：同步moss的图片至sntk
 *
 * @author 88396455_白振华
 * @since 2018-9-13
 */
@Component
public class SynMossPicToSntkJob {

    public static final String APPLICATION_X_PNG = "application/x-png";
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SynMossPicToSntkJob.class);
    @Autowired
    private OssService ossService;

    @Autowired
    private GuideInfoAdminDao guideInfoAdminDao;

    @Autowired
    private O2oGuideInfoDao o2oGuideInfoDao;

    /**
     * http协议头
     */
    private static final String HTTP_PROTOCOL = "http";

    /**
     * https协议头
     */
    private static final String HTTPS_PROTOCOL = "https";

    /**
     * 同步moss的图片至sntk
     *
     * @author 88396455_白振华
     * @since 10:48  2018-9-13
     */
    public void synPicFromMossTosntk() {
        LOGGER.info("开始执行同步moss的图片至sntk的任务");
        List<GuideInfo> toBeSynPicList = guideInfoAdminDao.obtainToBeSynPicUrl();
        if (CollectionUtils.isEmpty(toBeSynPicList)) {
            return;
        }
        //1.将moss系统的图片上传值sntk的oss图片存储区
        List<GuideInfo> successList = picUpload(toBeSynPicList);
        //2.更新后台导购管理表和中台导购信息表
        updateGuideInfo(successList);
        LOGGER.info("执行同步moss的图片至sntk的任务结束");
    }

    /**
     * 更新后台导购管理表和中台导购信息表
     */
    @Transactional
    public void updateGuideInfo(List<GuideInfo> successList) {
        if (CollectionUtils.isEmpty(successList)) {
            return;
        }
        Map<String, GuideInfo> temp = new HashMap<String, GuideInfo>();
        List<String> guideIds = new ArrayList<String>();
        for (GuideInfo guideInfo : successList) {
            String guideId = guideInfo.getGuideId();
            guideIds.add(guideId);
            temp.put(guideId, guideInfo);
        }
        List<O2oGuideInfo> o2oGuideInfos = o2oGuideInfoDao.queryGuideInfoList(guideIds);
        for (O2oGuideInfo o2oGuideInfo : o2oGuideInfos) {
            GuideInfo guideInfo = temp.get(o2oGuideInfo.getGuideId());
            o2oGuideInfo.setGuidePhoto(guideInfo.getGuidePhoto());
            o2oGuideInfo.setUpdatePerson(CommonConstants.SYS);
            o2oGuideInfo.setUpdateTime(CommonUtils.obtainCurrentTimestamp());
        }
        o2oGuideInfoDao.batchUpdateSkipNull(o2oGuideInfos);
        guideInfoAdminDao.batchUpdateSkipNull(successList);
    }

    /**
     * 将moss系统的图片上传至sntk的oss图片存储区
     */
    private List<GuideInfo> picUpload(List<GuideInfo> toBeSynPicList) {
        List<GuideInfo> successList = new ArrayList<GuideInfo>();
        for (GuideInfo guideInfo : toBeSynPicList) {
            ByteArrayInputStream bin = null;
            try (InputStream in = new URL(guideInfo.getGuidePhoto()).openConnection().getInputStream();
                 ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ) {
                byte[] buffer = new byte[1024];
                int len = -1;
                while (-1 != (len = in.read(buffer))) {
                    bout.write(buffer, 0, len);
                }
                bout.flush();
                bin = new ByteArrayInputStream(bout.toByteArray());
                OssFileInfo info = ossService
                        .uploadFile(CommonConstants.PIC_BUCKET_NAME, System.currentTimeMillis() + VgoConstants.IMG_TYPE_PNG,
                                APPLICATION_X_PNG, bin);
                if (null != info && StringUtils.isNotBlank(info.getDownloadUrl())) {
                    GuideInfo guide = new GuideInfo();
                    guide.setId(guideInfo.getId());
                    guide.setGuideId(guideInfo.getGuideId());
                    //将http头改成https,便于主站用https访问时正常展示
                    String pic = info.getDownloadUrl().replaceFirst(HTTP_PROTOCOL, HTTPS_PROTOCOL);
                    guide.setGuidePhoto(pic);
                    guide.setIsCompeletePicChange(VgoConstants.COMPLETE_PIC_CHANGE);
                    guide.setUpdatePerson(CommonConstants.SYS);
                    guide.setUpdateTime(CommonUtils.obtainCurrentTimestamp());
                    successList.add(guide);
                }
                bin.close();
            } catch (IOException e) {
                LOGGER.error("SynMossPicToSntkJob.synPicFromMossTosntk guideId:{}, error:{}", guideInfo.getGuideId(), e);
                throw new SntkRedisJobException(e);
            } catch (Exception e) {
                LOGGER.error("SynMossPicToSntkJob.synPicFromMossTosntk guideId:{}, error:{}", guideInfo.getGuideId(), e);
                throw new SntkRedisJobException(e);
            } finally {
                if (null != bin) {
                    try {
                        bin.close();
                    } catch (IOException e) {
                        LOGGER.error("SynMossPicToSntkJob.synPicFromMossTosntk bin.close error:{}", e);
                    }
                }
            }
        }
        return successList;
    }
}
