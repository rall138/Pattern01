package pattern01;
import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import com.rldevel.persistence.Table;


public class CrudApply extends Task{
	
	private final BuildFileRule buildfilerule = new BuildFileRule();
	private static String propertyFilePath = null;
	
	public CrudApply(String className){
		//Mediante esta propiedad se settea el archivo de propiedades para ant.
		getProject().setProperty("file",propertyFilePath);
		//Le decimos a ant cual va a ser el archivo donde se encuentran las tareas.
		buildfilerule.configureProject("CrudBuild.xml");		
	}
	
	//Para settear el archivo de configuración que contiene las folders necesarias
	public static void setPropertyFilePath(String path){
		propertyFilePath = path;
	}
	
	public void execute(){
		
//		String entityfolder="", dataAccessObjectfolder="", servicefolder="", controllerfolder="", 
//				templatesfolder="";
//		entityfolder = getProject().getProperty("entityfolder");
//		dataAccessObjectfolder = getProject().getProperty("dataAccessObjectfolder");
//		servicefolder = getProject().getProperty("servicefolder");
//		controllerfolder = getProject().getProperty("controllerfolder");
//		templatesfolder = getProject().getProperty("templatesfolder");
		
		getProject().setProperty("className", table.getTable_name());
		buildfilerule.executeTarget("entityGen");
		getProject().setProperty("className", "DAO" + table.getTable_name());		
		buildfilerule.executeTarget("daoGen");
		getProject().setProperty("className", "Service" + table.getTable_name());		
		buildfilerule.executeTarget("serviceGen");
		getProject().setProperty("className", "ManagerBean" + table.getTable_name());		
		buildfilerule.executeTarget("controllerGen");
		

	}

}
