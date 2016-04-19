package pattern01.helpers;

import java.util.HashMap;
import java.util.Map;
import org.apache.tools.ant.BuildFileRule;

public class PropertyHelper {

	private BuildFileRule bfr = new BuildFileRule();
	private Map<String, String> propertyValue = new HashMap<>();
	
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

}