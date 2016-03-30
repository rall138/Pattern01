package pattern01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Platform;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("restriction")
public class FileParser {

	private static final String strMethodPattern = "(public) (\\s*\\w*) (get\\w++)";
	private static final String strPackagePattern = "(package) (\\s*\\w*)";
	private List<ClassMethods> classMethodsCollection = new ArrayList<>();

	
	public FileParser(){
//		URL url = Platform.getInstanceLocation().getURL();
//		File file = new File(url.getPath());
//		if (file.exists()){
//			recursiveClassCollector(file);
//		}
	}
	
	
	public void collectClasses(IFolder folder){
		URI folderURI = ((Folder)folder).getLocationURI();
		File file = new File(folderURI);
		if (file.exists() && file.isDirectory()){
			File[] list = file.listFiles();
			for(int index = 0; index < list.length; index++){
				try{
					BufferedReader reader = new BufferedReader(new FileReader(list[index]));
					String allCode = "";
					String line;
					while((line = reader.readLine())!= null){
						allCode += line;
					}
					reader.close();
					
					//Obtenemos los metodos p�blicos
					Pattern pattern = Pattern.compile(strMethodPattern);
					Matcher matcher = pattern.matcher(allCode);
					
					//Obtenemos el package name
					Pattern pattern02 = Pattern.compile(strPackagePattern);
					Matcher matcher02 = pattern02.matcher(allCode);
					
					
					String packageName = "";
					while(matcher02.find()){
						packageName = matcher02.group(2);
					}
					
					while(matcher.find()){
						ClassMethods classMethods = 
								new ClassMethods(packageName,
										list[index].getName().replace(".java", ""), 
										matcher.group(2), 
										matcher.group(3));
						classMethodsCollection.add(classMethods);

					}
//					String auxPath = file.getAbsolutePath().
//							substring(0 ,file.getAbsolutePath().indexOf("/"));
					toJsonFile(file);
				}catch(IOException ex){
					
				}				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void toJsonFile(File projectFolder){
		JSONObject obj = new JSONObject(), item;
		JSONArray array;
		
		List<ClassMethods> list = getClassMethodsCollection();
		for(int index = 0; index < list.size(); index++){
			obj.put("packageName", list.get(index).getPackageName());
			obj.put("className", list.get(index).getClassName());
			array = new JSONArray();
			for(Map.Entry<String, Object> set : list.get(index).getMethod().entrySet()){
				item = new JSONObject();
				item.put("returntype", set.getKey());
				item.put("method", set.getValue());
				array.add(item);
			}
			obj.put("methods", array);
		}
		
		File auxProjectFolder = getJSONDBFile();
		
		try {
			Writer wr = new FileWriter(auxProjectFolder); 
			obj.writeJSONString(wr);
			wr.flush();
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private File getJSONDBFile(){
		String operative_system = Platform.getOS();
		String fileLoc = "";
		
		if (operative_system == Platform.OS_LINUX){
			fileLoc = "/yogurt.txt";
		}else if(operative_system == Platform.OS_WIN32){
			fileLoc = "\\yogurt.txt";
		}

		File auxProjectFolder = 
				new File(Platform.getInstanceLocation().getURL().getPath() + fileLoc);
		return auxProjectFolder;
	}
	
	private void generateClasses(){
		File auxProjectFolder = getJSONDBFile();
		try{
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(auxProjectFolder));
			while((line = br.readLine())!=null){
				
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
/*
	public void recursiveClassCollector(File file){
		int index = 0;
		if (file.isDirectory()){
			recursiveClassCollector(file.listFiles()[index]);
			index++;
		}else if(file.getName().endsWith(".java")){
			try{
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String allCode = "";
				String line;
				while((line = reader.readLine())!= null){
					allCode += line;
				}
				reader.close();
				
				Pattern pattern = Pattern.compile(strPattern);
				Matcher matcher = pattern.matcher(allCode);
				if (matcher.matches()){
					while(matcher.find()){
						ClassMethods classMethods = 
								new ClassMethods(file.getName().replace(".java", ""), 
										(String)matcher.group(2),matcher.group(3));
						classMethodsCollection.add(classMethods);
					}
				}
			}catch(IOException ex){
				
			}
		}
	}
	*/
	
		
	public List<ClassMethods> getClassMethodsCollection() {
		return classMethodsCollection;
	}

	class ClassMethods{
		
		private String packageName;
		private String className;
		private HashMap<String, Object> method = new HashMap<>(); //ClassName, return type
		
		public ClassMethods(String packageName, String className, 
				String methodSign, Object returnType){
			this.packageName = packageName;
			this.className = className;
			this.method.put(methodSign, returnType);
		}

		public String getClassName() {
			return className;
		}

		public String getPackageName() {
			return packageName;
		}

		public HashMap<String, Object> getMethod() {
			return method;
		}

	}
}
