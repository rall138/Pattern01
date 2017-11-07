
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Mon Nov 06 22:42:42 UYST 2017
	* Creator: rlomez
	**/
public class Messages implements IPatternElement{
	private java.lang.String uuid = "";
	private java.lang.String parentUUID = "";

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
		java.lang.String xml="<Messages ";
		xml+="uuid='"+this.uuid+"' ";
		xml+="parentUUID='"+this.parentUUID+"' ";
		xml+=">";
		xml+="</Messages>";
		return xml;
	}

	@Override
	public void setGenericElement(Object o){
	}
}