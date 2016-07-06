
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Tue Jul 05 16:37:50 GFT 2016
	* Creator: rlomez
	**/
public class RelatedFiles implements IPatternElement{
	private java.lang.String image = "generic.jpg";
	private java.util.List<RelatedFile> collection_RelatedFile = new java.util.ArrayList<>();

	public java.lang.String getImage(){
		return this.image;
	}

	public void setImage(java.lang.String image){
		this.image = image;
	}
	public void setCollection_RelatedFile(java.util.List<RelatedFile> collection_RelatedFile){
		this.collection_RelatedFile = collection_RelatedFile;
	}

	public java.util.List<RelatedFile> getCollection_RelatedFile(){
		return this.collection_RelatedFile;
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
		java.lang.String xml ="<RelatedFiles "
		+ "image='"+this.image+"'"
		for(int index = 0; index < collection_RelatedFile.size(); index++){
			xml+=collection_RelatedFile.get(index).toXml();
		}
		+ ">"
		+ "</RelatedFiles>";;
		return xml;
	}

	@Override
	public IPatternElement fromXml(java.lang.String xmlDocument){
		java.lang.String xpathuri = "null";
		return null;
	}
}