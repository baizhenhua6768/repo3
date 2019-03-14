package com.suning.sntk.admin.vo;

import java.io.Serializable;

public class CompanyVo implements Serializable{
    private static final long serialVersionUID = 1L;

    private String compCode;

    private String compName;

    public CompanyVo() {
        // Default constructor
    }

    public CompanyVo(String compCode, String compName) {
        this.compCode = compCode;
        this.compName = compName;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    @Override
    public String toString() {
        return "CompanyVo [compCode=" + compCode + ", compName=" + compName + "]";
    }

}
