package pattern01.helpers.temporal_containers;

public class Attribute {
	
	private String name;
	private String type;
	private String default_value;
	private boolean requiered;
	private String group = "Default";
	
	public Attribute(){}
	
	public Attribute(String name, String type, boolean requiered){
		this.name = name;
		this.type = type;
		this.requiered = requiered;
	}
	
	public boolean isCustomAttribute(){
		return type.contains("#{");
	}

	public String getName() {
		return name;
	}

	public String getPrettyName() {
		return name.substring(0, 1).toUpperCase()+name.substring(1);
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

	public void setType(String type) {
		this.type = type;
	}

	public void setRequiered(boolean requiered) {
		this.requiered = requiered;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}
	
	@Override
	public String toString(){
		return "Attribute: Name["+this.getName()+"], PrettyName["+this.getPrettyName()+"], Type["+this.getType()+"], "+
				"Requiered["+Boolean.toString(this.isRequiered())+"], DefaultValue["+this.getDefault_value()+"], "+
				"Group["+this.getGroup()+"]";
	}
}
