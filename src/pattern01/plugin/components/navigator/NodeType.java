	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Jun 09 06:48:16 UYT 2016
	* Creator: rlomez
	**/
package pattern01.plugin.components.navigator;

public enum NodeType {
	UNDEFINED,CLASS,PATTERNINSTANCE,RELATEDFILES,RELATEDFILE,FACES,SELECTION,REGISTER,DIALOG;

	public static String toString(NodeType nodetype){
		String nodetypestr = "";
			switch(nodetype){
			case UNDEFINED:
				nodetypestr = "UNDEFINED";
				break;
			case CLASS:
				nodetypestr = "CLASS";
				break;
			case PATTERNINSTANCE:
				nodetypestr = "PATTERNINSTANCE";
				break;
			case RELATEDFILES:
				nodetypestr = "RELATEDFILES";
				break;
			case RELATEDFILE:
				nodetypestr = "RELATEDFILE";
				break;
			case FACES:
				nodetypestr = "FACES";
				break;
			case SELECTION:
				nodetypestr = "SELECTION";
				break;
			case REGISTER:
				nodetypestr = "REGISTER";
				break;
			case DIALOG:
				nodetypestr = "DIALOG";
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
			case "PATTERNINSTANCE":
				nodetype = NodeType.PATTERNINSTANCE;
				break;
			case "RELATEDFILES":
				nodetype = NodeType.RELATEDFILES;
				break;
			case "RELATEDFILE":
				nodetype = NodeType.RELATEDFILE;
				break;
			case "FACES":
				nodetype = NodeType.FACES;
				break;
			case "SELECTION":
				nodetype = NodeType.SELECTION;
				break;
			case "REGISTER":
				nodetype = NodeType.REGISTER;
				break;
			case "DIALOG":
				nodetype = NodeType.DIALOG;
				break;
		}
		return nodetype;
	}

}
