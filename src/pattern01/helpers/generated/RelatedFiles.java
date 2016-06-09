package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Jun 09 06:48:16 UYT 2016
	* Creator: rlomez
	**/
public class RelatedFiles{

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
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.String image="ww.png";
	private RelatedFiles relatedFiles;
	private Faces faces;
