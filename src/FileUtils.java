import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class FileUtils {

	private static String[] getFiles(String folder) throws IOException {
		File _folder = new File(folder);
		String[] filesInFolder;

		if (_folder.isDirectory()) {
			filesInFolder = _folder.list();
			return filesInFolder;
		} else {
			throw new IOException("Path is not a directory");
		}
	}

	private static void download(String sURL) throws IOException {

		int nStartPos = 0;
		int nRead = 0;
		String sName = "xxx.ppt";
		String sPath = FileUtils.getFilePath();
		try {
			URL url = new URL(sURL);
			// ������
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			// ����ļ�����
			long nEndPos = getFileSize(sURL);
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

	private static String uploadload(String url) throws IOException {
		return url;

	}

	private static String getFilePath() {
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
		String FilePath = pros.getProperty("FilePath");
		return FilePath;

	}
	
	private static String[] getFileNames() {
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
		String[] fileNames = pros.getProperty("getFileNames").split(",");
		
		
		return fileNames;

	}
	
	
	

	// ����ļ�����
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
