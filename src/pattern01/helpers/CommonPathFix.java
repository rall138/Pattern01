package pattern01.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/***
 * Nasty work-around for getting location of some necessary files
 * @author rlomez
 *
 */
public class CommonPathFix {

	public enum PATH_NAME{CLASSGENERATOR_XML, WORKSPACE_LOC, 
		PATTERNDEFINITION_XML, BINFOLDER, CUSTOMPROPERTIES_PROPERTIES, ICONFOLDER};
	
	/*
	public static URI getHardCodedPath(PATH_NAME path_name){
		URI uri = null;
		try{
			switch (path_name) {
				case PATTERNDEFINITION_XML:
					uri = new URI("file:///E:/EclipseWorkspace/"+
						"Pattern01/GeneralConfig/PatternDefinition.xml");
				break;
				case CLASSGENERATOR_XML:
					uri = new URI("file:///E:/EclipseWorkspace/"+
						"Pattern01/src/pattern01/"+
						"helpers/definitiongen/ClassGenerator.xml");
				break;
				case WORKSPACE_LOC:
					uri = new URI("file:///E:/EclipseWorkspace/"+
							"Pattern01");					
				break;
				case BINFOLDER:
					uri = new URI("file:///E:/EclipseWorkspace/"+
							"Pattern01/bin");					
				break;
				case CUSTOMPROPERTIES_PROPERTIES:
					uri = new URI("file:///E:/EclipseWorkspace/"+
							"Pattern01/src/pattern01/"+
							"helpers/generated/Custom.properties");
				break;
			}
		}catch(MalformedURIException e){
			e.printStackTrace();
		}
		return uri;
	}
	*/
	
	public static URI getHardCodedPath(PATH_NAME path_name){
		URI uri = null;
		File file = null;
		switch (path_name) {
			case ICONFOLDER:
				file = new File("/home/ricardo/Documents/Eclipse commiters workspace/Pattern01/icons");
				break;
			case PATTERNDEFINITION_XML:
				file = new File("/home/ricardo/Documents/Eclipse commiters workspace/Pattern01"+
					"/GeneralConfig/PatternDefinition.xml");
			break;
			case CLASSGENERATOR_XML:
				file = new File("/home/ricardo/Documents/Eclipse commiters workspace/Pattern01"+
					"/src/pattern01/helpers/definitiongen/ClassGenerator.xml");
				break;
			case WORKSPACE_LOC:
				file = new File("/home/ricardo/Documents/Eclipse commiters workspace/Pattern01");					
				break;
			case BINFOLDER:
				file = new File("/home/ricardo/Documents/Eclipse commiters workspace/Pattern01/bin");					
				break;
			case CUSTOMPROPERTIES_PROPERTIES:
				file = new File("/home/ricardo/Documents/Eclipse commiters workspace/Pattern01"+
						"/src/pattern01/helpers/generated/Custom.properties");
				break;
		}
		if (file.exists()){
			uri = file.toURI();
		}
		return uri;
	}
	
}
