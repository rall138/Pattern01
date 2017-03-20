
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Mon Mar 20 17:47:44 GFT 2017
	* Creator: rlomez
	**/
public class CommandButton implements IPatternElement{
	private java.lang.String name;
	private java.lang.String action;
	private java.lang.String description;

	public java.lang.String getName(){
		return this.name;
	}

	public void setName(java.lang.String name){
		this.name = name;
	}

	public java.lang.String getAction(){
		return this.action;
	}

	public void setAction(java.lang.String action){
		this.action = action;
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
		java.lang.String xml="<CommandButton ";
		xml+="name='"+this.name+"' ";
		xml+="action='"+this.action+"' ";
		xml+="description='"+this.description+"' ";
		xml+=">";
		xml+="</CommandButton>";
		return xml;
	}

	@Override
	public IPatternElement fromXml(java.lang.String xmlDocument){
		java.lang.String xpathuri = "null";
		return null;
	}
}