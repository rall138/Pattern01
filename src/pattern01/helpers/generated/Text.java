
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Tue Apr 04 20:36:15 UYT 2017
	* Creator: rlomez
	**/
public class Text implements IPatternElement{
	private java.lang.String uuid = "";
	private java.lang.String parentUUID = "";
	private java.lang.String name = "";
	private java.lang.String value = "";
	private TextType type = pattern01.helpers.generated.TextType.PLAINTEXT;
	private java.lang.String description = "";

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

	public java.lang.String getValue(){
		return this.value;
	}

	public void setValue(java.lang.String value){
		this.value = value;
	}

	public TextType getType(){
		return this.type;
	}

	public void setType(TextType type){
		this.type = type;
	}

	public java.lang.String getDescription(){
		return this.description;
	}

	public void setDescription(java.lang.String description){
		this.description = description;
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
		java.lang.String xml="<Text ";
		xml+="uuid='"+this.uuid+"' ";
		xml+="parentUUID='"+this.parentUUID+"' ";
		xml+="name='"+this.name+"' ";
		xml+="value='"+this.value+"' ";
		xml+="type='"+this.type+"' ";
		xml+="description='"+this.description+"' ";
		xml+=">";
		xml+="</Text>";
		return xml;
	}

	@Override
	public void setGenericElement(Object o){
	}
}