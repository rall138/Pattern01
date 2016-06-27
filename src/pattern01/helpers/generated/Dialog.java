
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Sat Jun 25 21:23:02 UYT 2016
	* Creator: rlomez
	**/
public class Dialog{
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.String reference;
	private java.lang.String image = "generic.jpg";

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

	public java.lang.String getImage(){
		return this.image;
	}

	public void setImage(java.lang.String image){
		this.image = image;
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

	public java.lang.String toXml(){
		java.lang.String xml ="<Dialog "
		+ "name='"+this.name+"'"
		+ "description='"+this.description+"'"
		+ "reference='"+this.reference+"'"
		+ "image='"+this.image+"'"
		+ ">";
		xml+="</Dialog>";
		return xml;
	}
}