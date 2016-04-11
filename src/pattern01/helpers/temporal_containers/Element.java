package pattern01.helpers.temporal_containers;

import java.util.ArrayList;
import java.util.List;

public class Element {

	private List<Attribute> attribute_collection = new ArrayList<>();
	private List<Element> childElements_collection = new ArrayList<>();
	private String name;
	private String prettyName;
	
	public Element(){
		
	}
	
	public Element(String name, String prettyName){
		this.name = name;
		this.prettyName = prettyName;
	}

	public List<Attribute> getAttribute_collection() {
		return attribute_collection;
	}

	public List<Element> getChildElements_collection() {
		return childElements_collection;
	}
	
	public String getName() {
		return name;
	}

	public String getPrettyName() {
		return prettyName;
	}

	public void setAttribute_collection(List<Attribute> attribute_collection) {
		this.attribute_collection = attribute_collection;
	}

	public void setChildElements_collection(List<Element> childElements_collection) {
		this.childElements_collection = childElements_collection;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrettyName(String prettyName) {
		this.prettyName = prettyName;
	}
	
	
	
	
}
