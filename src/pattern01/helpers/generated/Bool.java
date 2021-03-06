
package pattern01.helpers.generated;

	/**
	* Generated enum class via ClassGenerator.xml
	* Creation date: Sat Mar 25 22:48:29 UYT 2017
	* Creator: rlomez
	**/
public enum Bool {UNDEFINED, FALSE, TRUE;

	public static java.lang.String getValueDescription(Bool description){
		switch(description){
			case FALSE:
				return "FALSE";
			case TRUE:
				return "TRUE";
			default:
				return "";
		}
	}

	public static boolean fullyQualifiedComparer(String fullyQualifiedValue, String simpleValue){
		return fullyQualifiedValue.replace("pattern01.helpers.generated","").replace("Bool","").replace(".","").equalsIgnoreCase(simpleValue);
	}

	public static java.util.List<java.lang.String> getOptionCollection(){
		java.util.List<java.lang.String> optionCollection = new java.util.ArrayList<>();
		optionCollection.add("FALSE");
		optionCollection.add("TRUE");
		return optionCollection;
	}
}