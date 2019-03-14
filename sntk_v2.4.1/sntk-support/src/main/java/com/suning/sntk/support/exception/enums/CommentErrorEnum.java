package com.suning.sntk.support.exception.enums;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

public enum CommentErrorEnum implements ServiceExceptionNameProvider {
	/**
	 * 8100 标签编码已存在
	 */
	LABEL_CODE_EXIST("sntk.comment.labelCodeExist"),
	/**
	 * 8101 同一星级下，标签名称不可重复
	 */
	LABEL_NAME_REPEAT("sntk.comment.labelNameRepeat"),

    /**
     * file_0001 初始化excel失败
     */
    INIT_EXCEL_FAIL("sntk.commont.initExcelFail")
	;
	private String name;

	CommentErrorEnum(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
