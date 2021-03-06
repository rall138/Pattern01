
package pattern01.helpers.generated;

	/**
	* Generated enum class via ClassGenerator.xml
	* Creation date: Sat Mar 25 22:48:29 UYT 2017
	* Creator: rlomez
	**/
public enum Generator {UNDEFINED, WEB, MOBILE;

	public static java.lang.String getValueDescription(Generator description){
		switch(description){
			case WEB:
				return "WEB";
			case MOBILE:
				return "MOBILE";
			default:
				return "";
		}
	}

	public static boolean fullyQualifiedComparer(String fullyQualifiedValue, String simpleValue){
		return fullyQualifiedValue.replace("pattern01.helpers.generated","").replace("Generator","").replace(".","").equalsIgnoreCase(simpleValue);
	}

	public static java.util.List<java.lang.String> getOptionCollection(){
		java.util.List<java.lang.String> optionCollection = new java.util.ArrayList<>();
		optionCollection.add("WEB");
		optionCollection.add("MOBILE");
		return optionCollection;
	}
}