package com.ubs.utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



public class WebUtils {
	public static String  download(String sURL,String sName) throws IOException {

		int nStartPos = 0;
		int nRead = 0;
		
		try {
			URL url = new URL(sURL);
			// 打开连接
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			// 获得文件长度
			long nEndPos = FileUtils.getFileSize(sURL);
			RandomAccessFile oSavedFile = new RandomAccessFile(sName, "rw");
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
		return sName;

	}


	
	/**
	 * @author Administrator
	 * @category get filename from url
	 * @return filename
	 * @param   url
	 * 
	 *
	 */
	
	public static String getFileName(String prefix,String urlStr){
		String fileName = null;
		try {
			URL url = new URL(urlStr);
	        URLConnection uc = url.openConnection();
	        fileName = uc.getHeaderField("Content-Disposition");
	        fileName = new String(fileName.getBytes("ISO-8859-1"), "GBK");
	        fileName = URLDecoder.decode(fileName.substring(fileName.indexOf("filename=")+9),"UTF-8");
	      // log.info("文件名为：" + fileName + "  大小" + (uc.getContentLength()/1024)+"KB");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(prefix!=null || prefix!=""){
			return prefix+"/"+fileName;
			
		}
		return fileName;

	}
	
	
	public static String upload(String url, String filefullname) {
	       CloseableHttpClient httpclient = HttpClients.createDefault(); 
	      
	       try { 
	           HttpPost httppost = new HttpPost(url); 
	  
	           RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
	           httppost.setConfig(requestConfig);
	            
	           FileBody bin = new FileBody(new File(filefullname)); 
	  StringBody comment = new StringBody("This is comment",ContentType.TEXT_PLAIN);  
        HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", bin).build();  /*.addPart("comment", comment)*/
	  
	           httppost.setEntity(reqEntity); 
	  
	           System.out.println("executing request " + httppost.getRequestLine()); 
	           CloseableHttpResponse response = httpclient.execute(httppost); 
	           try { 
	               System.out.println(response.getStatusLine()); 
	               HttpEntity resEntity = response.getEntity(); 
	               if (resEntity != null) { 
	                   String responseEntityStr = EntityUtils.toString(response.getEntity());
	                   System.out.println(responseEntityStr);
	                   System.out.println("Response content length: " + resEntity.getContentLength()); 
	               } 
	               EntityUtils.consume(resEntity); 
	               
	           } finally { 
	               response.close(); 
	               
	           } 
	       } catch (ClientProtocolException e) { 
	           e.printStackTrace(); 
	       } catch (IOException e) { 
	           e.printStackTrace(); 
	       } finally { 
	           try { 
	               httpclient.close(); 
	           } catch (IOException e) { 
	               e.printStackTrace(); 
	           } 
	       }
	       return filefullname; 
	   }
	
	
	public String post(String url,String loginId,String username) throws ClientProtocolException, IOException{
	    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	    CloseableHttpResponse httpResponse = null;
	    HttpPost httpPost = new HttpPost(url);
	    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setSocketTimeout(22000).build();
	    httpPost.setConfig(requestConfig);
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("user.loginId", loginId));
	    params.add(new BasicNameValuePair("user.employeeName",username));
	    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,Charset.forName("UTF-8"));
	    httpPost.setEntity(entity);
	    httpResponse = httpClient.execute(httpPost);
	    HttpEntity responseEntity = httpResponse.getEntity();
	    if(responseEntity!=null){
	        String content = EntityUtils.toString(responseEntity,"UTF-8");
	        System.out.println(content);
	    }
	         
	    if(httpResponse!=null){
	        httpResponse.close();
	    }
	    if(httpClient!=null){
	        httpClient.close();
	    }
	    return null;
	}
	
}
