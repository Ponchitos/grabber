package grabber;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import grabber.net.INetwork;
import grabber.net.NetWork;

public class Grabber extends TimerTask {
	private String newText = "";
	private String oldText = "";
	
	private INetwork network;
	
	public Grabber() throws IOException {
		network = new NetWork();
	}

	@Override
	public void run() {
		try {
			newText = network.connect();
			System.out.println(newText);
			
			if(!newText.equals(oldText)) {
				network.sendJson(newText);
				oldText = newText;
				newText = "";
			} 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		TimerTask timerTask = new Grabber();
		Timer timer = new Timer(false);
		timer.schedule(timerTask, 0, 30000);
		System.out.println("Service start");
	}

}
