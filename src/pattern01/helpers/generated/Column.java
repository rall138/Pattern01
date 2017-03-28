
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Tue Mar 28 17:03:02 GFT 2017
	* Creator: rlomez
	**/
public class Column implements IPatternElement{
	private java.lang.String headerText;
	private java.util.List<Text> collection_Text = new java.util.ArrayList<>();

	public java.lang.String getHeaderText(){
		return this.headerText;
	}

	public void setHeaderText(java.lang.String headerText){
		this.headerText = headerText;
	}
	public void setCollection_Text(java.util.List<Text> collection_Text){
		this.collection_Text = collection_Text;
	}

	public java.util.List<Text> getCollection_Text(){
		return this.collection_Text;
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
		java.lang.String xml="<Column ";
		xml+="headerText='"+this.headerText+"' ";
		xml+=">";
		for(int index = 0; index < collection_Text.size(); index++){
			xml+=collection_Text.get(index).toXml();
		}
		xml+="</Column>";
		return xml;
	}

	@Override
	public void setGenericElement(Object o){
		if (o instanceof Column){
			this.getCollection_Text().add((Text)o);
		}
	}
}