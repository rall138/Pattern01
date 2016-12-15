package pattern01.helpers.instancegen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.util.UUID;

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
				recursiveParseing(parentNode, this.parentItem, UUID.randomUUID().toString());
			}
		} catch (XPathExpressionException | IllegalStateException e) {
			e.printStackTrace();
		}
	}
	
	public void generateTreeFromDefinition(String patternUUID, String projectFolderPath){
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/PatternInstance";
		lgt.writeSingleMessage("Reading instance: "+patternUUID);
		try {
			//String classInstanceXml_Path = LocationHelper.searchClassInstancesFile(patternfolderPath);
			File file = new File(LocationHelper.getPatternInstanceFromFile(patternUUID, projectFolderPath));
			if (file.exists()){
				InputSource is = new InputSource(new FileReader(file));
				
				//Obtenemos el nodo padre (Siempre es patterninstance)
				Node patternNode = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
				if (patternNode != null){
					recursiveParseing(patternNode, this.parentItem, patternUUID);
				}
			}else{
				lgt.writeSingleMessage("Warning no definition found for: "+patternUUID);
			}
		} catch (XPathExpressionException | IllegalStateException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}





/* [Begin] Auto-generated code for pattern instance parser do not remove */
	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Nov 24 22:20:21 UYST 2016
	* Creator: rlomez
	**/

	private pattern01.helpers.generated.PatternInstance patternInstance = null;

	private void recursiveParseing(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem parent, String patternUUID){
		org.eclipse.swt.widgets.TreeItem item = new org.eclipse.swt.widgets.TreeItem(parent, 0);
		item.setData("reference",patternUUID);
		item.setText(actualNode.getNodeName());
		item.setData("type", NodeType.valueOf(actualNode.getNodeName().toUpperCase()));

		// Attribute traspasseing (from xpath node --> treeItem node)
		for(int index = 0; index < actualNode.getAttributes().getLength(); index++){
			item.setData(actualNode.getAttributes().item(index).getNodeName(),
				actualNode.getAttributes().item(index).getNodeValue().toString());
		}
		classInstanceStrategy(actualNode, item);

		// Recursion over child nodes
		if (actualNode.getChildNodes().getLength() > 0){
			for(int index = 0; index < actualNode.getChildNodes().getLength(); index++){
				if (actualNode.getChildNodes().item(index).getNodeType() == Node.ELEMENT_NODE){
					recursiveParseing(actualNode.getChildNodes().item(index), item, patternUUID);
				}
			}
		}

	}

	private void classInstanceStrategy(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem item){
		if(actualNode.getNodeName().equalsIgnoreCase("patternInstance")){
			pattern01.helpers.generated.PatternInstance patternInstance = new pattern01.helpers.generated.PatternInstance();
			patternInstance.setGenerator(actualNode.getAttributes().getNamedItem("generator") != null ? pattern01.helpers.generated.Generator.valueOf(actualNode.getAttributes().getNamedItem("generator").getNodeValue().toString()):pattern01.helpers.generated.Generator.WEB);
			patternInstance.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			this.patternInstance = new pattern01.helpers.generated.PatternInstance();
			this.patternInstance = patternInstance;
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance);
			item.setData("type","PATTERNINSTANCE");
		}else if(actualNode.getNodeName().equalsIgnoreCase("relatedFiles")){
			pattern01.helpers.generated.RelatedFiles relatedFiles = new pattern01.helpers.generated.RelatedFiles();
			relatedFiles.setImage(actualNode.getAttributes().getNamedItem("image") != null ? actualNode.getAttributes().getNamedItem("image").getNodeValue().toString():"primefaces.jpg");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",relatedFiles);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","RELATEDFILES");
			((pattern01.helpers.generated.PatternInstance)item.getParentItem().getData("class_instance")).setRelatedFiles(relatedFiles);
		}else if(actualNode.getNodeName().equalsIgnoreCase("relatedFile")){
			pattern01.helpers.generated.RelatedFile relatedFile = new pattern01.helpers.generated.RelatedFile();
			relatedFile.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			relatedFile.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			relatedFile.setReference(actualNode.getAttributes().getNamedItem("reference") != null ? actualNode.getAttributes().getNamedItem("reference").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",relatedFile);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","RELATEDFILE");
			((pattern01.helpers.generated.RelatedFiles)item.getParentItem().getData("class_instance")).getCollection_RelatedFile().add(relatedFile);
		}else if(actualNode.getNodeName().equalsIgnoreCase("faces")){
			pattern01.helpers.generated.Faces faces = new pattern01.helpers.generated.Faces();
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",faces);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","FACES");
			((pattern01.helpers.generated.PatternInstance)item.getParentItem().getData("class_instance")).getCollection_Faces().add(faces);
		}else if(actualNode.getNodeName().equalsIgnoreCase("selection")){
			pattern01.helpers.generated.Selection selection = new pattern01.helpers.generated.Selection();
			selection.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			selection.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			selection.setReference(actualNode.getAttributes().getNamedItem("reference") != null ? actualNode.getAttributes().getNamedItem("reference").getNodeValue().toString():"");
			selection.setInsert(actualNode.getAttributes().getNamedItem("insert") != null ? pattern01.helpers.generated.Bool.valueOf(actualNode.getAttributes().getNamedItem("insert").getNodeValue().toString()):pattern01.helpers.generated.Bool.TRUE);
			selection.setUpdate(actualNode.getAttributes().getNamedItem("update") != null ? pattern01.helpers.generated.Bool.valueOf(actualNode.getAttributes().getNamedItem("update").getNodeValue().toString()):pattern01.helpers.generated.Bool.TRUE);
			selection.setDelete(actualNode.getAttributes().getNamedItem("delete") != null ? pattern01.helpers.generated.Bool.valueOf(actualNode.getAttributes().getNamedItem("delete").getNodeValue().toString()):pattern01.helpers.generated.Bool.TRUE);
			selection.setDisplay(actualNode.getAttributes().getNamedItem("display") != null ? pattern01.helpers.generated.Bool.valueOf(actualNode.getAttributes().getNamedItem("display").getNodeValue().toString()):pattern01.helpers.generated.Bool.TRUE);
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",selection);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","SELECTION");
			((pattern01.helpers.generated.Faces)item.getParentItem().getData("class_instance")).getCollection_Selection().add(selection);
		}else if(actualNode.getNodeName().equalsIgnoreCase("register")){
			pattern01.helpers.generated.Register register = new pattern01.helpers.generated.Register();
			register.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			register.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			register.setReference(actualNode.getAttributes().getNamedItem("reference") != null ? actualNode.getAttributes().getNamedItem("reference").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",register);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","REGISTER");
			((pattern01.helpers.generated.Faces)item.getParentItem().getData("class_instance")).getCollection_Register().add(register);
		}else if(actualNode.getNodeName().equalsIgnoreCase("dialog")){
			pattern01.helpers.generated.Dialog dialog = new pattern01.helpers.generated.Dialog();
			dialog.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			dialog.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			dialog.setReference(actualNode.getAttributes().getNamedItem("reference") != null ? actualNode.getAttributes().getNamedItem("reference").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",dialog);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","DIALOG");
			((pattern01.helpers.generated.Faces)item.getParentItem().getData("class_instance")).getCollection_Dialog().add(dialog);
		}
	}
/* [End] Auto-generated code for pattern instance parser do not remove */

	public TreeItem getInstance(){
		return this.instance;
	}

}
