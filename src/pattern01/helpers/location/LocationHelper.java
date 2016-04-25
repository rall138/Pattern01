package pattern01.helpers.location;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class LocationHelper {
	
	public enum RL_PLUGIN {PATTERN01};
	
	public static String getActualProjectPath(){
		return System.getProperty("user.dir");
	}
	
	public static String getPluginPath(RL_PLUGIN pluginName){
		Bundle bundle = null;
		switch(pluginName){
		case PATTERN01:
			bundle = Platform.getBundle("Pattern01");
		}
		return bundle.getLocation();
	}

}