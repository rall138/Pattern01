package pattern01.helpers.instancegen;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
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
	
	public void generateTreeFromDefinition(String className, String patternfolderPath){
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/PatternInstance";
		lgt.writeSingleMessage("Reading instances from: "+className);
		try {
			File file = new File(patternfolderPath+className+"Instance.xml");
			if (file.exists()){
				URI classInstanceXml_uri = new URI("file://"+patternfolderPath+className+"Instance.xml");
				InputSource is = new InputSource(classInstanceXml_uri.getPath());
				
				//Obtenemos el nodo padre (Siempre es patterninstance)
				Node parentNode = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
				if (parentNode != null){
					recursiveParseing(parentNode, this.parentItem);
				}
			}else{
				lgt.writeSingleMessage("Warning no definition found for: "+className);
			}
		} catch (XPathExpressionException | IllegalStateException | URISyntaxException e) {
			e.printStackTrace();
		}
	}


/* [Begin] Auto-generated code for pattern instance parser do not remove */
	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Wed Jun 22 12:49:39 GFT 2016
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
			if("generator" == actualNode.getNodeName()){
				patterninstance.setGenerator(actualNode.getNodeValue() != null ? pattern01.helpers.generated.Generator.valueOf(actualNode.getNodeValue().toString()):pattern01.helpers.generated.Generator.WEB);
			}
			if("name" == actualNode.getNodeName()){
				patterninstance.setName(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("description" == actualNode.getNodeName()){
				patterninstance.setDescription(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("image" == actualNode.getNodeName()){
				patterninstance.setImage(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"ww.png");
			}
			item.setData("class_instance",patterninstance);
		}else if(actualNode.getNodeName().equalsIgnoreCase("relatedFiles")){
			pattern01.helpers.generated.RelatedFiles relatedFiles = new pattern01.helpers.generated.RelatedFiles();
			item.setData("class_instance",relatedFiles);
			((pattern01.helpers.generated.PatternInstance)item.getParentItem().getData("class_instance")).setRelatedFiles(relatedFiles);
		}else if(actualNode.getNodeName().equalsIgnoreCase("relatedFile")){
			pattern01.helpers.generated.RelatedFile relatedFile = new pattern01.helpers.generated.RelatedFile();
			if("name" == actualNode.getNodeName()){
				relatedFile.setName(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("description" == actualNode.getNodeName()){
				relatedFile.setDescription(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("reference" == actualNode.getNodeName()){
				relatedFile.setReference(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("image" == actualNode.getNodeName()){
				relatedFile.setImage(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"ww.png");
			}
			item.setData("class_instance",relatedFile);
			((pattern01.helpers.generated.RelatedFiles)item.getParentItem().getData("class_instance")).getCollection_RelatedFile().add(relatedFile);
		}else if(actualNode.getNodeName().equalsIgnoreCase("faces")){
			pattern01.helpers.generated.Faces faces = new pattern01.helpers.generated.Faces();
			item.setData("class_instance",faces);
			((pattern01.helpers.generated.PatternInstance)item.getParentItem().getData("class_instance")).getCollection_Faces().add(faces);
		}else if(actualNode.getNodeName().equalsIgnoreCase("selection")){
			pattern01.helpers.generated.Selection selection = new pattern01.helpers.generated.Selection();
			if("name" == actualNode.getNodeName()){
				selection.setName(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("description" == actualNode.getNodeName()){
				selection.setDescription(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("reference" == actualNode.getNodeName()){
				selection.setReference(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("image" == actualNode.getNodeName()){
				selection.setImage(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"ww.png");
			}
			if("insert" == actualNode.getNodeName()){
				selection.setInsert(actualNode.getNodeValue() != null ? java.lang.Boolean.valueOf(actualNode.getNodeValue().toString()).booleanValue():true);
			}
			if("update" == actualNode.getNodeName()){
				selection.setUpdate(actualNode.getNodeValue() != null ? java.lang.Boolean.valueOf(actualNode.getNodeValue().toString()).booleanValue():true);
			}
			if("delete" == actualNode.getNodeName()){
				selection.setDelete(actualNode.getNodeValue() != null ? java.lang.Boolean.valueOf(actualNode.getNodeValue().toString()).booleanValue():true);
			}
			if("display" == actualNode.getNodeName()){
				selection.setDisplay(actualNode.getNodeValue() != null ? java.lang.Boolean.valueOf(actualNode.getNodeValue().toString()).booleanValue():true);
			}
			item.setData("class_instance",selection);
			((pattern01.helpers.generated.Faces)item.getParentItem().getData("class_instance")).getCollection_Selection().add(selection);
		}else if(actualNode.getNodeName().equalsIgnoreCase("register")){
			pattern01.helpers.generated.Register register = new pattern01.helpers.generated.Register();
			if("name" == actualNode.getNodeName()){
				register.setName(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("description" == actualNode.getNodeName()){
				register.setDescription(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("reference" == actualNode.getNodeName()){
				register.setReference(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("image" == actualNode.getNodeName()){
				register.setImage(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"ww.png");
			}
			item.setData("class_instance",register);
			((pattern01.helpers.generated.Faces)item.getParentItem().getData("class_instance")).getCollection_Register().add(register);
		}else if(actualNode.getNodeName().equalsIgnoreCase("dialog")){
			pattern01.helpers.generated.Dialog dialog = new pattern01.helpers.generated.Dialog();
			if("name" == actualNode.getNodeName()){
				dialog.setName(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("description" == actualNode.getNodeName()){
				dialog.setDescription(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("reference" == actualNode.getNodeName()){
				dialog.setReference(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"");
			}
			if("image" == actualNode.getNodeName()){
				dialog.setImage(actualNode.getNodeValue() != null ? actualNode.getNodeValue().toString():"ww.png");
			}
			item.setData("class_instance",dialog);
			((pattern01.helpers.generated.Faces)item.getParentItem().getData("class_instance")).getCollection_Dialog().add(dialog);
		}
	}
/* [End] Auto-generated code for pattern instance parser do not remove */

	public TreeItem getInstance(){
		return this.instance;
	}

}
