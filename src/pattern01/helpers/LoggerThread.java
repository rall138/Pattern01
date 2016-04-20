package pattern01.helpers;

import java.util.ArrayList;
import java.util.List;

public class LoggerThread extends Thread{

	private List<String> messageCollection = new ArrayList<>();

	public LoggerThread(){}
	
	public void addMessage(String message){
		messageCollection.add(message);
	}
	
	public void clearMessages(){
		this.messageCollection.clear();
	}
	
	public void writeSingleMessage(String message){
		clearMessages();
		this.messageCollection.add(0, message);
		run();
	}
	
	@Override
	public void run() {
		for(String message : this.messageCollection){
			System.out.println(message);
		}
	}

}