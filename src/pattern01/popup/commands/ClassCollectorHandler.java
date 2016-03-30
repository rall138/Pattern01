package pattern01.popup.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.internal.Workbench;

import pattern01.FileParser;

@SuppressWarnings("restriction")
public class ClassCollectorHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelectionService selectionService = Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();
		ISelection selection  = selectionService.getSelection("org.eclipse.ui.views.ResourceNavigator");
		if (selection instanceof IStructuredSelection){
			Object element = ((IStructuredSelection)selection).getFirstElement();
			if (element instanceof IAdaptable){
				IFolder folder  = ((IAdaptable)element).getAdapter(IFolder.class);
				FileParser parser = new FileParser();
				parser.collectClasses(folder);
				
			}
		}
		return null;
	}

	
}
