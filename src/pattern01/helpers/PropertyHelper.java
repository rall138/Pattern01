package pattern01.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.tools.ant.BuildFileRule;

import pattern01.helpers.CommonPathFix.PATH_NAME;

public class PropertyHelper {

	private BuildFileRule bfr = new BuildFileRule();
	private Map<String, String> propertyValue = new HashMap<>();
	private Properties prop = new Properties();
	
	public PropertyHelper(){}
	
	public void putProperty(String propertyName, String propertyValue){
		this.propertyValue.put(propertyName, propertyValue);
	}
	
	public void impactPropertiesOnFile(String propertyFilePath){
		LoggerThread log = new LoggerThread();
		log.writeSingleMessage("Generating property file");
		
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		log.writeSingleMessage(bfr.getProject().getBaseDir().getPath());
		
		bfr.getProject().setNewProperty("filename", propertyFilePath);
		
		
		CustomStringBuilder builder = new CustomStringBuilder();
		for(Map.Entry<String, String> entry : propertyValue.entrySet()){
			builder.appendLn(entry.getKey()+"="+entry.getValue());
		}
		bfr.getProject().setNewProperty("message", builder.toString());
		bfr.executeTarget("fileRelative");
	}
	
	public String getProperty(String propertyFilePath, String propertyName){
		try {
			URI uri = new URI(propertyFilePath);
			System.out.println("Path obtenido: "+uri.getPath());
			prop.load(new FileInputStream(uri.getPath()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return prop.getProperty(propertyName);
	}

}