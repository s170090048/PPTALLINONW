package com.ubs.utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebUtils {
	public static void download(String sURL,String sName) throws IOException {

		int nStartPos = 0;
		int nRead = 0;
		//String sName = "xxx.ppt";
		String sPath = FileUtils.getFilePath();
		try {
			URL url = new URL(sURL);
			// 打开连接
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			// 获得文件长度
			long nEndPos = FileUtils.getFileSize(sURL);
			RandomAccessFile oSavedFile = new RandomAccessFile(sPath + "\\" + sName, "rw");
			httpConnection.setRequestProperty("User-Agent", "Internet Explorer");
			String sProperty = "bytes=" + nStartPos + "-";
			// 告诉服务器book.rar这个文件从nStartPos字节开始传
			httpConnection.setRequestProperty("RANGE", sProperty);
			System.out.println(sProperty);
			InputStream input = httpConnection.getInputStream();
			byte[] b = new byte[1024];
			// 读取网络文件,写入指定的文件中
			while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos) {
				oSavedFile.write(b, 0, nRead);
				nStartPos += nRead;
			}
			httpConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String uploadload(String url) throws IOException {
		return url;

	}
}
