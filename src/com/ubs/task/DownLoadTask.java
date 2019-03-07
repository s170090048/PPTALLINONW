package com.ubs.task;
import java.io.IOException;
import java.util.TimerTask;

import com.ubs.utils.FileUtils;
import com.ubs.utils.WebUtils;

public class DownLoadTask  extends TimerTask {
    
	@Override
	public void run() {
//		step1 .  读取所需要下载的文件列表
		String[] fileNames=FileUtils.getFileNames();
//	    step2.   读取下载路径	
		String filePath=FileUtils.getFilePath();

		// TODO Auto-generated method stub
//      step3.   获取下载链接
		String sUrl=FileUtils.getsUrl();
//      step4.   下载文件
		try {
			WebUtils.download(sUrl, sUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	
//       Step5. 获取下载的 文件
		String[]  files=null;
		files=FileUtils.getFiles(filePath);
		
	}

}
