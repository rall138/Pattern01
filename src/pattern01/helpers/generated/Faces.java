
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Tue Jul 05 08:25:51 UYT 2016
	* Creator: rlomez
	**/
public class Faces implements IPatternElement{
	private java.lang.String image = "generic.jpg";
	private java.util.List<Selection> collection_Selection = new java.util.ArrayList<>();
	private java.util.List<Register> collection_Register = new java.util.ArrayList<>();
	private java.util.List<Dialog> collection_Dialog = new java.util.ArrayList<>();

	public java.lang.String getImage(){
		return this.image;
	}

	public void setImage(java.lang.String image){
		this.image = image;
	}
	public void setCollection_Selection(java.util.List<Selection> collection_Selection){
		this.collection_Selection = collection_Selection;
	}

	public java.util.List<Selection> getCollection_Selection(){
		return this.collection_Selection;
	}
	public void setCollection_Register(java.util.List<Register> collection_Register){
		this.collection_Register = collection_Register;
	}

	public java.util.List<Register> getCollection_Register(){
		return this.collection_Register;
	}
	public void setCollection_Dialog(java.util.List<Dialog> collection_Dialog){
		this.collection_Dialog = collection_Dialog;
	}

	public java.util.List<Dialog> getCollection_Dialog(){
		return this.collection_Dialog;
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
		java.lang.String xml ="<Faces "
		+ "image='"+this.image+"'";
		for(int index = 0; index < collection_Selection.size(); index++){
			xml+=collection_Selection.get(index).toXml();
		}
		for(int index = 0; index < collection_Register.size(); index++){
			xml+=collection_Register.get(index).toXml();
		}
		for(int index = 0; index < collection_Dialog.size(); index++){
			xml+=collection_Dialog.get(index).toXml();
		}
		xml+="</Faces>";
		return xml;
	}
}