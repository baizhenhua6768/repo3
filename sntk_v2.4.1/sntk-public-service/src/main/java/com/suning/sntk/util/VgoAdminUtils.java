/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoAdminUtils
 * Author:   88396455_白振华
 * Date:     2018-9-6 9:58
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;

/**
 * 功能描述：服务项目处理类
 *
 * @author 88396455_白振华
 * @since 2018-9-6
 */
public class VgoAdminUtils {

    private VgoAdminUtils() {

    }

    /**
     * 截掉图片url
     * 原moss系统服务项目中存的名称，拼接规则是逗号分割图片Url，名称，sntk数据全量迁移，只取名称
     *
     * @param serverItems 服务项目
     * @author 88396455_白振华
     * @since 9:59  2018-9-6
     */
    public static List<String> cutOffPicUrl(List<String> serverItems) {
        List<String> serverItemList = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(serverItems)) {
            for (String serverItem : serverItems) {
                String[] serverItemArr = serverItem.split(",");
                if (serverItemArr.length == 2) {
                    serverItemList.add(serverItemArr[1]);
                } else {
                    serverItemList.add(serverItemArr[0]);
                }
            }
        }
        return serverItemList;
    }

    /**
     * 拼接擅长品类名称
     *
     * @param vcategoryRelInfoDtos 擅长品类信息
     * @author 88396455_白振华
     * @since 16:06  2018-9-8
     */
    public static String obtainCategoryNames(List<VcategoryRelInfoDto> vcategoryRelInfoDtos) {
        StringBuilder buffer = new StringBuilder();
        if (CollectionUtils.isNotEmpty(vcategoryRelInfoDtos)) {
            for (VcategoryRelInfoDto relInfoDto : vcategoryRelInfoDtos) {
                buffer.append(relInfoDto.getCategoryName()).append("、");
            }
        }
        String result = buffer.toString();
        return StringUtils.isNotBlank(result) ? result.substring(0, result.length() - 1) : StringUtils.EMPTY;
    }

    /**
     * 拼接服务项目名称
     *
     * @param serverItems 服务项目信息
     * @author 88396455_白振华
     * @since 16:06  2018-9-8
     */
    public static String obtainServerItemNames(List<String> serverItems) {
        StringBuilder buffer = new StringBuilder();
        if (CollectionUtils.isNotEmpty(serverItems)) {
            for (String serverItemName : serverItems) {
                buffer.append(serverItemName).append("、");
            }
        }
        String result = buffer.toString();

        return StringUtils.isNotBlank(result) ? result.substring(0, result.length() - 1) : StringUtils.EMPTY;
    }

}
