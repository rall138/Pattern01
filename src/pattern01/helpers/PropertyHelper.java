package pattern01.helpers;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.tools.ant.BuildFileRule;

import pattern01.helpers.CommonPathFix.PATH_NAME;

public class PropertyHelper {

	private BuildFileRule bfr = new BuildFileRule();
	private Properties prop = new Properties();
	private LoggerThread log = new LoggerThread();
	
	public void impactPropertiesOnFile(String propertyFilePath, String properties){
		log.writeSingleMessage("<<< Generating property file (Custom.properties) >>>");
		this.impactPropertiesOnFile(propertyFilePath, properties, true);
	}

	public void impactPropertiesOnFile(String propertyFilePath, String properties,
			boolean noLog){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		bfr.getProject().setNewProperty("filename", propertyFilePath);
		bfr.getProject().setNewProperty("message", properties);
		bfr.executeTarget("fileRelative");
	}
	
	public String getProperty(String propertyFilePath, String propertyName){
		try {
			File file = new File(propertyFilePath);
			if (!file.exists()){
				file.createNewFile();
			}
			java.io.FileInputStream input = new java.io.FileInputStream(file.getAbsolutePath());
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(propertyName);
	}
}