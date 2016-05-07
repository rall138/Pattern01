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
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.ViewPart;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import pattern01.helpers.ImageHelper;
import pattern01.helpers.LocationHelper;
import pattern01.helpers.instancegen.PatternInstanceParser;

public class PatternNavigator extends ViewPart {

	private Action searchPatternAction;
	private Tree instanceTree = null;
	
	@Override
	public void createPartControl(Composite parent) {
		//Tree de navegacion
		generateTree(parent);
		menuBuilder();
		
		searchPatternAction = new Action("search..") {
			public void run(){
				//Aca va el codigo de la accion
			}
		};
		searchPatternAction.setImageDescriptor(ImageHelper
				.getImageDescriptor("lupa.png"));
		createActionBar();
	}
	
	private void generateTree(Composite parent){
		this.instanceTree = new Tree(parent,0);
		generateItems(this.instanceTree);
	}
	
	private void generateItems(Tree tree){
		try {
			TreeItem projectItem = null;
			URI uri = new URI("file:///"+LocationHelper.getActiveWorkSpace());
			File workspaceFolder = new File(uri);
			File projectFolder = null;
			for(int index = 0; index < workspaceFolder.listFiles().length; index++){
				if(workspaceFolder.listFiles()[index].isDirectory()){
					projectItem = new TreeItem(tree, 0);
					projectItem.setText(workspaceFolder.listFiles()[index].getName());
					projectItem.setImage(ImageHelper.getImage("prj_obj.png"));
					projectFolder = workspaceFolder.listFiles()[index];
					int hindex = 0;
					boolean itemFound = false;
					while (hindex < projectFolder.listFiles().length && !itemFound){
						if (projectFolder.listFiles()[hindex].isDirectory() &&
								projectFolder.listFiles()[hindex].getName().equalsIgnoreCase("patternfolder")){
							itemFound = true;
						}else{
							hindex++;
						}
					}
					if (itemFound){
						uri = projectFolder.listFiles()[hindex].toURI();
						generateTreeItemInstances(projectItem, uri);
					}
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private void generateTreeItemInstances(TreeItem parent, URI patternFolderURI){
		
		//Archivo que contiene las instancias generadas
		URI classInstancexml_uri = null;
		try {
			
			classInstancexml_uri = new URI("file://"+patternFolderURI.getPath()+"ClassInstances.xml");
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/Classes/Class";
		NodeList classNodeList;
		try {
			
			classNodeList = (NodeList) xpath.evaluate
					(expression, new InputSource(classInstancexml_uri.getPath()), 
					XPathConstants.NODESET);
			
			if(classNodeList != null && classNodeList.getLength() > 0){
				TreeItem classInstance = null;
				for(int index = 0; index < classNodeList.getLength(); index++){
					
					if(classNodeList.item(index).getNodeType() == Node.ELEMENT_NODE){
						
						//Nombre de lo clase 
						String className = classNodeList.item(index)
								.getAttributes().getNamedItem("name").getNodeValue();
						
						//Genera el item [class] como nodo "raiz"
						classInstance = new TreeItem(parent, 0);
						classInstance.setText(className);
						classInstance.setData("type", NodeType.classType);
						classInstance.setImage(ImageHelper.getImage("class_obj.png"));
						
						//Se generan los demas elementos debajo del nodo clase por intermedio de parseing del xml correspondiente.
						PatternInstanceParser instanceParser = new PatternInstanceParser(classInstance);
						instanceParser.generateTreeFromDefinition(className, patternFolderURI.getPath());
						classInstance = instanceParser.getInstance();
					}
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	private void menuBuilder(){

		
		MenuFactory mfact = new MenuFactory();
		mfact.generateDisplayableOptions(NodeType.classType, this.instanceTree);
		
		
//		final Menu menu_principal = new Menu(this.instanceTree);
//		final MenuItem menu_item = new MenuItem(menu_principal, SWT.CASCADE);
//		menu_item.setText("Patterns");
//		this.instanceTree.setMenu(menu_principal);
//		menu_principal.addMenuListener(new MenuAdapter(){
//			@Override
//			public void menuShown(MenuEvent e) {
//				TreeItem event_item = instanceTree.getSelection()[0];
//				if (event_item.getData("type").equals(NodeType.classType)){
//					
//					Menu ww_menu = new Menu(menu_principal);
//					menu_item.setMenu(ww_menu);
//					
//					MenuItem ww_menuitem = new MenuItem(ww_menu, SWT.PUSH);
//					ww_menuitem.setText("WorkWith");
//				}else if(event_item.getData("type").toString()
//						.equalsIgnoreCase(NodeType.toString(NodeType.wwType))){
//					//TODO - Agregar logica de instancia
//				}
//			}
//			
//		});
		
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