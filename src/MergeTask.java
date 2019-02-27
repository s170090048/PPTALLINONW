import java.io.IOException;
import java.util.TimerTask;

public class MergeTask  extends TimerTask{
    
	
	
	
	@Override
	public void run() {
//		step1 .  ��ȡ����Ҫ���ص��ļ�
		String[] fileNames=FileUtils.getFileNames();
//	    step2.   ��ȡ����·��	
		String filePath=FileUtils.getFilePath();
//      step3.   ��ȡ��������
		String sUrl=FileUtils.getsUrl();
//      step4.   �����ļ�
		try {
			FileUtils.download(sUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	
//       Step5. ��ȡ���ص� �ļ�
		String[]  files=null;
		try {
			files=FileUtils.getFiles(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
//       Step6. �ϲ�PPT
		try {
			PPTUtils.MergePPT(files, filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
//       Step7.  �ϴ�PPT
		
		try {
			FileUtils.uploadload(sUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
	}

}
