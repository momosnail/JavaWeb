package com.futurus.usermsgdemo.domain;

import java.util.List;

/**
 * 分页工具对象
 */
public class PageBean<T> {
    private int totalCount; //总记录数
    private int totalPage; //总页码
    private List<T> mList; //每页的数据
    private int currentPage; //当前页码
    private int rows; //每页显示的记录数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> list) {
        mList = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", mList=" + mList +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
