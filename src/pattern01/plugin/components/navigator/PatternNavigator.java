package pattern01.plugin.components.navigator;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.WorkbenchTriggerPointAdvisor;
import org.eclipse.ui.part.ViewPart;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import pattern01.helpers.ImageHelper;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.instancegen.PatternInstanceParser;
import pattern01.helpers.location.LocationHelper;
import pattern01.plugin.components.editors.PatternEditor;

public class PatternNavigator extends ViewPart {

	private Action searchPatternAction;
	
	@Override
	public void createPartControl(Composite parent) {
		menuBuilder();
		
		searchPatternAction = new Action("search..") {
			public void run(){
				//Aca va el codigo de la accion
			}
		};
		searchPatternAction.setImageDescriptor(ImageHelper
				.getImageDescriptor("lupa.png"));
		createActionBar();

		Tree tree = new Tree(parent, 0);
		
		TreeItem item;
		PatternInstanceParser instanceParser = null;
		LoggerThread lg = new LoggerThread();
		
		//Obtenemos la carpeta PatternFolder
		File patternFolder = null;
		try {
			patternFolder = new File(new URI("file:///"+
					LocationHelper.getSelectedProjectPath()+"/PatternFolder"));
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		if (patternFolder.isDirectory()){
			File[] files = patternFolder.listFiles(new FileNameFilterImpl());
			int index = 0;
			boolean itemFound = false;
			while(index < files.length && !itemFound){
				if (files[index].getName().equalsIgnoreCase("classinstances.xml")){
					itemFound = true;
				}else{
					index++;
				}
			}

			if (itemFound){
				lg.writeSingleMessage("Item encontrado");
				XPath xpath = XPathFactory.newInstance().newXPath();
				String expression = "/Classes/Class";
				NodeList classNodeList;
				try {
					classNodeList = (NodeList) xpath.evaluate(expression, new InputSource(files[index].getAbsolutePath()), 
							XPathConstants.NODESET);
					TreeItem parentItem = new TreeItem(tree, 0);
					parentItem.setText("Pattern Instances");
					if(classNodeList != null && classNodeList.getLength() > 0){
						String className = "";
						for(index = 0; index < classNodeList.getLength(); index++){
							if(classNodeList.item(index).getNodeType() == Node.ELEMENT_NODE){
								item = new TreeItem(parentItem, 0);
								className = classNodeList.item(index)
										.getAttributes().getNamedItem("name").getNodeValue();
								item.setText(className);
								item.setImage(ImageHelper.getImage("class_obj.png"));
								instanceParser = new PatternInstanceParser(item);
								instanceParser.generateTreeFromDefinition(className);
								item = instanceParser.getInstance();
							}
						}
					}
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private void generateBasicTree(Tree tree){
		try {
			TreeItem projectItem = null;
			URI uri = new URI("file:///"+LocationHelper.getActiveWorkSpace());
			File workspaceFolder = new File(uri);
			for(int index = 0; index < workspaceFolder.listFiles().length; index++){
				if(workspaceFolder.listFiles()[index].isDirectory()){
					projectItem = new TreeItem(tree, 0);
					projectItem.setText(workspaceFolder.listFiles()[index].getName());
					projectItem.setImage("");
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
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
//		Menu menu = mgr.createContextMenu(tviewer.getControl());
//		tviewer.getControl().setMenu(menu);
//
//		//Registramos el manejador del menú
//		this.getViewSite().registerContextMenu(mgr, this.getViewSite().getSelectionProvider());
	}
	
	private void populateMenu(IMenuManager mgr){
		Action editPattern = new Action("Edit patterns") {
			public void run(){
				
				PatternEditor patternEditor = new PatternEditor();
				try {
					
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.openEditor(patternEditor.getEditorInput(), patternEditor.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		};
		mgr.add(editPattern);
	}
	
	private void createActionBar(){
		IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		mgr.add(searchPatternAction);
	}	

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
}