
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Mon Nov 06 22:42:42 UYST 2017
	* Creator: rlomez
	**/
public class DataTable implements IPatternElement{
	private java.lang.String uuid = "";
	private java.lang.String parentUUID = "";
	private java.lang.String name = "";
	private java.lang.String managedBean = "";
	private java.lang.String description = "";
	private java.util.List<Column> collection_Column = new java.util.ArrayList<>();

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

	public java.lang.String getName(){
		return this.name;
	}

	public void setName(java.lang.String name){
		this.name = name;
	}

	public java.lang.String getManagedBean(){
		return this.managedBean;
	}

	public void setManagedBean(java.lang.String managedBean){
		this.managedBean = managedBean;
	}

	public java.lang.String getDescription(){
		return this.description;
	}

	public void setDescription(java.lang.String description){
		this.description = description;
	}
	public void setCollection_Column(java.util.List<Column> collection_Column){
		this.collection_Column = collection_Column;
	}

	public java.util.List<Column> getCollection_Column(){
		return this.collection_Column;
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
		java.lang.String xml="<DataTable ";
		xml+="uuid='"+this.uuid+"' ";
		xml+="parentUUID='"+this.parentUUID+"' ";
		xml+="name='"+this.name+"' ";
		xml+="managedBean='"+this.managedBean+"' ";
		xml+="description='"+this.description+"' ";
		xml+=">";
		if(collection_Column != null)
			for(int index = 0; index < collection_Column.size(); index++){
				xml+=collection_Column.get(index).toXml();
			}
		xml+="</DataTable>";
		return xml;
	}

	@Override
	public void setGenericElement(Object o){
		if (o instanceof Column){
			this.getCollection_Column().add((Column)o);
		}
	}
}