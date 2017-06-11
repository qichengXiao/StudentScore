package com.util;



/*	
* 	该类用于封装   代码运行状态(成功或失败),成功或者失败原因,当前页码,总记录条数,总页数,主要数据
*	通过该类前端可以清晰的判断请求运行得是否成功,或者失败的原因.
*	主要用于返回前端
*/
public class PageAjaxObject {

	private String status; 
	
	private String message;
	
	private int currentPage;  
	
	private int totalCount;
	
	private int pageCount;

	private Object data;

	
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
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }


    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    /**
     * @return the pageCount
     */
    public int getPageCount() {
        return pageCount;
    }


    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    public PageAjaxObject(String status, String message, int currentPage, int pageCount, int totalCount, Object data) {
		this.status = status;
		this.message = message;
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
		this.data = data;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}

	
	
}
