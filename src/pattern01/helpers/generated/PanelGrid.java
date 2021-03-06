
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Sat Mar 25 22:48:29 UYT 2017
	* Creator: rlomez
	**/
public class PanelGrid implements IPatternElement{
	private java.util.List<Panel> collection_Panel = new java.util.ArrayList<>();
	private java.util.List<Property> collection_Property = new java.util.ArrayList<>();
	private java.util.List<Text> collection_Text = new java.util.ArrayList<>();
	private java.util.List<CommandButton> collection_CommandButton = new java.util.ArrayList<>();
	private java.util.List<DataTable> collection_DataTable = new java.util.ArrayList<>();
	public void setCollection_Panel(java.util.List<Panel> collection_Panel){
		this.collection_Panel = collection_Panel;
	}

	public java.util.List<Panel> getCollection_Panel(){
		return this.collection_Panel;
	}
	public void setCollection_Property(java.util.List<Property> collection_Property){
		this.collection_Property = collection_Property;
	}

	public java.util.List<Property> getCollection_Property(){
		return this.collection_Property;
	}
	public void setCollection_Text(java.util.List<Text> collection_Text){
		this.collection_Text = collection_Text;
	}

	public java.util.List<Text> getCollection_Text(){
		return this.collection_Text;
	}
	public void setCollection_CommandButton(java.util.List<CommandButton> collection_CommandButton){
		this.collection_CommandButton = collection_CommandButton;
	}

	public java.util.List<CommandButton> getCollection_CommandButton(){
		return this.collection_CommandButton;
	}
	public void setCollection_DataTable(java.util.List<DataTable> collection_DataTable){
		this.collection_DataTable = collection_DataTable;
	}

	public java.util.List<DataTable> getCollection_DataTable(){
		return this.collection_DataTable;
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
		java.lang.String xml="<PanelGrid ";
		xml+=">";
		for(int index = 0; index < collection_Panel.size(); index++){
			xml+=collection_Panel.get(index).toXml();
		}
		for(int index = 0; index < collection_Property.size(); index++){
			xml+=collection_Property.get(index).toXml();
		}
		for(int index = 0; index < collection_Text.size(); index++){
			xml+=collection_Text.get(index).toXml();
		}
		for(int index = 0; index < collection_CommandButton.size(); index++){
			xml+=collection_CommandButton.get(index).toXml();
		}
		for(int index = 0; index < collection_DataTable.size(); index++){
			xml+=collection_DataTable.get(index).toXml();
		}
		xml+="</PanelGrid>";
		return xml;
	}

	@Override
	public IPatternElement fromXml(java.lang.String xmlDocument){
		java.lang.String xpathuri = "null";
		return null;
	}
}