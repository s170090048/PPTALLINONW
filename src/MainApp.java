import java.util.Timer;

public class MainApp {
	public static void main(String[] args) {
		
		Timer  time=new Timer();
		time.schedule(new MergeTask(), 0,1000*60*60*24);
	
	}

}
