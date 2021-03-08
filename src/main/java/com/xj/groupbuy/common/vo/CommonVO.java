package com.xj.groupbuy.common.vo;

public class CommonVO {

	//交易成功失败表示
	private boolean success;
	//交易结果描述
	private String msg;
	//返回数据
	private Object rows;
	//返回数据
	private Object data;
	//返回总记录条数
	private String total;

	public CommonVO() {
	}

	public CommonVO(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public CommonVO(boolean success, String msg, Object rows, String total) {
		this.success = success;
		this.msg = msg;
		this.rows = rows;
		this.total = total;
	}
	
	public CommonVO(boolean success, String msg,  Object data) {
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	
	public CommonVO(boolean success,  Object data) {
		this.success = success;
		this.data = data;
		if(success){
			this.msg = "交易成功";
		}else{
			this.msg = "交易失败";
		}
	}
	
	public CommonVO(boolean success, Object data, String total) {
		this.success = success;
		this.data = data;
		this.total = total;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CommonVO [data=" + data + ", msg=" + msg + ", rows=" + rows
				+ ", success=" + success + ", total=" + total + "]";
	}
	
	
	
	
}
