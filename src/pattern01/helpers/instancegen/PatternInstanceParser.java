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
	* Creation date: Thu Jun 09 20:11:58 UYT 2016
	* Creator: rlomez
	**/
	private void recursiveParseing(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem parent){
		org.eclipse.swt.widgets.TreeItem item = new org.eclipse.swt.widgets.TreeItem(parent, 0);
		item.setText(actualNode.getNodeName());
		item.setData("type", NodeType.nodeTypeFromString(actualNode.getNodeName()));

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
		}
	}
/* [End] Auto-generated code for pattern instance parser do not remove */



















	public TreeItem getInstance(){
		return this.instance;
	}
	
}
