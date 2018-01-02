package com.mcflythekid.lazylearncore.response;

public class JsonResponse {
	
	private static final String STATUS_OK ="ok";
	private static final String STATUS_ERROR ="error";
	
	private JsonResponse(String status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	private JsonResponse(String status, String msg) {
		this(status, msg, null);
	}

	public static JsonResponse ok(){
		return new JsonResponse(STATUS_OK, "", null);
	}
	public static JsonResponse ok(String msg){
		return new JsonResponse(STATUS_OK, msg, null);
	}
	public static JsonResponse ok(String msg, Object data){
		return new JsonResponse(STATUS_OK, msg, data);
	}
	
	public static JsonResponse error(){
		return new JsonResponse(STATUS_ERROR, "", null);
	}
	public static JsonResponse error(String msg){
		return new JsonResponse(STATUS_ERROR, msg, null);
	}
	public static JsonResponse error(String msg, Object data){
		return new JsonResponse(STATUS_ERROR, msg, data);
	}
	
	private String status;
	private String msg;
	private Object data;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
