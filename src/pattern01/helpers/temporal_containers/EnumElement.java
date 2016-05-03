package pattern01.helpers.temporal_containers;

import java.util.HashMap;
import java.util.Map;

public class EnumElement extends Element{

	public enum VISIBLE_INFO{VALUE, DESCRIPTION};
	
	private String name;
	private VISIBLE_INFO visible_info;
	private Map<String, String> value_list = new HashMap<>();
	
	public EnumElement(){}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public VISIBLE_INFO getVisible_info() {
		return visible_info;
	}
	public void setVisible_info(VISIBLE_INFO visible_info) {
		this.visible_info = visible_info;
	}

	public Map<String, String> getValue_list() {
		return value_list;
	}
}