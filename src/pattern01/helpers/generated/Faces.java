package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Jun 02 23:33:24 UYT 2016
	* Creator: rlomez
	**/
public class Faces{

	private Selection selection;
	private Register register;
	private java.util.List<Dialog> collection_Dialog = new java.util.ArrayList<>();


	public Selection getselection(){
		return this.selection;
	}

	public void setSelection(Selection selection){
		this.selection = selection;
	}

	public Register getregister(){
		return this.register;
	}

	public void setRegister(Register register){
		this.register = register;
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

	public java.lang.String toXml(){
		java.lang.String xml ="<Faces "
		+ ">";
			xml+=this.selection.toXml();
			xml+=this.register.toXml();
			for(int index = 0; index < collection_Dialog.size(); index++){
				xml+=collection_Dialog.get(index).toXml();
			}
		xml+="</Faces>";
		return xml;
	}
}
