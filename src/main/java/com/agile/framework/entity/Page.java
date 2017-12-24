/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */
package com.agile.framework.entity;

import java.util.List;

/**
 * 分页封装类
 * @param <T>
 */
public class Page<T> {
    
    private static final int DEFAULT_PAGE_SIZE = 10;       
    private static final int DEFAULT_CURRENT_PAGE = 1;   
    
	// 下一页
    private int pageIndex;

    // 当前页
    private int currentPage;

    // 每页个数
    private int pageSize;

    // 总条数
    private int totalCount;

    // 总页数
    private int pageCount;

    // 记录集
    private List<T> results;

    public Page() {   
        this.currentPage = DEFAULT_CURRENT_PAGE;   
        this.pageSize = DEFAULT_PAGE_SIZE;   
    }  
    
    public Page(int currentPage) {   
        this.currentPage = currentPage;   
        this.pageSize = DEFAULT_PAGE_SIZE;   
    } 
    
    public Page(int currentPage, int pageSize) {   
        this.currentPage = currentPage;   
        this.pageSize = pageSize;   
    }   
     
    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        if (pageIndex <= 0) {
            return 1;
        } else{
            return pageIndex > pageCount ? pageCount : pageIndex;
        }
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = (pageSize <= 0 ? 10 : pageSize);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getFirstIndex() {   
        return pageSize * (currentPage - 1);   
    }   
   
    public boolean hasPrevious() {   
        return currentPage > 1;   
    }   
   
    public boolean hasNext() {   
        return currentPage < getTotalPage();   
    } 
    
    public int getTotalPage() {       	   
        int total = totalCount % pageSize == 0 ? totalCount / pageSize
                : totalCount / pageSize + 1;
        return total;
    }
    
}
