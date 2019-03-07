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
			// ������
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			// ����ļ�����
			long nEndPos = FileUtils.getFileSize(sURL);
			RandomAccessFile oSavedFile = new RandomAccessFile(sPath + "\\" + sName, "rw");
			httpConnection.setRequestProperty("User-Agent", "Internet Explorer");
			String sProperty = "bytes=" + nStartPos + "-";
			// ���߷�����book.rar����ļ���nStartPos�ֽڿ�ʼ��
			httpConnection.setRequestProperty("RANGE", sProperty);
			System.out.println(sProperty);
			InputStream input = httpConnection.getInputStream();
			byte[] b = new byte[1024];
			// ��ȡ�����ļ�,д��ָ�����ļ���
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
