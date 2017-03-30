
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Mar 30 17:08:02 GFT 2017
	* Creator: rlomez
	**/
public class Messages implements IPatternElement{

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
		xml+=">";
		xml+="</Messages>";
		return xml;
	}

	@Override
	public void setGenericElement(Object o){
	}
}