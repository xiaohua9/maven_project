package com.learn.utils;

import com.learn.entity.PostInfo;

import java.util.List;

//用于分页显示的页面数据类
public class PageBean {
    //属性：每页显示的数量，总页数，当前页数，数据库的总数据量,当前页的数据
    private int pageSize=2;
    private int totalPages;
    private int currentPage=1;
    private int rows;
    private List<PostInfo> postInfo;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }
//////对当前页数进行限制
    public void setCurrentPage(int currentPage) {
        if (currentPage<1){
            this.currentPage=1;
        }else if (currentPage>this.getTotalPages()){
            this.currentPage=this.getTotalPages();
        }else {
            this.currentPage = currentPage;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {//当赋值总行数时，总页数即可根据下方的方式计算得到
        this.rows = rows;
        if (this.rows%this.pageSize==0){
            this.totalPages=this.rows/this.pageSize;
        }else {
            this.totalPages=this.rows/this.pageSize+1;
        }
    }

    public List<PostInfo> getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(List<PostInfo> postInfo) {
        this.postInfo = postInfo;
    }
}
