package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Wed May 04 22:41:34 UYT 2016
	* Creator: rlomez
	**/
public class PatternInstance{

	private Generator generator = Generator.generator1;
	private java.lang.String Name;
	private java.lang.String Description;
	private java.lang.String Image="ww.png";
	private RelatedFiles relatedFiles;
	private Faces faces;

	public Generator getGenerator(){
		return this.generator;
	}

	public void setGenerator(Generator generator){
		this.generator = generator;
	}

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

	public java.lang.String getImage(){
		return this.Image;
	}

	public void setImage(java.lang.String Image){
		this.Image = Image;
	}

	public RelatedFiles getrelatedFiles(){
		return this.relatedFiles;
	}

	public void setRelatedFiles(RelatedFiles relatedFiles){
		this.relatedFiles = relatedFiles;
	}

	public Faces getfaces(){
		return this.faces;
	}

	public void setFaces(Faces faces){
		this.faces = faces;
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
