package pattern01.helpers.generated;

	/**
	* Generated enum class via ClassGenerator.xml
	* Creation date: Thu May 12 17:44:59 GFT 2016
	* Creator: rlomez
	**/
public enum Generator {
	generator2,
	generator1;

	public java.lang.String getValueDescription(Generator description){
		switch(description){
			case generator2:
				return "Mobile";
			case generator1:
				return "Web";
			default:
				return "";
		}
	}
}
