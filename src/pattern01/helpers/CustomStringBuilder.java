package pattern01.helpers;

public class CustomStringBuilder{
	
	private String complete_text;
	
	public void append(String text){
		if (complete_text == null){
			complete_text = new String();
		}
		complete_text += text;
	}
	
	public void appendLn(String text){
		if (complete_text == null){
			complete_text = "";
		}
		complete_text += text;
		complete_text += System.getProperty("line.separator");
	}
	
	public String toString(){
		return complete_text;
	}
}
