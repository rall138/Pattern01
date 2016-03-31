package pattern01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Platform;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClassCollectorHelper {

	private static final String strMethodPattern = "(public) (\\s*\\w*) (get\\w++)";
	private static final String strPackagePattern = "(package) (.*?;)";
	private List<ClassCollectorHelper.Class> classCollection = new ArrayList<>();

	
	public ClassCollectorHelper(){
//		URL url = Platform.getInstanceLocation().getURL();
//		File file = new File(url.getPath());
//		if (file.exists()){
//			recursiveClassCollector(file);
//		}
	}
	
	
	public void collectClasses(String packageURI) throws URISyntaxException{
		URI folderURI = new URI(packageURI);
		File file = Paths.get(folderURI).toFile();
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
					
					ClassCollectorHelper.Class class_def = new Class(packageName, list[index].getName().replace(".java", ""));
					//Coincidencias de métodos
					while(matcher.find()){
						Method method = new Method(matcher.group(3), matcher.group(2));
						class_def.getMethodCollection().add(method);
					}
					classCollection.add(class_def);					

				}catch(IOException ex){
					ex.printStackTrace();
				}				
			}
			toJsonFile(file);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void toJsonFile(File projectFolder){
		JSONObject parentObject = new JSONObject(), 
				classObject = new JSONObject(), 
				methodDescItem;
		
		JSONArray classesArray = new JSONArray(), 
				methodsArray;
		
		List<ClassCollectorHelper.Class> list = getClassCollection();
		//Listado de clases
		for(int index = 0; index < list.size(); index++){
			classObject.put("packageName", list.get(index).getPackageName());
			classObject.put("className", list.get(index).getClassName());
			methodsArray = new JSONArray();
			for(Method method : list.get(index).getMethodCollection()){
				methodDescItem = new JSONObject();
				methodDescItem.put("methodName", method.methodName);
				methodDescItem.put("returntype", method.returnType);
				methodsArray.add(methodDescItem);
			}
			classObject.put("methods", methodsArray);
			classesArray.add(classObject);
		}
		parentObject.put("classes", classesArray);
		
		File auxProjectFolder = getJSONDBFile();
		
		try {
			Writer wr = new FileWriter(auxProjectFolder); 
			parentObject.writeJSONString(wr);
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
		
		if (operative_system.compareTo(Platform.OS_LINUX) == 0){
			fileLoc = "/yogurt.txt";
		}else if(operative_system.compareTo(Platform.OS_WIN32) == 0){
			fileLoc = "\\yogurt.txt";
		}

		File auxProjectFolder = 
				new File(Platform.getInstanceLocation().getURL().getPath() + fileLoc);
		return auxProjectFolder;
	}
	
	
	//Es usada cuando inicializamos la vista de Pattern Navigator.
	@SuppressWarnings("unchecked")
	public void classCollectionFromJsonFile(){
		File auxProjectFolder = getJSONDBFile();
		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject)parser.parse(new FileReader(auxProjectFolder));
			JSONArray classes = (JSONArray) object.get("classes");
			for(JSONObject jsonClass : (ArrayList<JSONObject>)classes){
				ClassCollectorHelper.Class class_def = new Class((String)jsonClass.get("packageName"), 
						(String)jsonClass.get("className"));
				JSONArray methods = (JSONArray)jsonClass.get("methods");
				for(JSONObject jsonMethod : (ArrayList<JSONObject>)methods){
					class_def.getMethodCollection().add(new Method((String)jsonMethod.get("methodName"), 
							(String)jsonMethod.get("returnType")));
				}
				this.classCollection.add(class_def);
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public List<ClassCollectorHelper.Class> getClassCollection(){
		return this.classCollection;
	}
	
	//Clases auxiliares
	
	class Class{
		private String packageName;
		private String className;
		private List<Method> methodCollection = new ArrayList<>();
		
		public Class(String packageName, String className){
			this.packageName = packageName;
			this.className = className;
		}

		public String getClassName() {
			return className;
		}

		public String getPackageName() {
			return packageName;
		}

		public List<Method> getMethodCollection() {
			return methodCollection;
		}
		
	}

	class Method{
		private String methodName;
		private String returnType;
		
		public Method(String methodName, String returnType){
			this.methodName = methodName;
			this.returnType = returnType;
		}

		public String getMethodName() {
			return methodName;
		}

		public String getReturnType() {
			return returnType;
		}
	}
}
