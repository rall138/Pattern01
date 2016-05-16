package pattern01.helpers.generated;

	/**
	* Generated enum class via ClassGenerator.xml
	* Creation date: Mon May 16 12:35:08 GFT 2016
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
