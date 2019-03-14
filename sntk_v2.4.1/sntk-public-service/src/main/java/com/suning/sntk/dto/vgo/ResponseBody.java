/*
 * Copyright (C), 2002-2016, 苏宁易购电子商务有限公司
 * FileName: ResponseBody.java
 * Author:   14041582
 * Date:     2016年11月2日 上午10:36:04
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.dto.vgo;

import java.util.List;

/**
 * 响应信息<br>
 *
 * @author 14041582
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ResponseBody<T> extends BaseResponse {

    /**
     */
    private static final long serialVersionUID = -422665330709222056L;

    // 业务的具体类型
    private T responseDto;

    /**
     *
     */
    private List<T> respDatas;

    public List<T> getRespDatas() {
        return respDatas;
    }

    public void setRespDatas(List<T> respDatas) {
        this.respDatas = respDatas;
    }

    // 查询列表的结果(分页查询才需要用到,默认为空)
    private QueryResult queryResult;

    public T getResponseDto() {
        return responseDto;
    }

    public void setResponseDto(T responseDto) {
        this.responseDto = responseDto;
    }

    public QueryResult getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult;
    }

    @Override
    public String toString() {
        return "ResponseBody [responseDto=" + responseDto + ", respDatas=" + respDatas + ", queryResult=" + queryResult
                + "]";
    }

}
