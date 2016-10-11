
	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Tue Oct 11 16:11:48 GFT 2016
	* Creator: rlomez
	**/
package pattern01.plugin.components.navigator;

public enum NodeType {	UNDEFINED,CLASS,PATTERNINSTANCE,RELATEDFILES,RELATEDFILE,FACES,SELECTION,REGISTER,DIALOG;


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
				nodetypestr ="PATTERNINSTANCE";
				break;
			case RELATEDFILES:
				nodetypestr ="RELATEDFILES";
				break;
			case RELATEDFILE:
				nodetypestr ="RELATEDFILE";
				break;
			case FACES:
				nodetypestr ="FACES";
				break;
			case SELECTION:
				nodetypestr ="SELECTION";
				break;
			case REGISTER:
				nodetypestr ="REGISTER";
				break;
			case DIALOG:
				nodetypestr ="DIALOG";
				break;
		}
		return nodetypestr;
	}
}