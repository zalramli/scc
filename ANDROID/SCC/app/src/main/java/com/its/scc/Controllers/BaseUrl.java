package com.its.scc.Controllers;

public class BaseUrl {
	String urlData;
	String urlUpload;

	String ipAddress = "https://scc-himastaits.com/";

	public BaseUrl() {
		urlData = ipAddress + "api/";
		urlUpload = ipAddress + "upload/";
	}

	public String getUrlData() {
		return urlData;
	}

	public String getUrlUpload() {
		return urlUpload;
	}
}
