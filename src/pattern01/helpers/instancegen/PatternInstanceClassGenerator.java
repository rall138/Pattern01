package pattern01.helpers.instancegen;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Node;
import org.w3c.dom.xpath.XPathResult;
import org.xml.sax.InputSource;

import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.PropertyHelper;
import pattern01.helpers.temporal_containers.Element;
import pattern01.plugin.components.navigator.ImageHelper;

/*
 * A traves de la estructura definida en el PatternDefinition 
 * y por intermedio de las clases generadas, se crea un arbol que identifica 
 * en que orden se deben cargar los elementos, de esta manera se pretende asociar 
 * el treeview definido en el editor del plugin con una interfaz que obtenga el valor de sus propiedades  
 * y pueda realizar modificaciones de las mismas.
 * */

public class PatternInstanceClassGenerator {

	private static final String tabspace = "\t";
	private static final String treeNameSpace = "org.eclipse.swt.widgets.Tree";
	private static final String quotscape = "\"";
	private static final String classHeaderComment = 
			tabspace+"/**\n"
			+tabspace+"* Generated class via Generator.xml\n"
			+tabspace+"* Creation date: "+Calendar.getInstance().getTime()+"\n"
			+tabspace+"* Creator: rlomez\n"
			+tabspace+"**/";
	
	private PropertyHelper prophelper = new PropertyHelper();
	Tree defaultInstance = null;
	
	public void generate(List<Element> collected_elements){
		CustomStringBuilder builder = new CustomStringBuilder();
		builder.appendLn("package pattern01.helpers.generated;");
		builder.appendLn("");
		builder.appendLn(classHeaderComment);
		builder.appendLn("public class PatternInterface{");
		builder.appendLn(tabspace+"private "+treeNameSpace+" instance = null;");
		builder.appendLn(tabspace+"public void load("+treeNameSpace+" parentClass){");
		
		if(collected_elements != null && collected_elements.size() > 0){
			
			//El primer elemento de la definicion.
			defaultInstance = new Tree(null, 0);
			recursiveElementGeneration(defaultInstance, collected_elements, 0);
			
			//XPath xpath = XPathFactory.newInstance(new URI("file:///").getPath());
			
			
			builder.appendLn(tabgen(2)+"instance = "+
					"new "+treeNameSpace+"(parentClass,0);");
			builder.appendLn(tabgen(2)+"instance.setText("+quotscape+
					collected_elements.get(0).getPrettyName()+quotscape+");");
			builder.appendLn(tabgen(2)+"instance.setData(Type,"+quotscape+
					collected_elements.get(0).getPrettyName()+quotscape+");");
			builder.appendLn(tabgen(2)+"instance.setImage("+
					ImageHelper.getImage(prophelper.getProperty(collected_elements.get(0).getPrettyName()+"."+"Image")));

		}
		builder.appendLn(tabspace+"}");		
		builder.appendLn("}");
	}
	
	private void recursiveElementIteration(TreeItem parent, int index, XPath xpath, InputSource inputSoruce, CustomStringBuilder builder){
		String expression = "";
		try{
			if(parent.getItemCount() > 0){
				expression = parent.getData("Type").toString()+"/"+parent.getItem(index).getData("Type").toString()+"{"+index+"}";
				Node node = (Node) xpath.evaluate(expression, inputSoruce ,XPathConstants.NODE);
				for(int hindex = 0; hindex < node.getAttributes().getLength(); hindex++){
					parent.getItem(hindex).setData(node.getAttributes().item(hindex).getNodeName(), 
							node.getAttributes().item(hindex).getNodeValue().toString());
	
					recursiveElementIteration(parent.getItem(hindex), ++index, xpath, inputSoruce, builder);
				}
			}else{
				expression = parent.getParent().getData("Type").toString()+"/"+parent.getData("Type").toString();
				Node node = (Node) xpath.evaluate(expression, inputSoruce ,XPathConstants.NODE);
				for(int hindex = 0; hindex < node.getAttributes().getLength(); hindex++){
					parent.setData(node.getAttributes().item(hindex).getNodeName(), 
							node.getAttributes().item(hindex).getNodeValue().toString());
				}
			}
		}catch(XPathExpressionException e){
			e.printStackTrace();
		}
	}
	
	
	private void recursiveElementGeneration(Tree parent, List<Element> collected_elements, int index){
		if(collected_elements.get(index).getChildElements_collection().size() > 0){
			for(int jindex = 0; jindex < collected_elements.get(index).getChildElements_collection().size(); jindex++){
				recursiveElementGeneration(parent, collected_elements.get(index).getChildElements_collection(), jindex);
			}
		}else{
			TreeItem item = new TreeItem(parent, 0);
			item.setText(collected_elements.get(index).getClass().getName()+" - "+collected_elements.get(index).getName());
			item.setData("Type", collected_elements.get(index).getClass().getName());
			item.setImage(ImageHelper.getImage(prophelper.getProperty(collected_elements.get(0).getPrettyName()+"."+"Image")));
		}
	}
	
	private String tabgen(int tabnumber){
		String tab = "";
		for(int index = 0; index < tabnumber; index++){
			tab += tabspace;
		}
		return tab;
	}
}