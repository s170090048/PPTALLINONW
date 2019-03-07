package com.ubs.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileUtils {

	public static String[] getFiles(String folder) {
		System.out.println(folder);
		File _folder = new File(folder);
		String[] filesInFolder;

		if (_folder.isDirectory()) {
			filesInFolder = _folder.list();
			return filesInFolder;
		} 
			return null;
		
	}

	

	public static String getFilePath() {
		Properties pros = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(new File(ClassLoader.getSystemClassLoader().getResource("config.property").getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pros.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String FilePath = pros.getProperty("FilePath");
		return FilePath;

	}
	
	
	/*@description  获取所需要合并的文件名列表
	 * 
	 * 
	 * */
	public static String[] getFileNames() {
		Properties pros = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(new File(ClassLoader.getSystemClassLoader().getResource("config.property").getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pros.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] fileNames = pros.getProperty("FileNames").split(",");
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileNames;

	}
	
	
	
	/*@description  获取所需要合并的文件名列表
	 * 
	 * 
	 * */
	public static List<String> getNamesWithFilter(String[] filenames,String filter) {
		ArrayList<String> filteredFilenames=new ArrayList<String>();
				
				for (String filename : filenames) {
					if(filename.contains(filter)){
						
						filteredFilenames.add(filename);
						
					}
				}
		
		
		
		return filteredFilenames;

	}
	
	public static String  getsUrl() {
		Properties pros = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(new File("config.property"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pros.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sUrl = pros.getProperty("sUrl");
		
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sUrl;

	}
	

	// 获得文件长度
	public static long getFileSize(String sURL) {
		int nFileLength = -1;
		try {
			URL url = new URL(sURL);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestProperty("User-Agent", "Internet Explorer");

			int responseCode = httpConnection.getResponseCode();
			if (responseCode >= 400) {
				System.err.println("Error Code : " + responseCode);
				return -2; // -2 represent access is error
			}
			String sHeader;
			for (int i = 1;; i++) {
				sHeader = httpConnection.getHeaderFieldKey(i);
				if (sHeader != null) {
					if (sHeader.equals("Content-Length")) {
						nFileLength = Integer.parseInt(httpConnection.getHeaderField(sHeader));
						break;
					}
				} else
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(nFileLength);
		return nFileLength;
	}

}
