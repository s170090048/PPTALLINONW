package com.ubs.task;

import java.util.concurrent.Callable;

import com.ubs.utils.WebUtils;

public class UploadTask implements Callable {

	public UploadTask(String filefullname, String url) {
		super();
		this.filefullname = filefullname;
		this.url = url;
	}

	String filefullname;
	String url;

	@Override
	public String call() throws Exception {

		return WebUtils.upload(url, filefullname);

	}

}
