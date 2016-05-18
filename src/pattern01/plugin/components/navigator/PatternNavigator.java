package pattern01.plugin.components.navigator;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
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

	private Action searchPatternAction, savePatternAction;
	private Tree instanceTree = null;
	
	@Override
	public void createPartControl(Composite parent) {
		generateTree(parent);
		menuBuilder();
		defineActionBarActions();
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
						classInstance.setData("type", NodeType.nodeTypeFromString(classNodeList.item(index).getNodeName()));
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
		instanceTree.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				instanceTree.setMenu(null);
				MenuFactory mfact = new MenuFactory();
				TreeItem aux_item = (TreeItem )event.item;
				NodeType aux_nodeType = aux_item.getData("type") != null ? 
					(NodeType)aux_item.getData("type"): NodeType.UNDEFINED;
					System.err.println("Tipo de nodo:"+NodeType.toString(aux_nodeType));
				if (aux_nodeType != NodeType.UNDEFINED){
					mfact.generateDisplayableOptions(aux_nodeType, instanceTree);
				}
			}
		});
		
		instanceTree.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.DEL){
					TreeItem item_to_delete = instanceTree.getSelection()[0];
					item_to_delete = null;
				}
			}
		});
	}
	
	private void defineActionBarActions(){
		searchPatternAction = new Action("Search class") {
			public void run(){
				//Aca va el codigo de la accion
			}
		};
		searchPatternAction.setImageDescriptor(ImageHelper
				.getImageDescriptor("lupa.png"));
		
		savePatternAction = new Action("Save instance") {
			public void run(){
				
			}
		};
		savePatternAction.setImageDescriptor(ImageHelper
				.getImageDescriptor("save.png"));
		createActionBar();
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