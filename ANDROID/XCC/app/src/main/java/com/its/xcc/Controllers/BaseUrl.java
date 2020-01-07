package com.its.xcc.Controllers;

public class BaseUrl {
	String urlData;
	String urlUpload;

	String ipAddress = "https://scc-himastaits.com/";

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
