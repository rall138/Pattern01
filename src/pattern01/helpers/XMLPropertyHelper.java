package pattern01.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Node;

import pattern01.helpers.generated.PatternInstance;
import pattern01.plugin.components.navigator.NodeType;

/***
 * 
 * @author rlomez
 * Class made for read and write properties in xml files. 
 *
 */
public class XMLPropertyHelper {

	public static void saveProperties(Tree fullPatternInstance){

		//La referencia es el verdadero identificador de la instancia
		byte reference = Byte.valueOf(fullPatternInstance.getSelection()[0]
				.getData("reference").toString());
		
		//Project folder item
		String projectFolder = fullPatternInstance.getItem(0)
				.getData("path").toString(); 
		
		//Obtenemos la clase asociada al primer elemento del pattern
		PatternInstance classInstance = 
				(PatternInstance)getItemByType(fullPatternInstance
				.getItem(0), NodeType.PATTERNINSTANCE).getData("class_instance");
		

		//Obtenemos el mapper para cambiar la línea de esta pattern
		File mapper = new File(LocationHelper
				.searchPatternFolderPath(projectFolder)
				+System.getProperty("file.separator")
				+"Mapper.xml");
		
		TreeItem packageItem =
				getItemByType(fullPatternInstance.getItem(0), NodeType.PACKAGE);
		
		//Obtención de nodo clase
		TreeItem classItem = 
				getItemByType(fullPatternInstance.getItem(0), NodeType.CLASS);
		
		try {
			
			String packageName = packageItem.getData("package").toString(), 
					className = classItem.getData("name").toString(),
					expression = ""; 
			
			//Modificamos la propiedad name
			XPath xpath = XPathFactory.newInstance().newXPath();
			
			expression = "*//Pattern[@type='WWPattern' and @reference='"+reference+"']";
			
			System.out.println("Expressiones: "+expression);
			
			Node node = (Node) xpath.evaluate(expression, mapper, XPathConstants.NODE);
			
			node.getAttributes().getNamedItem("name")
			.setNodeValue(classInstance.getName());

			File instance = new File(LocationHelper
					.searchPatternFolderPath(projectFolder)
					+System.getProperty("file.separator")
					+classInstance.getName()+".xml");
			
			//Eliminamos el archivo y lo volvemos a crear
			instance.delete();
			
			instance = new File(LocationHelper
					.searchPatternFolderPath(projectFolder)
					+System.getProperty("file.separator")
					+classInstance.getName()+".xml");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(instance));
			writer.write(classInstance.toXml());
			writer.flush();
			writer.close();
	
			
		} catch (IOException |XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	private static TreeItem getItemByType(TreeItem item, NodeType type){
		if (item.getData("type")==type){
			return item;
		}else{
			if (item.getItems().length > 0){
				for (TreeItem it: item.getItems()){
					item = getItemByType(it, type);
				}
			}
		}
		return item;
	}

}