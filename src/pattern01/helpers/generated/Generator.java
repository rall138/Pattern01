package pattern01.helpers.generated;

	/**
	* Generated enum class via ClassGenerator.xml
	* Creation date: Sun Jun 12 15:19:05 UYT 2016
	* Creator: rlomez
	**/
public enum Generator {
UNDEFINED,	WEB,	MOBILE;
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

	public static java.util.List<java.lang.String> getOptionCollection(){
		java.util.List<java.lang.String> optionCollection = new java.util.ArrayList<>();
		optionCollection.add("WEB");
		optionCollection.add("MOBILE");
		return optionCollection;
	}
}
