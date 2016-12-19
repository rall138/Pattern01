package pattern01.helpers.temporal_containers;

import java.util.ArrayList;
import java.util.List;

public class Element {

	/* Package reference, if you want to change generation location modify this */
	public static final String classPackage = "pattern01.helpers.generated";
	public static final String editorPackage = "pattern01.plugin.components.editors.generated";
	public static final String postFix = "PatternEditor";
	
	protected List<Attribute> attribute_collection = new ArrayList<>();
	protected List<CommonElement> childElements_collection = new ArrayList<>();
	protected boolean unique;
	protected String prettyName;
	protected String name;
	protected String image;
	protected String xpathURI;
	
	public List<Attribute> getAttribute_collection() {
		return attribute_collection;
	}
	
	public void setAttribute_collection(List<Attribute> attribute_collection) {
		this.attribute_collection = attribute_collection;
	}
	
	public List<CommonElement> getChildElements_collection() {
		return childElements_collection;
	}
	
	public void setChildElements_collection(List<CommonElement> childElements_collection) {
		this.childElements_collection = childElements_collection;
	}
	
	public boolean isUnique() {
		return unique;
	}
	
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrettyName(String prettyName){
		this.prettyName = prettyName;
	}
	
	public String getPrettyName() {
		return prettyName;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setXpathURI(String xpathURI){
		this.xpathURI = xpathURI;
	}
	
	public String getXPathURI(){
		return this.xpathURI;
	}
}
