import java.io.IOException;
import java.util.TimerTask;

public class MergeTask  extends TimerTask{
    
	
	
	
	@Override
	public void run() {
//		step1 .  读取所需要下载的文件
		String[] fileNames=FileUtils.getFileNames();
//	    step2.   读取下载路径	
		String filePath=FileUtils.getFilePath();
//      step3.   获取下载链接
		String sUrl=FileUtils.getsUrl();
//      step4.   下载文件
		try {
			FileUtils.download(sUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	
//       Step5. 获取下载的 文件
		String[]  files=null;
		try {
			files=FileUtils.getFiles(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
//       Step6. 合并PPT
		try {
			PPTUtils.MergePPT(files, filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
//       Step7.  上传PPT
		
		try {
			FileUtils.uploadload(sUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
	}

}
