package com.learning.restapi.dto;

import com.learning.restapi.enumeration.responseCodes;

public class ResponseJson {

	private String code;
	private String desc;
	private Object data;
		
		
		public ResponseJson() {
		}
		public ResponseJson(responseCodes code, Object data) {
			this.code = code.getCode();
			this.desc = code.getDesc();
			this.data = data;
		}
		public ResponseJson(responseCodes code) {
			this.code = code.getCode();
			this.desc = code.getDesc();
		}
		public String getcode() {
			return code;
		}
		public void setcode(String code) {
			this.code = code;
		}
		public String getdesc() {
			return desc;
		}
		public void setdesc(String desc) {
			this.desc = desc;
		}
		public Object getdata() {
			return data;
		}
		public void setdata(Object data) {
			this.data = data;
		}
}