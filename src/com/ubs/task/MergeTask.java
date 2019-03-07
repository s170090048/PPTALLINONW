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
     //		   读取文件下载路径	
			String filePath=FileUtils.getFilePath();
			System.out.println(filePath);
	 //      获取下载路径的所有文件
			String[]  files=FileUtils.getFiles(filePath);
			System.out.println(files);
     //	               配置文件中需要合并的文件列表
			String[] fileNames =FileUtils.getFileNames();
			System.out.println(fileNames);

			for (String filename : fileNames) {
				
			List<String> filteredFilenames=FileUtils.getNamesWithFilter(files, filename);

			//获取输出文件名
			String outPut=filename;
			System.out.println(outPut);
			try {
			    //	               合并PPT
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
