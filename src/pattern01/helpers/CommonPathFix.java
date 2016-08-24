package pattern01.helpers;

import java.io.File;
import java.net.URI;

/***
 * Nasty work-around for getting location of some necessary files
 * @author rlomez
 *
 */
public class CommonPathFix {

	public enum PATH_NAME{CLASSGENERATOR_XML, WORKSPACE_LOC, 
		PATTERNDEFINITION_XML, BINFOLDER, 
		CUSTOMPROPERTIES_PROPERTIES, ICONFOLDER,
		CUSTOMVALUESDEFINITION, DEFAULTWWDEFINITION, MANIFEST_XML, 
		JFACEDIALOG_TEMPLATE};
	
	public static URI getHardCodedPath(PATH_NAME path_name){
		File file = null;
		String os = System.getProperty("os.name");
		switch (path_name) {
			case ICONFOLDER:
				file = new File(getHardCodedPath(PATH_NAME.WORKSPACE_LOC).getPath()+
					barFix(os, "/icons"));
				break;
			case PATTERNDEFINITION_XML:
				file = new File(getHardCodedPath(PATH_NAME.WORKSPACE_LOC).getPath()+				
					barFix(os, "/generalconfig/PatternDefinition.xml"));
			break;
			case CLASSGENERATOR_XML:
				file = new File(getHardCodedPath(PATH_NAME.WORKSPACE_LOC).getPath()+
					barFix(os, "/src/pattern01/helpers/definitiongen/ClassGenerator.xml"));
				break;
			case WORKSPACE_LOC:
				if (os.indexOf("Windows")>=0){
					file = new File("E:\\EclipseWorkspace\\Pattern01");
				}else{
					file = new File("/home/ricardo/Documents/Eclipse commiters workspace/Pattern01");
				}
				break;
			case BINFOLDER:
				file = new File(getHardCodedPath(PATH_NAME.WORKSPACE_LOC).getPath()+
					barFix(os, "/bin"));
				break;
			case CUSTOMPROPERTIES_PROPERTIES:
				file = new File(getHardCodedPath(PATH_NAME.WORKSPACE_LOC).getPath()+
					barFix(os, "/src/pattern01/helpers/generated/Custom.properties"));
				break;
			case CUSTOMVALUESDEFINITION:
				file = new File(getHardCodedPath(PATH_NAME.WORKSPACE_LOC).getPath()+
						barFix(os, "/generalconfig/CustomValuesDefinition.xml"));
				break;
			case DEFAULTWWDEFINITION:
				file = new File(getHardCodedPath(PATH_NAME.WORKSPACE_LOC).getPath()+
						barFix(os, "/generalconfig/DefaultPatternDefinition.xml"));
				break;
			case MANIFEST_XML:
				file = new File(getHardCodedPath(PATH_NAME.WORKSPACE_LOC).getPath()+
						barFix(os, "/plugin.xml"));
			case JFACEDIALOG_TEMPLATE:
				file = new File(getHardCodedPath(PATH_NAME.WORKSPACE_LOC).getPath()+
						barFix(os, "/generalconfig/templates/JFaceDialog.txt"));
		} 
		return file.toURI();
	}
	
	private static String barFix(String os, String relative_path){
		if(os.indexOf("Windows")>=0){
			relative_path = relative_path.replace("/", "\\");
		}
		return relative_path;
	}
	
}
