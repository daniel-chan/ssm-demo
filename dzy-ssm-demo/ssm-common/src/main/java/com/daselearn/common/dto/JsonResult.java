package com.daselearn.common.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

public class JsonResult implements Serializable{

	public static final int RESP_SUCCESS = 200;
	public static final int RESP_FAILURE = -1;
	private int code;
	private String msg;
	private Object data ;

	public JsonResult(int code, String msg, Object data){
		this.setCode(code);
		this.setMsg(msg);
		this.setData(data) ;
	}
	public JsonResult(int code, String msg){
		this.setCode(code);
		this.setMsg(msg);
		this.setData(null);
	}

	 
	public static JsonResult create(int code, String msg, Object data){
		return new JsonResult(code, msg, data);
	}
	public static JsonResult create(int code, String msg){
		return new JsonResult(code, msg, null);
	}


	public static JsonResult error(String msg, Object data){
		return new JsonResult(RESP_FAILURE,msg,data);
	}
	public static JsonResult error(int code,String msg){
		return new JsonResult(code,msg);
	}
	public static JsonResult error(String msg){
		return new JsonResult(RESP_FAILURE,msg);
	}


	public static JsonResult success(String msg, Object data){
		return new JsonResult(RESP_SUCCESS,msg,data);
	}
	public static JsonResult success(String msg){
		return new JsonResult(RESP_SUCCESS,msg);
	}
	public static JsonResult success(Object data){
		return new JsonResult(RESP_SUCCESS,"",data);
	}


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
		if(data ==null){
			data = "";
		}
		this.data = data;
	}
	
	public String toJson() {
		 return JSON.toJSONString(this);
	}

	public String toJson(SerializerFeature serializerFeature) {
		 return JSON.toJSONString(this,serializerFeature);
	}

}
