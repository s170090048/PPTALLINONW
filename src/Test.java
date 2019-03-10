import java.io.IOException;
import java.util.ArrayList;

import com.ubs.utils.PPTUtils;

public class Test {

	public static void main(String[] args) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		list.add("C:/Users/Administrator/Desktop/test/e.pptx");
		list.add("C:/Users/Administrator/Desktop/test/f.pptx");
		list.add("C:/Users/Administrator/Desktop/test/g.pptx");

		PPTUtils.MergePPT(list, "C:/Users/Administrator/Desktop/test/h.pptx");

		/*
		 * File file=new File("C:/Users/Administrator/Desktop/test");
		 * System.out.println(file.isDirectory()); String[]
		 * files1=FileUtils.getFiles("C:/Users/Administrator/Desktop/test");
		 * 
		 * ExecutorService
		 * executor=Executors.newSingleThreadScheduledExecutor(); Future<String>
		 * future=executor.submit(new MergeTask()); String res; try { res =
		 * (String) future.get();
		 * 
		 * System.out.println(res);
		 * 
		 * } catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (ExecutionException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

}
