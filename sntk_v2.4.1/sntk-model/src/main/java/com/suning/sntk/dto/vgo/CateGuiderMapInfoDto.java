/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CateGuiderMapInfoDto
 * Author:   18041004_余长杰
 * Date:     2018/8/17 11:27
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：品类-导购列表
 *
 * @author 18041004_余长杰
 * @since 2018/8/17
 */
public class CateGuiderMapInfoDto implements Serializable{

    private static final long serialVersionUID = -3098785405839720369L;

    /**
     * map<品类-导购列表>
     */
    private Map<String, List<CategoryGuideInfoDto>> cateGuideMap;

    public Map<String, List<CategoryGuideInfoDto>> getCateGuideMap() {
        return cateGuideMap;
    }

    public void setCateGuideMap(Map<String, List<CategoryGuideInfoDto>> cateGuideMap) {
        this.cateGuideMap = cateGuideMap;
    }
}
