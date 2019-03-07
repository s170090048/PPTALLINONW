package com.ubs.task;
import java.io.IOException;
import java.util.TimerTask;

import com.ubs.utils.FileUtils;
import com.ubs.utils.WebUtils;

public class DownLoadTask  extends TimerTask {
    
	@Override
	public void run() {
//		step1 .  ��ȡ����Ҫ���ص��ļ��б�
		String[] fileNames=FileUtils.getFileNames();
//	    step2.   ��ȡ����·��	
		String filePath=FileUtils.getFilePath();

		// TODO Auto-generated method stub
//      step3.   ��ȡ��������
		String sUrl=FileUtils.getsUrl();
//      step4.   �����ļ�
		try {
			WebUtils.download(sUrl, sUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	
//       Step5. ��ȡ���ص� �ļ�
		String[]  files=null;
		files=FileUtils.getFiles(filePath);
		
	}

}
