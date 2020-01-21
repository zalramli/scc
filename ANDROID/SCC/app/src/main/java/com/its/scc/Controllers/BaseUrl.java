package com.its.scc.Controllers;

public class BaseUrl {
	String urlData;
	String urlUpload;

	String ipAddress = "http://192.168.137.1/scc/";

	public BaseUrl() {
		urlData = ipAddress + "api/";
		urlUpload = ipAddress + "";
	}

	public String getUrlData() {
		return urlData;
	}

	public String getUrlUpload() {
		return urlUpload;
	}
}
