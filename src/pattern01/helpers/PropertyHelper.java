package pattern01.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.tools.ant.BuildFileRule;
import org.eclipse.core.runtime.Platform;

public class PropertyHelper {

	private BuildFileRule bfr = new BuildFileRule();
	private Map<String, String> propertyValue = new HashMap<>();
	private Properties prop = new Properties();
	
	public PropertyHelper(){}
	
	public void putProperty(String propertyName, String propertyValue){
		this.propertyValue.put(propertyName, propertyValue);
	}
	
	public void impactPropertiesOnFile(){
		bfr.configureProject("ClassGenerator.xml");
		bfr.getProject().setNewProperty("filename", "generated/Custom.properties");
		
		LoggerThread log = new LoggerThread();
		log.addMessage("Generating property file");
		
		CustomStringBuilder builder = new CustomStringBuilder();
		for(Map.Entry<String, String> entry : propertyValue.entrySet()){
			builder.appendLn(entry.getKey()+"="+entry.getValue());
		}
		
		log.run();

		bfr.getProject().setNewProperty("message", builder.toString());
		bfr.executeTarget("fileRelative");
	}
	
	public String getProperty(String propertyName){
		try {
			URI uri = new URI("file:///");
			prop.load(new FileInputStream(uri.getPath()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return prop.getProperty(propertyName);
	}

}