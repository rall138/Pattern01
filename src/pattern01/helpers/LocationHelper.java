package pattern01.helpers;

import java.io.File;
import java.net.URI;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

@SuppressWarnings("restriction")
public class LocationHelper {
	
	public enum RL_PLUGIN {PATTERN01};
	private static String project_folder_path = "";
	
	public static String getSelectedProjectPath(){
		IPath projectPath = null;
		ISelectionService selectionService = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getSelectionService();
				
		@SuppressWarnings("deprecation")
		ISelection selection  = selectionService.getSelection(IPageLayout.ID_RES_NAV);		
		Object element = ((IStructuredSelection)selection).getFirstElement();
		if (element instanceof IAdaptable){
			projectPath = ((Project)element).getLocation();
			System.out.println("Path obtenido: "+projectPath.toString());
		}
		return projectPath.toString();
	}
	
	public static String getActiveWorkSpace(){
		return Platform.getInstanceLocation().getURL().getPath();
	}
	
	public static String getPluginPath(RL_PLUGIN pluginName){
		Bundle bundle = null;
		switch(pluginName){
		case PATTERN01:
			bundle = Platform.getBundle("Pattern01");
		}
		return bundle.getLocation();
	}
	
	public static URI fromFileToURI(File fileinstance){
		return fileinstance.toURI();
	}
	
	/* Necesary class and folders on active project */
	
	public static String searchPatternFolderPath(String projectFolderPath){
		String patternFolderPath = "";
		File projectFolder = new File(projectFolderPath);
		int index = 0;
		boolean itemFound = false;
		while (index < projectFolder.listFiles().length && !itemFound){
			if (projectFolder.listFiles()[index].getName().equalsIgnoreCase("patternfolder")){
				patternFolderPath = projectFolder.listFiles()[index].getAbsolutePath();
				itemFound = true;
			}else{
				index++;
			}
		}
		return patternFolderPath;
	}
	
	public static String searchClassInstancesFile(String patternFolderPath){
		String classInsancesURItoString = "";
		File projectFolder = new File(patternFolderPath);
		int index = 0;
		boolean itemFound = false;
		while (projectFolder.exists() && index < projectFolder.listFiles().length && !itemFound){
			if (projectFolder.listFiles()[index].getName().equalsIgnoreCase("classinstances.xml") && 
					!projectFolder.listFiles()[index].isDirectory()){
				classInsancesURItoString = projectFolder.listFiles()[index].getAbsolutePath();
				itemFound = true;
			}else{
				index++;
			}
		}
		return classInsancesURItoString;
	}
	
	public static String searchClassPatternInstanceByClassName(String className, String projectFolderPath){
		File classPatternInstance = null;
		File patternFolder = new File(searchPatternFolderPath(projectFolderPath));
		if (patternFolder.exists()){
			classPatternInstance = new File(patternFolder.getAbsolutePath()+
					System.getProperty("file.separator")+
					className+"Instance.xml"); 
		}
		return classPatternInstance == null ? "" : classPatternInstance.getAbsolutePath();
	}

	public static String getProject_folder_path() {
		return project_folder_path;
	}

	public static void setProject_folder_path(String pattern_folder_path) {
		LocationHelper.project_folder_path = pattern_folder_path;
	}
	
	/* Necesary class and folders on active project */
	

}