package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Sun Apr 24 15:09:46 UYT 2016
	* Creator: rlomez
	**/
public class RelatedFile{

	private java.lang.String Name;
	private java.lang.String Description;
	private java.lang.String Reference;


	public java.lang.String getName(){
		return this.Name;
	}

	public void setName(java.lang.String Name){
		this.Name = Name;
	}

	public java.lang.String getDescription(){
		return this.Description;
	}

	public void setDescription(java.lang.String Description){
		this.Description = Description;
	}

	public java.lang.String getReference(){
		return this.Reference;
	}

	public void setReference(java.lang.String Reference){
		this.Reference = Reference;
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

}