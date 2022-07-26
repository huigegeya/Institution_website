package com.huige.Institution.domain.vo;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.huige.Institution.util.ServletUtil;

import javax.validation.constraints.Max;

/**
 * 分页参数
 *
 * @author hying75@126.con
 */
public class PageParam {
    private static final String[] ORDER_BY_TYPES = {"asc", "desc"};
    /**
     * 页号
     */
    public static final String PAGE_NUM = "page";

    /**
     * 每页显示记录数
     */
    @Max(value = 100, message = "分页数据量过大")
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";
    /**
     * 页号
     */
    private Integer pageNum;

    /**
     * 每页显示记录数
     */
    private Integer pageSize;

    /**
     * 排序列
     */
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    private String isAsc = "asc";

    public String getOrderBy() {
        if (StrUtil.isBlank(orderByColumn)) {
            return "";
        }
        if (StrUtil.isBlank(isAsc) || !ArrayUtil.containsIgnoreCase(ORDER_BY_TYPES, isAsc))
            return "";
        return StrUtil.toUnderlineCase(orderByColumn) + " " + isAsc;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }

    public static PageParam buildFromReq() {
        PageParam pageParam = new PageParam();
        // 一定要有默认值
        pageParam.setPageNum(ServletUtil.getParameterToInt(PAGE_NUM, 1));
        // 一定要有默认值
        pageParam.setPageSize(ServletUtil.getParameterToInt(PAGE_SIZE, 10));
        pageParam.setOrderByColumn(ServletUtil.getParameter(ORDER_BY_COLUMN));
        pageParam.setIsAsc(ServletUtil.getParameter(IS_ASC));
        return pageParam;
    }
}
