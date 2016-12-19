
package pattern01.helpers.generated;

	/**
	* Generated enum class via ClassGenerator.xml
	* Creation date: Sun Dec 18 19:38:30 UYST 2016
	* Creator: rlomez
	**/
public enum PatternType {UNDEFINED, REGISTER, WORKWITH, WIZZARD;

	public static java.lang.String getValueDescription(PatternType description){
		switch(description){
			case REGISTER:
				return "REGISTER";
			case WORKWITH:
				return "WORKWITH";
			case WIZZARD:
				return "WIZZARD";
			default:
				return "";
		}
	}

	public static boolean fullyQualifiedComparer(String fullyQualifiedValue, String simpleValue){
		return fullyQualifiedValue.replace("pattern01.helpers.generated","").replace("PatternType","").replace(".","").equalsIgnoreCase(simpleValue);
	}

	public static java.util.List<java.lang.String> getOptionCollection(){
		java.util.List<java.lang.String> optionCollection = new java.util.ArrayList<>();
		optionCollection.add("REGISTER");
		optionCollection.add("WORKWITH");
		optionCollection.add("WIZZARD");
		return optionCollection;
	}
}