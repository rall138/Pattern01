
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Wed Jun 22 12:49:29 GFT 2016
	* Creator: rlomez
	**/
public class PatternInstance{
	private Generator generator=pattern01.helpers.generated.Generator.WEB;
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.String image = "ww.png";
	private RelatedFiles relatedFiles;
	private java.util.List<Faces> collection_Faces = new java.util.ArrayList<>();
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
	public void setCollection_Faces(java.util.List<Faces> collection_Faces){
		this.collection_Faces = collection_Faces;
	}

	public java.util.List<Faces> getCollection_Faces(){
		return this.collection_Faces;
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
			for(int index = 0; index < collection_Faces.size(); index++){
				xml+=collection_Faces.get(index).toXml();
			}
		xml+="</PatternInstance>";
		return xml;
	}
}