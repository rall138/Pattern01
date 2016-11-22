
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Tue Nov 22 16:58:35 GFT 2016
	* Creator: rlomez
	**/
public class Selection implements IPatternElement{
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.String reference;
	private Bool insert = pattern01.helpers.generated.Bool.TRUE;
	private Bool update = pattern01.helpers.generated.Bool.TRUE;
	private Bool delete = pattern01.helpers.generated.Bool.TRUE;
	private Bool display = pattern01.helpers.generated.Bool.TRUE;

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
	public Bool getInsert(){
		return this.insert;
	}

	public void setInsert(Bool insert){
		this.insert = insert;
	}
	public Bool getUpdate(){
		return this.update;
	}

	public void setUpdate(Bool update){
		this.update = update;
	}
	public Bool getDelete(){
		return this.delete;
	}

	public void setDelete(Bool delete){
		this.delete = delete;
	}
	public Bool getDisplay(){
		return this.display;
	}

	public void setDisplay(Bool display){
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