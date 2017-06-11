package com.util;

/*	
* 	该类用于封装   代码运行状态(成功或失败),成功或者失败原因,主要数据
*	通过该类前端可以清晰的判断请求运行得是否成功,或者失败的原因.
*	主要用于后台返回前端	
*/
public class AjaxObject {
	
	private String status;//代码运行状态(结果)
		
	private String message;//成功或者失败原因

	private Object data;//主要数据对象

	
	public AjaxObject(String status, String message, Object data) {
		this.status = status;
		this.message = message;
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
