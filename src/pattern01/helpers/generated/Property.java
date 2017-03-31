
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Fri Mar 31 17:23:54 GFT 2017
	* Creator: rlomez
	**/
public class Property implements IPatternElement{
	private java.lang.String uuid = "";
	private java.lang.String parentUUID = "";
	private java.lang.String name = "";
	private java.lang.String description = "";
	private java.lang.String type = "";
	private java.lang.String defaultValue = "";

	public java.lang.String getUuid(){
		return this.uuid;
	}

	public void setUuid(java.lang.String uuid){
		this.uuid = uuid;
	}

	public java.lang.String getParentUUID(){
		return this.parentUUID;
	}

	public void setParentUUID(java.lang.String parentUUID){
		this.parentUUID = parentUUID;
	}

	public java.lang.String getName(){
		return this.name;
	}

	public void setName(java.lang.String name){
		this.name = name;
	}

	public java.lang.String getDescription(){
		return this.description;
	}

	public void setDescription(java.lang.String description){
		this.description = description;
	}

	public java.lang.String getType(){
		return this.type;
	}

	public void setType(java.lang.String type){
		this.type = type;
	}

	public java.lang.String getDefaultValue(){
		return this.defaultValue;
	}

	public void setDefaultValue(java.lang.String defaultValue){
		this.defaultValue = defaultValue;
	}

	public java.lang.String getProperty(java.lang.String propertyName){
		java.util.Properties properties = new java.util.Properties();
		try{
			java.io.FileInputStream input = new java.io.FileInputStream("Custom.properties");
			properties.load(input);
		} catch (java.io.IOException e) {
			e.printStackTrace(System.err);
		}
		return properties.getProperty(propertyName);
	}

	@Override
	public java.lang.String toXml(){
		java.lang.String xml="<Property ";
		xml+="uuid='"+this.uuid+"' ";
		xml+="parentUUID='"+this.parentUUID+"' ";
		xml+="name='"+this.name+"' ";
		xml+="description='"+this.description+"' ";
		xml+="type='"+this.type+"' ";
		xml+="defaultValue='"+this.defaultValue+"' ";
		xml+=">";
		xml+="</Property>";
		return xml;
	}

	@Override
	public void setGenericElement(Object o){
	}
}