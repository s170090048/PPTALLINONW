package com.ubs.task;

import java.util.concurrent.Callable;

import com.ubs.utils.FileUtils;
import com.ubs.utils.WebUtils;

public class DownLoadTask implements Callable<Object> {

	private String sUrl;
	private String prefix;

	public DownLoadTask(String sUrl) {
		this.sUrl = sUrl;
	}

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return WebUtils.download(sUrl, WebUtils.getFileName(prefix, sUrl));
	}

}
