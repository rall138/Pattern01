package pattern01.plugin.components.navigator;

import java.io.File;

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
		generateProjectNodeFromWorkspace();
	}
		
	// Java projects contains .project named file that is an xml file 
	private void generateProjectNodeFromWorkspace(){
		File activeWorkspace = new File(LocationHelper.getActiveWorkSpace());
		for (File potencialProjectFolder : activeWorkspace.listFiles()){ // first level project folder
			if (potencialProjectFolder.isDirectory()){
				for (File insideFile : potencialProjectFolder.listFiles()){ // second level must be the .project file
					if (!insideFile.isDirectory() && insideFile.getName().equalsIgnoreCase(".project")){
						TreeItem projectParentItem = new TreeItem(this.instanceTree, 0);
						projectParentItem.setText(potencialProjectFolder.getName());
						projectParentItem.setImage(ImageHelper.getImage("prj_obj.png"));
						generateLeafs(projectParentItem, potencialProjectFolder.getPath());
						break;
					}
				}
			}
		}
	}
	
	private String getPatternFolderPath(String projectFolderPath){
		String patternFolderPath = "";
		File projectFolder = new File(projectFolderPath);
		int index = 0;
		boolean itemFound = false;
		while (index < projectFolder.listFiles().length && !itemFound){
			if (projectFolder.listFiles()[index].getName().equalsIgnoreCase("patternfolder")){
				patternFolderPath = projectFolder.listFiles()[index].getAbsolutePath();
				itemFound = true;
			}else{
				index++;
			}
		}
		return patternFolderPath;
	}
	
	private String getClassInstancesFile(String projectFolderPath){
		String classInsancesURItoString = "";
		File projectFolder = new File(projectFolderPath);
		int index = 0;
		boolean itemFound = false;
		while (index < projectFolder.listFiles().length && !itemFound){
			if (projectFolder.listFiles()[index].getName().equalsIgnoreCase("classinstances.xml")){
				classInsancesURItoString = projectFolder.listFiles()[index].getAbsolutePath();
				itemFound = true;
			}else{
				index++;
			}
		}
		return classInsancesURItoString;
	}
	
	//TODO Generar nodos packages
	private void generateLeafs(TreeItem parent, String projectFolderPath){
		try {
			String classInstancesXml = "", patternFolder = "";
			
			// Only for projects wich has PatternFolder created
			patternFolder = this.getPatternFolderPath(projectFolderPath);
			if (patternFolder.equals("")){
				return;
			}
			
			classInstancesXml = this.getClassInstancesFile(patternFolder);
			if (classInstancesXml.equals("")){
				return;
			}

			XPath xpath = XPathFactory.newInstance().newXPath();
			String expression = "/Classes/Class";

			NodeList classNodeList = (NodeList) xpath.evaluate 
					(expression, new InputSource(classInstancesXml), XPathConstants.NODESET);
			
			if(classNodeList != null && classNodeList.getLength() > 0){
				TreeItem classInstance = null, packageNode = null;
				for(int index = 0; index < classNodeList.getLength(); index++){
					if(classNodeList.item(index).getNodeType() == Node.ELEMENT_NODE){
						
						String className = classNodeList.item(index)
								.getAttributes().getNamedItem("name").getNodeValue();
						
						//Genera el item [class] como nodo "raiz"
						classInstance = new TreeItem(parent, 0);
						classInstance.setText(className);
						classInstance.setData("type", NodeType.valueOf(classNodeList.item(index).getNodeName().toUpperCase()));
						classInstance.setImage(ImageHelper.getImage("class_obj.png"));
						
						//Se generan los demas elementos debajo del nodo clase por intermedio de parseing del xml correspondiente.
						PatternInstanceParser instanceParser = new PatternInstanceParser(classInstance);
						instanceParser.generateTreeFromDefinition(className, patternFolder);
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
				if (aux_nodeType != NodeType.UNDEFINED){
					mfact.generateDisplayableOptions(aux_nodeType, instanceTree);
				}
			}
		});
		
		//TODO Borrado particular de un elemento
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