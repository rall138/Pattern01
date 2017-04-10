
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Sun Apr 09 23:06:08 UYT 2017
	* Creator: rlomez
	**/
public class Panel implements IPatternElement{
	private java.lang.String uuid = "";
	private java.lang.String parentUUID = "";
	private java.util.List<Property> collection_Property = new java.util.ArrayList<>();
	private java.util.List<Text> collection_Text = new java.util.ArrayList<>();
	private java.util.List<CommandButton> collection_CommandButton = new java.util.ArrayList<>();
	private java.util.List<DataTable> collection_DataTable = new java.util.ArrayList<>();

	public java.lang.String getUuid(){
		return this.uuid;
	}

	public void setUuid(java.lang.String uuid){
		this.uuid = uuid;
	}

	public java.lang.String getParentUUID(){
		return this.parentUUID;
	}

	public void setParentUUID(java.lang.String parentUUID){
		this.parentUUID = parentUUID;
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
		java.lang.String xml="<Panel ";
		xml+="uuid='"+this.uuid+"' ";
		xml+="parentUUID='"+this.parentUUID+"' ";
		xml+=">";
		if(collection_Property != null)
			for(int index = 0; index < collection_Property.size(); index++){
				xml+=collection_Property.get(index).toXml();
			}
		if(collection_Text != null)
			for(int index = 0; index < collection_Text.size(); index++){
				xml+=collection_Text.get(index).toXml();
			}
		if(collection_CommandButton != null)
			for(int index = 0; index < collection_CommandButton.size(); index++){
				xml+=collection_CommandButton.get(index).toXml();
			}
		if(collection_DataTable != null)
			for(int index = 0; index < collection_DataTable.size(); index++){
				xml+=collection_DataTable.get(index).toXml();
			}
		xml+="</Panel>";
		return xml;
	}

	@Override
	public void setGenericElement(Object o){
		if (o instanceof Property){
			this.getCollection_Property().add((Property)o);
		}else if (o instanceof Text){
			this.getCollection_Text().add((Text)o);
		}else if (o instanceof CommandButton){
			this.getCollection_CommandButton().add((CommandButton)o);
		}else if (o instanceof DataTable){
			this.getCollection_DataTable().add((DataTable)o);
		}
	}
}