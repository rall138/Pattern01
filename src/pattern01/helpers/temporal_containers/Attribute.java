package pattern01.helpers.temporal_containers;

public class Attribute {
	
	private String name;
	private String prettyName;
	private String type;
	private boolean requiered;
	
	public Attribute(){
		
	}
	
	public Attribute(String name, String prettyName, 
			String type, boolean requiered){
		this.name = name;
		this.prettyName = prettyName;
		this.type = type;
		this.requiered = requiered;
	}

	public String getName() {
		return name;
	}

	public String getPrettyName() {
		return prettyName;
	}

	public String getType() {
		return type;
	}

	public boolean isRequiered() {
		return requiered;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrettyName(String prettyName) {
		this.prettyName = prettyName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRequiered(boolean requiered) {
		this.requiered = requiered;
	}

	
}
