package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Mon Jun 06 21:21:10 UYT 2016
	* Creator: rlomez
	**/
public class PatternInstance{

	private Generator generator = Generator.WEB;
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.String image="ww.png";
	private RelatedFiles relatedFiles;
	private Faces faces;

	public Generator getGenerator(){
		return this.generator;
	}

	public void setGenerator(Generator generator){
		this.generator = generator;
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

	public java.lang.String getImage(){
		return this.image;
	}

	public void setImage(java.lang.String image){
		this.image = image;
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

	public java.lang.String toXml(){
		java.lang.String xml ="<PatternInstance "
		+ "generator='"+this.generator+"'"
		+ "name='"+this.name+"'"
		+ "description='"+this.description+"'"
		+ "image='"+this.image+"'"
		+ ">";
			xml+=this.relatedFiles.toXml();
			xml+=this.faces.toXml();
		xml+="</PatternInstance>";
		return xml;
	}
}
