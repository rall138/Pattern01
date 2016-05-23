package pattern01.helpers;

public class CustomStringBuilder{
	
	private String complete_text;
	
	public void append(String text){
		initializeString();
		complete_text += text;
	}
	
	public void appendLn(String text){
		initializeString();
		complete_text += text;
		complete_text += System.getProperty("line.separator");
	}
	
	public void clrlf(){
		initializeString();
		complete_text += System.getProperty("line.separator");
	}
	
	private void initializeString(){
		if (complete_text == null){
			complete_text = "";
		}
		complete_text += System.getProperty("line.separator");		
	}

	public String toString(){
		return complete_text;
	}
}
