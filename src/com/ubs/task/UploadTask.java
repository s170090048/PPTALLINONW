package com.ubs.task;

import java.io.IOException;
import java.util.TimerTask;

import com.ubs.utils.WebUtils;

public class UploadTask extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
	
	try {
		WebUtils.uploadload(sUrl);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
	
	
       }
}

