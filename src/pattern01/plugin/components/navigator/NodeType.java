	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Jun 09 14:44:45 GFT 2016
	* Creator: rlomez
	**/
package pattern01.plugin.components.navigator;

public enum NodeType {
	UNDEFINED,CLASS,

	public static String toString(NodeType nodetype){
		String nodetypestr = "";
			switch(nodetype){
			case UNDEFINED:
				nodetypestr = "UNDEFINED";
				break;
			case CLASS:
				nodetypestr = "CLASS";
				break;
		}
		return nodetypestr;
	}

	public static NodeType nodeTypeFromString(String nodetypestr){
		NodeType nodetype = NodeType.UNDEFINED;
			switch(nodetypestr.toUpperCase()){
			case "UNDEFINED":
				nodetype = NodeType.UNDEFINED;
				break;
			case "CLASS":
				nodetype = NodeType.CLASS;
				break;
		}
		return nodetype;
	}

}
