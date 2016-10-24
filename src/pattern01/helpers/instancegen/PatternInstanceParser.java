package pattern01.helpers.instancegen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.LocationHelper;
import pattern01.helpers.LoggerThread;
import pattern01.plugin.components.navigator.NodeType;


public class PatternInstanceParser {

	private TreeItem instance = null;
	private LoggerThread lgt = new LoggerThread();
	private TreeItem parentItem = null;
	
	public PatternInstanceParser(TreeItem parentItem){
		this.parentItem = parentItem;
	}
	
	/* Para aplicar la instancia por defecto */
	public void generateTreeFromDefaultDefinition(){
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/PatternInstance";
		try {
			URI classInstanceXml_uri = CommonPathFix.getHardCodedPath(PATH_NAME.DEFAULTWWDEFINITION);
			InputSource is = new InputSource(classInstanceXml_uri.getPath());
			
			//Obtenemos el nodo padre (Siempre es patterninstance)
			Node parentNode = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
			if (parentNode != null){
				recursiveParseing(parentNode, this.parentItem);
			}
		} catch (XPathExpressionException | IllegalStateException e) {
			e.printStackTrace();
		}
	}
	
	public void generateTreeFromDefinition(String patternName, String projectFolderPath){
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/PatternInstance";
		lgt.writeSingleMessage("Reading instance: "+patternName);
		try {
			//String classInstanceXml_Path = LocationHelper.searchClassInstancesFile(patternfolderPath);
			File file = new File(LocationHelper.getPatternInstanceFromFile(patternName, projectFolderPath));
			if (file.exists()){
				InputSource is = new InputSource(new FileReader(file));
				
				//Obtenemos el nodo padre (Siempre es patterninstance)
				Node patternNode = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
				if (patternNode != null){
					recursiveParseing(patternNode, this.parentItem);
				}
			}else{
				lgt.writeSingleMessage("Warning no definition found for: "+patternName);
			}
		} catch (XPathExpressionException | IllegalStateException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

/* [Begin] Auto-generated code for pattern instance parser do not remove */
	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Mon Oct 24 12:02:32 GFT 2016
	* Creator: rlomez
	**/
	private void recursiveParseing(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem parent){
		org.eclipse.swt.widgets.TreeItem item = new org.eclipse.swt.widgets.TreeItem(parent, 0);
		item.setText(actualNode.getNodeName());
		item.setData("type", NodeType.valueOf(actualNode.getNodeName().toUpperCase()));

		// Attribute traspasseing (from xpath node --> treeItem node)
		for(int index = 0; index < actualNode.getAttributes().getLength(); index++){
			item.setData(actualNode.getAttributes().item(index).getNodeName(),
				actualNode.getAttributes().item(index).getNodeValue().toString());
		}
		classInstanceStrategy(actualNode, item);
		extraInfoForPatternInstance(parent, item);

		// Recursion over child nodes
		if (actualNode.getChildNodes().getLength() > 0){
			for(int index = 0; index < actualNode.getChildNodes().getLength(); index++){
				if (actualNode.getChildNodes().item(index).getNodeType() == Node.ELEMENT_NODE){
					recursiveParseing(actualNode.getChildNodes().item(index), item);
				}
			}
		}

	}

	private void classInstanceStrategy(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem item){
		if(actualNode.getNodeName().equalsIgnoreCase("patterninstance")){
			pattern01.helpers.generated.PatternInstance patterninstance = new pattern01.helpers.generated.PatternInstance();
			patterninstance.setGenerator(actualNode.getAttributes().getNamedItem("generator") != null ? pattern01.helpers.generated.Generator.valueOf(actualNode.getAttributes().getNamedItem("generator").getNodeValue()):pattern01.helpers.generated.Generator.WEB);
			patterninstance.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue():"");
			patterninstance.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue():"");
			patterninstance.setParentClass(actualNode.getAttributes().getNamedItem("parentClass") != null ? actualNode.getAttributes().getNamedItem("parentClass").getNodeValue():"");
			item.setImage(pattern01.helpers.ImageHelper.getImage(""));
			item.setData("class_instance",patterninstance);
		}else if(actualNode.getNodeName().equalsIgnoreCase("relatedFiles")){
			pattern01.helpers.generated.RelatedFiles relatedFiles = new pattern01.helpers.generated.RelatedFiles();
			relatedFiles.setImage(actualNode.getAttributes().getNamedItem("image") != null ? actualNode.getAttributes().getNamedItem("image").getNodeValue():"generic.jpg");
			item.setImage(pattern01.helpers.ImageHelper.getImage(""));
			item.setData("class_instance",relatedFiles);
			((pattern01.helpers.generated.PatternInstance)item.getParentItem().getData("class_instance")).setRelatedFiles(relatedFiles);
		}else if(actualNode.getNodeName().equalsIgnoreCase("relatedFile")){
			pattern01.helpers.generated.RelatedFile relatedFile = new pattern01.helpers.generated.RelatedFile();
			relatedFile.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue():"");
			relatedFile.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue():"");
			relatedFile.setReference(actualNode.getAttributes().getNamedItem("reference") != null ? actualNode.getAttributes().getNamedItem("reference").getNodeValue():"");
			item.setImage(pattern01.helpers.ImageHelper.getImage(""));
			item.setData("class_instance",relatedFile);
			((pattern01.helpers.generated.RelatedFiles)item.getParentItem().getData("class_instance")).getCollection_RelatedFile().add(relatedFile);
		}else if(actualNode.getNodeName().equalsIgnoreCase("faces")){
			pattern01.helpers.generated.Faces faces = new pattern01.helpers.generated.Faces();
			item.setImage(pattern01.helpers.ImageHelper.getImage(""));
			item.setData("class_instance",faces);
			((pattern01.helpers.generated.PatternInstance)item.getParentItem().getData("class_instance")).getCollection_Faces().add(faces);
		}else if(actualNode.getNodeName().equalsIgnoreCase("selection")){
			pattern01.helpers.generated.Selection selection = new pattern01.helpers.generated.Selection();
			selection.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue():"");
			selection.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue():"");
			selection.setReference(actualNode.getAttributes().getNamedItem("reference") != null ? actualNode.getAttributes().getNamedItem("reference").getNodeValue():"");
			selection.setInsert(actualNode.getAttributes().getNamedItem("insert") != null ? java.lang.Boolean.valueOf(actualNode.getAttributes().getNamedItem("insert").getNodeValue()).booleanValue():true);
			selection.setUpdate(actualNode.getAttributes().getNamedItem("update") != null ? java.lang.Boolean.valueOf(actualNode.getAttributes().getNamedItem("update").getNodeValue()).booleanValue():true);
			selection.setDelete(actualNode.getAttributes().getNamedItem("delete") != null ? java.lang.Boolean.valueOf(actualNode.getAttributes().getNamedItem("delete").getNodeValue()).booleanValue():true);
			selection.setDisplay(actualNode.getAttributes().getNamedItem("display") != null ? java.lang.Boolean.valueOf(actualNode.getAttributes().getNamedItem("display").getNodeValue()).booleanValue():true);
			item.setImage(pattern01.helpers.ImageHelper.getImage(""));
			item.setData("class_instance",selection);
			((pattern01.helpers.generated.Faces)item.getParentItem().getData("class_instance")).getCollection_Selection().add(selection);
		}else if(actualNode.getNodeName().equalsIgnoreCase("register")){
			pattern01.helpers.generated.Register register = new pattern01.helpers.generated.Register();
			register.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue():"");
			register.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue():"");
			register.setReference(actualNode.getAttributes().getNamedItem("reference") != null ? actualNode.getAttributes().getNamedItem("reference").getNodeValue():"");
			item.setImage(pattern01.helpers.ImageHelper.getImage(""));
			item.setData("class_instance",register);
			((pattern01.helpers.generated.Faces)item.getParentItem().getData("class_instance")).getCollection_Register().add(register);
		}else if(actualNode.getNodeName().equalsIgnoreCase("dialog")){
			pattern01.helpers.generated.Dialog dialog = new pattern01.helpers.generated.Dialog();
			dialog.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue():"");
			dialog.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue():"");
			dialog.setReference(actualNode.getAttributes().getNamedItem("reference") != null ? actualNode.getAttributes().getNamedItem("reference").getNodeValue():"");
			item.setImage(pattern01.helpers.ImageHelper.getImage(""));
			item.setData("class_instance",dialog);
			((pattern01.helpers.generated.Faces)item.getParentItem().getData("class_instance")).getCollection_Dialog().add(dialog);
		}
	}

	private void extraInfoForPatternInstance( org.eclipse.swt.widgets.TreeItem parentItem, org.eclipse.swt.widgets.TreeItem item){
		if (item.getData("type").toString().compareToIgnoreCase("patterninstance")==0){
			((pattern01.helpers.generated.PatternInstance)item.getData("class_instance")).setParentClass(parentItem.getData("name").toString());
		}
	}
/* [End] Auto-generated code for pattern instance parser do not remove */

	public TreeItem getInstance(){
		return this.instance;
	}

}
