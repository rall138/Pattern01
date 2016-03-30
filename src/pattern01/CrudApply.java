package pattern01;
import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

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
		
		
		buildfilerule.executeTarget("entityGen");
		buildfilerule.executeTarget("daoGen");
		buildfilerule.executeTarget("serviceGen");
		buildfilerule.executeTarget("controllerGen");
		

	}

}
