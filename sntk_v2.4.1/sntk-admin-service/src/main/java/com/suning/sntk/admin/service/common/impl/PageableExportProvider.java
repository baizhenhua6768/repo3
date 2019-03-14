/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: PageableExportProvider
 * Author:   17074520
 * Date:     2018/5/29 17:40
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.common.impl;

import java.util.Iterator;

import com.suning.sntk.admin.service.common.ExportProvider;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.commons.pagination.PageableBuilder;

/**
 * 功能描述：
 *
 * @author 17074520 章多亮
 * @since 2018/5/29
 */
public abstract class PageableExportProvider<T> implements ExportProvider<T> {
    private Pageable pageable;
    private Page<T> page;
    private Iterator<T> iterator;

    public PageableExportProvider() {
        pageable = PageableBuilder.pageable(0, getRowAccessWindowSize()).build();
    }

    @Override
    public int getRowAccessWindowSize() {
        return 1000;
    }

    @Override
    public boolean hasNext() {
        if (page == null) {
            page = getNextPage(pageable);
            iterator = page.iterator();
        }
        if (iterator.hasNext()) {
            return true;
        } else {
            if (page.isLast()) {
                return false;
            } else {
                pageable = page.nextPageable();
                page = getNextPage(pageable);
                iterator = page.iterator();
                return iterator.hasNext();
            }
        }
    }

    @Override
    public T next() {
        return iterator.next();
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    protected abstract Page<T> getNextPage(Pageable pageable);
}
