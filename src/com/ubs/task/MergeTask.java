package com.ubs.task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import com.ubs.utils.FileUtils;
import com.ubs.utils.PPTUtils;
import com.ubs.utils.WebUtils;

public class MergeTask  implements Callable{ 
	
	@Override
	public String call() {
     //		   ��ȡ�ļ�����·��	
			String filePath=FileUtils.getFilePath();
			System.out.println(filePath);
	 //      ��ȡ����·���������ļ�
			String[]  files=FileUtils.getFiles(filePath);
			System.out.println(files);
     //	               �����ļ�����Ҫ�ϲ����ļ��б�
			String[] fileNames =FileUtils.getFileNames();
			System.out.println(fileNames);

			for (String filename : fileNames) {
				
			List<String> filteredFilenames=FileUtils.getNamesWithFilter(files, filename);

			//��ȡ����ļ���
			String outPut=filename;
			System.out.println(outPut);
			try {
			    //	               �ϲ�PPT
				PPTUtils.MergePPT(filteredFilenames, outPut,filePath);
				return  "success";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failed";

			
		}

	}
			return "failed";
			
		
	}
	}
