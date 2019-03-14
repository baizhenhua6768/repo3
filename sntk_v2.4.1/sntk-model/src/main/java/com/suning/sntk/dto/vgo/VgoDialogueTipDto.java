package com.suning.sntk.dto.vgo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 功能描述：云信对话模板响应
 *
 * @author 17061157_王薛
 * @since 2018-10-11
 */
public class VgoDialogueTipDto implements Serializable {

    private static final long serialVersionUID = 7893452026078305331L;

    // 标志
    private String flag;
    // 提示语
    private String template;
    // 店+提示语
    private String sawpTemplate;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSawpTemplate() {
        return sawpTemplate;
    }

    public void setSawpTemplate(String sawpTemplate) {
        this.sawpTemplate = sawpTemplate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("flag", flag)
                .append("template", template)
                .append("sawpTemplate", sawpTemplate)
                .toString();
    }
}
