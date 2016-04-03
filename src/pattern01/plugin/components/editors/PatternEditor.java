package pattern01.plugin.components.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class PatternEditor extends EditorPart{
	
	private IEditorInput input;
	private IEditorSite site;

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyListener(IPropertyListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IWorkbenchPartSite getSite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getTitleImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitleToolTip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePropertyListener(IPropertyListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEditorInput getEditorInput() {
		
		// TODO Cambiar para que se devuelva el input recibido.
		IEditorInput input = new TreeviewEditorInput();
		return input;
	}

	@Override
	public IEditorSite getEditorSite() {
		return this.site;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if(!(input instanceof TreeviewEditorInput)){
			throw new RuntimeException("Unexpected input on editor part");
		}
		setSite(site);
		setInput(input);
	}
}
