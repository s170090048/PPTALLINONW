import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubs.task.DownLoadTask;
import com.ubs.task.MergeTask;
import com.ubs.task.UploadTask;
import com.ubs.utils.FileUtils;
import com.ubs.utils.HtmlParser;

public class MainApp {
	public static void main(String[] args) {

		// step1 。 下载文件
		// 获取要下载的连接

		HashMap<String, String> map = new HashMap();
		map.put("OSTO", "https://www.baidu.com");
		map.put("OOT", "https://www.baidu.com");
		map.put("OSM", "https://www.baidu.com");
		map.put("OSL", "https://www.baidu.com");

		HashMap<String, String> outputmapping = new HashMap();
		outputmapping.put("OSTO", "C:/Users/Administrator/Desktop/test/OSTO.pptx");
		outputmapping.put("OOT", "C:/Users/Administrator/Desktop/test/OOT.pptx");
		outputmapping.put("OSM", "C:/Users/Administrator/Desktop/test/OSM.pptx");
		outputmapping.put("OSL", "C:/Users/Administrator/Desktop/test/OSL.pptx");

		for (String foldername : map.keySet()) {
			// 读取文件下载路径
			String filePath = FileUtils.getFilePath();
			File file = new File(filePath + "/" + foldername);

			if (!file.exists()) {

				file.mkdir();
			}
			ArrayList<String> hreflist = new ArrayList<String>();
			try {
				hreflist = new HtmlParser(map.get(foldername)).getHrefList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ExecutorService executor = Executors.newCachedThreadPool();

			ArrayList<String> filenames = new ArrayList<String>();
			if (!hreflist.isEmpty()) {

				for (String url : hreflist) {
					DownLoadTask dt = new DownLoadTask(url);
					java.util.concurrent.Future<Object> res = executor.submit(dt);
					try {
						filenames.add((String) res.get());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			executor.shutdown();

			// 合并PPT 。

			MergeTask mt = new MergeTask(filenames, outputmapping.get(foldername));
			java.util.concurrent.Future<String> res = executor.submit(mt);
			String outputfilename = null;
			try {
				outputfilename = res.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 上传PPT

			UploadTask ut = new UploadTask(outputfilename, map.get(foldername));
			java.util.concurrent.Future<String> res1 = executor.submit(ut);
			String finalname = "";
			try {
				finalname = res1.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (finalname != null || finalname != "") {

				System.out.println("成功上传" + finalname);

			}

		}
	}
}
