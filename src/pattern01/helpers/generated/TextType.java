
package pattern01.helpers.generated;

	/**
	* Generated enum class via ClassGenerator.xml
	* Creation date: Fri Mar 31 17:23:54 GFT 2017
	* Creator: rlomez
	**/
public enum TextType {UNDEFINED, PLAINTEXT, TEXTAREA;

	public static java.lang.String getValueDescription(TextType description){
		switch(description){
			case PLAINTEXT:
				return "PLAINTEXT";
			case TEXTAREA:
				return "TEXTAREA";
			default:
				return "";
		}
	}

	public static boolean fullyQualifiedComparer(String fullyQualifiedValue, String simpleValue){
		return fullyQualifiedValue.replace("pattern01.helpers.generated","").replace("TextType","").replace(".","").equalsIgnoreCase(simpleValue);
	}

	public static java.util.List<java.lang.String> getOptionCollection(){
		java.util.List<java.lang.String> optionCollection = new java.util.ArrayList<>();
		optionCollection.add("PLAINTEXT");
		optionCollection.add("TEXTAREA");
		return optionCollection;
	}
}