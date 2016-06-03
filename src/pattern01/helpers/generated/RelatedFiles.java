package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Jun 02 23:33:24 UYT 2016
	* Creator: rlomez
	**/
public class RelatedFiles{

	private java.util.List<RelatedFile> collection_RelatedFile = new java.util.ArrayList<>();

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

	public java.lang.String toXml(){
		java.lang.String xml ="<RelatedFiles "
		+ ">";
			for(int index = 0; index < collection_RelatedFile.size(); index++){
				xml+=collection_RelatedFile.get(index).toXml();
			}
		xml+="</RelatedFiles>";
		return xml;
	}
}
