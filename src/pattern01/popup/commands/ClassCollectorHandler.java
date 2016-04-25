package pattern01.popup.commands;

import java.net.URISyntaxException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.internal.Workbench;

@SuppressWarnings("restriction")
public class ClassCollectorHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelectionService selectionService = Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();
		ISelection selection  = selectionService.getSelection("org.eclipse.jdt.ui.PackageExplorer");
		if (selection instanceof IStructuredSelection){
			Object element = ((IStructuredSelection)selection).getFirstElement();
			if (element instanceof IAdaptable){
				
				IPackageFragment fragment = (IPackageFragment)element;
				IPath projectPath = fragment.getJavaProject().getProject().getLocation();
				String fixPath = "";
				String elementName = fragment.getPath().toString();
				if(Platform.getOS().compareTo(Platform.OS_LINUX) == 0){
					fixPath = "file://";
				}else if(Platform.getOS().compareTo(Platform.OS_WIN32) == 0){
					fixPath = "file:/";
				}
				elementName = elementName.replace("/" + fragment.getJavaProject().getElementName(), "");
				fixPath += projectPath.toString() + elementName; 
				try{
					ClassCollectorHelper parser = new ClassCollectorHelper();
					parser.collectClasses(fixPath);
				}catch(URISyntaxException e){
					e.printStackTrace();
				}
				
			}
		}
		return null;
	}

	
}
