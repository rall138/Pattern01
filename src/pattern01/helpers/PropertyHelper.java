package pattern01.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.tools.ant.BuildFileRule;

import pattern01.helpers.CommonPathFix.PATH_NAME;

public class PropertyHelper {

	private BuildFileRule bfr = new BuildFileRule();
	private Properties prop = new Properties();
	private LoggerThread log = new LoggerThread();
	
	public void impactPropertiesOnFile(String propertyFilePath, String properties){
		log.writeSingleMessage("Generating property file");
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		bfr.getProject().setNewProperty("filename", propertyFilePath);
		bfr.getProject().setNewProperty("message", properties);
		bfr.executeTarget("fileRelative");
	}
	
	public String getProperty(String propertyFilePath, String propertyName){
		try {
			java.io.FileInputStream input = new java.io.FileInputStream(propertyFilePath);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(propertyName);
	}

}