package pattern01.helpers.instancegen;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import pattern01.helpers.LoggerThread;

public class PatternInstanceParser {

	private static final String linux_generalConfigPath = 
			System.getProperty("user.dir").replace("src/", "")
			.replace("pattern01/", "").replace("/helpers", "")
			+ "/GeneralConfig/PatternInstance.xml";

	private static final String windows_generalConfigPath = 
			System.getProperty("user.dir").replace("src\\", "")
			.replace("pattern01\\", "").replace("\\helpers", "")
			+ "\\GeneralConfig\\PatternInstance.xml";

	private TreeItem instance = null;
	private LoggerThread lgt = new LoggerThread();
	private Tree tree = null;
	
	public PatternInstanceParser(Tree tree){
		this.tree = tree;
	}
	
	public void generateTreeFromDefinition(String className){
		lgt.writeSingleMessage("generateTreeFromDefinition");
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/PatternInstance";
		try {
			InputSource is = new InputSource(linux_generalConfigPath);
			//Obtenemos el nodo padre (Siempre es patterninstance)
			Node parentNode = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
			if (parentNode != null){
				TreeItem item = new TreeItem(tree, 0);
				item.setText(parentNode.getNodeName());
				recursiveParseing(parentNode, item);
			}
		} catch (XPathExpressionException | IllegalStateException e) {
			e.printStackTrace();
		}
	}
	
	private void recursiveParseing(Node actualNode, TreeItem parent){
		TreeItem item = new TreeItem(parent, 0);
		item.setText(actualNode.getNodeName());
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
