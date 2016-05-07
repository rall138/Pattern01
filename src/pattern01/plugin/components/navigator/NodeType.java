package pattern01.plugin.components.navigator;

public enum NodeType {
	classType, wwType, facesType, faceType, referencedFilesType, referencedFileType;
	
	static public String toString(NodeType nodeType){
		String typeToString = "undefinded";
		switch (nodeType) {
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
		}
		return typeToString;
	}
}
