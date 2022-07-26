package com.huige.Institution.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huige.Institution.domain.vo.PageParam;

/**
 * 分页工具类
 *
 * @author hying
 */
public class PageUtil {
    /**
     * 分页查询
     */
    public static void start() {
        start(true);
    }

    /**
     * topN查询
     */
    public static void startTop() {
        start(false);
    }

    private static void start(boolean count) {
        PageParam pagingParam = PageParam.buildFromReq();
        Integer pageNum = pagingParam.getPageNum();
        Integer pageSize = pagingParam.getPageSize();
        if (!count)
            pageNum = 1;
        String orderBy = SqlUtil.escapeOrderBySql(pagingParam.getOrderBy());
        Page page = PageHelper.startPage(pageNum, pageSize, orderBy);
        if (!count)
            page.count(count);
    }
}
