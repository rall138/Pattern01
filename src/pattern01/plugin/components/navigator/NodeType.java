
	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Tue Apr 04 20:36:16 UYT 2017
	* Creator: rlomez
	**/
package pattern01.plugin.components.navigator;

public enum NodeType {
	UNDEFINED,PACKAGE,CLASS,COMMANDBUTTON,DATATABLE,PANEL,FORM,TEXT,PROPERTY,PANELGRID,PATTERNINSTANCE,MESSAGES,COLUMN;

	public static String toString(NodeType nodetype){
		String nodetypestr = "";
		switch(nodetype){
			case UNDEFINED:
				nodetypestr = "UNDEFINED";
				break;
			case PACKAGE:
				nodetypestr = "PACKAGE";
				break;
			case CLASS:
				nodetypestr = "CLASS";
				break;
			case COMMANDBUTTON:
				nodetypestr = "COMMANDBUTTON";
				break;
			case DATATABLE:
				nodetypestr = "DATATABLE";
				break;
			case PANEL:
				nodetypestr = "PANEL";
				break;
			case FORM:
				nodetypestr = "FORM";
				break;
			case TEXT:
				nodetypestr = "TEXT";
				break;
			case PROPERTY:
				nodetypestr = "PROPERTY";
				break;
			case PANELGRID:
				nodetypestr = "PANELGRID";
				break;
			case PATTERNINSTANCE:
				nodetypestr = "PATTERNINSTANCE";
				break;
			case MESSAGES:
				nodetypestr = "MESSAGES";
				break;
			case COLUMN:
				nodetypestr = "COLUMN";
				break;
		}
		return nodetypestr;
	}
}