package pattern01;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

public class PatternNav extends ViewPart{

	private Action searchPatternAction;
	private Composite mParent;
	
	@Override
	public void createPartControl(Composite parent) {
		mParent = parent;
		searchPatternAction = new Action("search..") {
			public void run(){
				//Aca va el c�digo de la acci�n
			}
		};
		searchPatternAction.setImageDescriptor(getImageDescriptor("lupa.png"));
		createActionBar();

		Tree tree = new Tree(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		TreeItem item = new TreeItem(tree, 0);
		Image image = getImage("package_obj.png");
		item.setImage(image);
		item.setText("Entidad de prueba");
		TreeItem sub_item = new TreeItem(item, 0);
		image = getImage("class_obj.png");		
		sub_item.setImage(image);
		sub_item.setText("Sub item");
		image = getImage("ww.ico");
		TreeItem sub_item2 = new TreeItem(sub_item, 0);
		sub_item2.setImage(image);
		sub_item2.setText("Sub item 2");
		image = getImage("primefaces.ico");		
		TreeItem sub_item3 = new TreeItem(sub_item2, 0);
		sub_item3.setImage(image);
		sub_item3.setText("Sub item 3");		
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
		ISelectionListener listener = new ISelectionListener() {
			
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				
				
			}
		};
	}
	
	private void setSecundaryClickListener(){
		MenuManager mgr = new MenuManager();
		mgr.addMenuListener(new IMenuListener() {
			
			@Override
			public void menuAboutToShow(IMenuManager manager) {
			
				
			}
		});
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
