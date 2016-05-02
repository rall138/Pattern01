package pattern01.helpers.instancegen;

import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import pattern01.helpers.ImageHelper;
import pattern01.helpers.LoggerThread;


public class PatternInstanceParser {

	private TreeItem instance = null;
	private LoggerThread lgt = new LoggerThread();
	private TreeItem parentItem = null;
	
	public PatternInstanceParser(TreeItem parentItem){
		this.parentItem = parentItem;
	}
	
	public void generateTreeFromDefinition(String className, String patternfolderPath){
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/PatternInstance";
		lgt.writeSingleMessage("Reading instances from: "+className);
		try {
			URI classInstanceXml_uri = new URI("file://"+patternfolderPath+className+"Instance.xml");
			InputSource is = new InputSource(classInstanceXml_uri.getPath());
			
			//Obtenemos el nodo padre (Siempre es patterninstance)
			Node parentNode = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
			if (parentNode != null){
				recursiveParseing(parentNode, this.parentItem);
			}
		} catch (XPathExpressionException | IllegalStateException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private void recursiveParseing(Node actualNode, TreeItem parent){
		TreeItem item = new TreeItem(parent, 0);
		item.setText(actualNode.getNodeName());
		
		if (actualNode.getAttributes().getNamedItem("image") != null){
			item.setImage(ImageHelper.getImage(actualNode.getAttributes()
					.getNamedItem("image").getNodeValue()));
		}
		
		//Se obtiene el valor de los atributos
		for(int index = 0; index < actualNode.getAttributes().getLength(); index++){
			item.setData(actualNode.getAttributes().item(index).getNodeName(), 
					actualNode.getAttributes().item(index).getNodeValue().toString());
		}
		//Si el nodo es padre entonces iteramos sobre sus hijos
		if (actualNode.getChildNodes().getLength() > 0){
			for(int index = 0; index < actualNode.getChildNodes().getLength(); index++){
				if (actualNode.getChildNodes().item(index).getNodeType() == Node.ELEMENT_NODE){
					recursiveParseing(actualNode.getChildNodes().item(index), item);
				}
			}
		}
	}
	
	public TreeItem getInstance(){
		return this.instance;
	}
	
}
