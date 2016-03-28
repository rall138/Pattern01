package pattern01;

import java.util.List;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.Platform;

public class FileParser {

	private static final String strPattern = "(public \\d+ \\s* get|is\\d+)";
	private HashMap<String, ArrayList<E>>
	
	public FileParser(){
		File file;
		URL url = Platform.getInstanceLocation().getURL();
		if (url != null){
			if (file.isDirectory()){
				
			}
		}
	}
	
	private void recursiveClassCollector(File file){
		if (file != null && file.isDirectory()){
			
		}
	}
	
	class ClassMethods{
		
		private String className;
		private HashMap<String, Object> method = new HashMap<>(); //ClassName, return type
		
		public ClassMethods(String className, String methodSign, Object returnType){
			this.className = className;
			method.put(methodSign, returnType);
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

	}
}
