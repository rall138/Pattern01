package pattern01.plugin.components.navigator;

public enum NodeType {
	patternInstanceType, classType, wwType, facesType, faceType, referencedFilesType, referencedFileType, undefined;
	
	static public String toString(NodeType nodeType){
		String typeToString = "";
		switch (nodeType) {
		case patternInstanceType:
			typeToString = "patternInstance";
			break;
		case classType:
			typeToString = "class";
			break;
		case wwType:
			typeToString = "workwith";
			break;
		case facesType:
			typeToString = "faces";
			break;
		case faceType:
			typeToString = "face";
			break;
		case referencedFilesType:
			typeToString = "referencedFiles";
			break;
		case referencedFileType:
			typeToString = "referencedFile";
			break;
		case undefined:
			typeToString = "undefinded";
			break;
		}
		return typeToString;
	}
}
