import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ubs.task.MergeTask;
import com.ubs.utils.FileUtils;
import com.ubs.utils.PPTUtils;

public class Test {

	public static void main(String[] args) throws IOException {
/*		ArrayList list=new ArrayList<String>();
		list.add("C:/Users/Administrator/Desktop/test/a.pptx");
		list.add("C:/Users/Administrator/Desktop/test/b.pptx");
		list.add("C:/Users/Administrator/Desktop/test/c.pptx");
		
		PPTUtils.MergePPT(list, "C:/Users/Administrator/Desktop/test/d.pptx");*/
		
		
	File file=new File("C:/Users/Administrator/Desktop/test");
	System.out.println(file.isDirectory());
	String[] files1=FileUtils.getFiles("C:/Users/Administrator/Desktop/test");
		
	ExecutorService executor=Executors.newSingleThreadScheduledExecutor();
	Future<String> future=executor.submit(new MergeTask());
	String res;
	try {
		res = (String) future.get();
		
		System.out.println(res);
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
	}

}
