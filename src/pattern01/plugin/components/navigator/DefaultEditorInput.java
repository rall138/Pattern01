package pattern01.plugin.components.navigator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class DefaultEditorInput implements IEditorInput{

	private org.eclipse.swt.widgets.Tree treeInstance = null;	
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "Test";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return null;
	}

	public org.eclipse.swt.widgets.Tree getInstanceTree(){
		return this.treeInstance;
	}

	public void setInstanceTree(org.eclipse.swt.widgets.Tree treeInstance){
		this.treeInstance = treeInstance;
	}
	
}
