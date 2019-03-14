/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoVideo
 * Author:   18010645_黄成
 * Date:     2018/9/12 18:59
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述：v购视频
 *
 * @author 18010645_黄成
 * @since 2018/9/12
 */
public class VgoVideoDto implements Serializable {
    private static final long serialVersionUID = 8319204805877054645L;

    //视频id
    private Long id;
    //视频封面URL（尺寸16:9）
    private String imageUrl;
    //视频名称
    private String title;
    //视频链接
    private String videoUrl;
    //单个视频点赞数
    private Integer likeCnt;
    //单个视频评论数
    private Integer commentCnt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(Integer likeCnt) {
        this.likeCnt = likeCnt;
    }

    public Integer getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(Integer commentCnt) {
        this.commentCnt = commentCnt;
    }

    @Override
    public String toString() {
        return "VgoVideoDto{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", likeCnt=" + likeCnt +
                ", commentCnt=" + commentCnt +
                '}';
    }
}
