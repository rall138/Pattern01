package pattern01.helpers;

public class CustomStringBuilder {
	
	private StringBuilder sb = new StringBuilder();
	
	public void append(String text){
		sb.append(text);
	}
	
	public void appendComment(int tabspaces_quantity, String text){
		sb.append(System.getProperty("line.separator")+StringHelper.tabGen(tabspaces_quantity)+"/*");
		sb.append(System.getProperty("line.separator")+StringHelper.tabGen(tabspaces_quantity)+text);
		sb.append(System.getProperty("line.separator")+StringHelper.tabGen(tabspaces_quantity)+"*/");
	}
	
	public void appendLn(String text){
		sb.append(System.getProperty("line.separator")+text);
	}

	public void appendLn(int tabspaces_quantity, String text){
		sb.append(System.getProperty("line.separator")
			+StringHelper.tabGen(tabspaces_quantity)
			+text);
	}

	public void clrlf(){
		sb.append(System.getProperty("line.separator"));
	}
	
	public String toString(){
		return sb.toString();
	}
}
