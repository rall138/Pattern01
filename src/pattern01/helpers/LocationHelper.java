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

}