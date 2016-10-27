
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Oct 27 17:05:01 GFT 2016
	* Creator: rlomez
	**/
public class Selection implements IPatternElement{
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.String reference;
	private java.lang.Boolean insert = true;
	private java.lang.Boolean update = true;
	private java.lang.Boolean delete = true;
	private java.lang.Boolean display = true;

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

	public java.lang.String getReference(){
		return this.reference;
	}

	public void setReference(java.lang.String reference){
		this.reference = reference;
	}

	public java.lang.Boolean getInsert(){
		return this.insert;
	}

	public void setInsert(java.lang.Boolean insert){
		this.insert = insert;
	}

	public java.lang.Boolean getUpdate(){
		return this.update;
	}

	public void setUpdate(java.lang.Boolean update){
		this.update = update;
	}

	public java.lang.Boolean getDelete(){
		return this.delete;
	}

	public void setDelete(java.lang.Boolean delete){
		this.delete = delete;
	}

	public java.lang.Boolean getDisplay(){
		return this.display;
	}

	public void setDisplay(java.lang.Boolean display){
		this.display = display;
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
		java.lang.String xml="<Selection ";
		xml+="name='"+this.name+"' ";
		xml+="description='"+this.description+"' ";
		xml+="reference='"+this.reference+"' ";
		xml+="insert='"+this.insert+"' ";
		xml+="update='"+this.update+"' ";
		xml+="delete='"+this.delete+"' ";
		xml+="display='"+this.display+"' ";
		xml+=">";
		xml+="</Selection>";
		return xml;
	}

	@Override
	public IPatternElement fromXml(java.lang.String xmlDocument){
		java.lang.String xpathuri = "null";
		return null;
	}
}