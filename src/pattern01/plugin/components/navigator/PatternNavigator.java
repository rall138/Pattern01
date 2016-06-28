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
						try{
							generateLeafs(projectParentItem, potencialProjectFolder.getPath());
						}catch(XPathExpressionException e){
							//System.err.println(e.getMessage());
						}
						break;
					}
				}
			}
		}
	}
	
	private String getPatternFolderPath(String projectFolderPath){
		return LocationHelper.searchPatternFolderPath(projectFolderPath);
	}	
	
	private String getClassInstanceFile(String projectFolderPath) throws NullPointerException{
		return LocationHelper.searchClassInstancesFile(LocationHelper.searchPatternFolderPath(projectFolderPath));
	}
	
	private NodeList getPackagesDeclared(String projectFolderPath) throws XPathExpressionException {
		NodeList classNodeList = null;
		String classInstancesXml = this.getClassInstanceFile(projectFolderPath), expression = "";
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		expression = "/Packages/Package";
		try{
			classNodeList = (NodeList) xpath.evaluate (expression, 
					new InputSource(classInstancesXml), XPathConstants.NODESET);
			System.err.println("Cantidad de packages: "+classNodeList.getLength());
		}catch(XPathExpressionException e){
			throw new XPathExpressionException("No packages defined at classinstances.xml file");			
		}
		return classNodeList;
	}
	
	private NodeList getClassesDeclared(String projectFolderPath, String packageName) throws XPathExpressionException{
		NodeList classNodeList = null;
		String classInstancesXml = this.getClassInstanceFile(projectFolderPath), expression = "";
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		expression = "/Packages/Package[@name='"+packageName+"']/Class";
		try{
			classNodeList = (NodeList) xpath.evaluate (expression, 
					new InputSource(classInstancesXml), XPathConstants.NODESET);
		}catch(XPathExpressionException e){
			throw new XPathExpressionException("No classes defined for package "+packageName);			
		}
		return classNodeList;
	}
	
	//TODO Generar nodos packages
	private void generateLeafs(TreeItem parent, String projectFolderPath) throws XPathExpressionException{
		TreeItem packageItem = null, classItem = null;
		String packageName = "", className = "";
		NodeList packageList = this.getPackagesDeclared(projectFolderPath), classList = null;
		
		for(int index = 0; index < packageList.getLength(); index++){ //Packages
			if(packageList.item(index).getNodeType() == Node.ELEMENT_NODE){				
				packageName = packageList.item(index).getAttributes().getNamedItem("name").getNodeValue();
				packageItem = new TreeItem(parent, 0);
				packageItem.setText(packageName);
				packageItem.setImage(ImageHelper.getImage("package_obj.png"));
				classList = this.getClassesDeclared(projectFolderPath, packageName);
				for (int hindex = 0; hindex < classList.getLength(); hindex++){ //Classes
					if(classList.item(hindex).getNodeType() == Node.ELEMENT_NODE){						
						className = classList.item(hindex).getAttributes().getNamedItem("name").getNodeValue();
						classItem = new TreeItem(packageItem, 0);
						classItem.setText(className);
						classItem.setImage(ImageHelper.getImage("class_obj.png"));
						PatternInstanceParser instanceParser = new PatternInstanceParser(classItem);
						instanceParser.generateTreeFromDefinition(className, this.getPatternFolderPath(projectFolderPath));
						if (instanceParser.getInstance()!= null){
							classItem = instanceParser.getInstance();
						}
					}
				}
			}
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