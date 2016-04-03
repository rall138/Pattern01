package pattern01.plugin.components.navigator;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

import pattern01.ClassCollectorHelper;
import pattern01.plugin.components.editors.PatternEditor;

public class PatternNav extends ViewPart implements Observer{

	private Action searchPatternAction;
	TreeViewer tviewer;
	
	@Override
	public void createPartControl(Composite parent) {
		tviewer = new TreeViewer(parent);
		menuBuilder();
		
		searchPatternAction = new Action("search..") {
			public void run(){
				//Aca va el c�digo de la acci�n
			}
		};
		searchPatternAction.setImageDescriptor(getImageDescriptor("lupa.png"));
		createActionBar();

		Tree tree = tviewer.getTree();
		
		//Tree tree = new Tree(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		ClassCollectorHelper classCollectorHelper = new ClassCollectorHelper();
		classCollectorHelper.classCollectionFromJsonFile();
		ArrayList<ClassCollectorHelper.Class> classCollection = 
				new ArrayList<>(classCollectorHelper.getClassCollection());
		
		TreeItem item;
		//Image img;
		for(ClassCollectorHelper.Class class_def : classCollection){
			item = new TreeItem(tree, 0);
			item.setText(class_def.getClassName());
			item.setImage(getImage("class_obj.png"));
		}
		
	}
	
	
	private void menuBuilder(){
		MenuManager mgr = new MenuManager();
		mgr.setRemoveAllWhenShown(true);
		mgr.addMenuListener(new IMenuListener() {
			
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				populateMenu(manager);
				
			}
		});
		
		//Agregameos el menú al viewpart
		Menu menu = mgr.createContextMenu(tviewer.getControl());
		tviewer.getControl().setMenu(menu);

		//Registramos el manejador del menú
		this.getViewSite().registerContextMenu(mgr, this.getViewSite().getSelectionProvider());
	}
	
	private void populateMenu(IMenuManager mgr){
		Action editPattern = new Action("Edit patterns") {
			public void run(){
				
				PatternEditor patternEditor = new PatternEditor();
				try {
					getViewSite().getPage()
					.openEditor(patternEditor.getEditorInput(), 
							"pattern01.plugin.components.editors.PatternEditor");
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		mgr.add(editPattern);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		
	}

	private URL getFileUrl(String imageName){
		String iconPath = "icons/";
		Bundle bundle = Platform.getBundle("Pattern01");
		Path path = new Path(iconPath + imageName);
		URL fileUrl = FileLocator.find(bundle, path, null);
		return fileUrl;
	}
	
	private ImageDescriptor getImageDescriptor(String imageName){
		return ImageDescriptor.createFromURL(getFileUrl(imageName));
	}
	
	private Image getImage(String imageName){
		Image img = null;
		try{
			img = ImageDescriptor.createFromURL(getFileUrl(imageName)).createImage();
		}catch(Exception ex){
			ex.printStackTrace(System.err);
		}
		return img;
	}
	
	private void createActionBar(){
		IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		mgr.add(searchPatternAction);
	}
	
	private void setSelectionListener(){
//		ISelectionListener listener = new ISelectionListener() {
//			
//			@Override
//			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
//				
//				
//			}
//		};
	}
	
	private void setSecundaryClickListener(){
//		MenuManager mgr = new MenuManager();
//		mgr.addMenuListener(new IMenuListener() {
//			
//			@Override
//			public void menuAboutToShow(IMenuManager manager) {
//			
//				
//			}
//		});
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
