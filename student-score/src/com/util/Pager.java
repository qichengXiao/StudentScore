package com.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/*	
* 	该类用于封装   分页所需要的信息
*	
*/
public class Pager {
	private int pageSize=10; 	//页面大小
	private int currPage=1;		//当前页
	private int totalCount=0;	//总记录条数
	private int totalPage=0;	//总记录页数
	private int startItem = 0;	//开始条目
	
	
	/**
     * @param currentPage
     * @param pageSize2
     */
    public Pager(int currPage, int pageSize) {
        this.currPage = currPage;
        this.pageSize = pageSize;
    }


    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }


    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * @return the currPage
     */
    public int getCurrPage() {
        return currPage;
    }


    /**
     * @param currPage the currPage to set
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }


    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }


    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    /**
     * @return the totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }


    /**
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    /**
     * @return the startItem
     */
    public int getStartItem() {
        return startItem;
    }


    /**
     * @param startItem the startItem to set
     */
    public void setStartItem(int startItem) {
        this.startItem = startItem;
    }


    public static void main(String arg[]){
		
	}
	
}
