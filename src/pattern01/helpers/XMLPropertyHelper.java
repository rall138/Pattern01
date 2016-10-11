package pattern01.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.eclipse.swt.widgets.Tree;
import org.xml.sax.InputSource;

import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.generated.PatternInstance;

/***
 * 
 * @author rlomez
 * Class made for read and write properties in xml files. 
 *
 */
public class XMLPropertyHelper {

//	public static Object getPropertyValue(String className, String path, 
//			String property){
		
//		XPathFactory factory = XPathFactory.newInstance();
//		XPath xpath = factory.newXPath();
//		InputSource source = new InputSource(classInstance.getPath());
//		xpath.evaluate(path, source, XPathConstants.NODE);
		
		
//	}
	
	public static void saveProperties(Tree patternInstance){
		//Project folder item
		String projectFolder = patternInstance.getItem(0)
				.getData("path").toString(); 
		
		PatternInstance classInstance = ((PatternInstance)patternInstance
				.getSelection()[0].
				getData("class_instance"));
		
		File instance = new File(LocationHelper
				.searchPatternFolderPath(projectFolder)
				+System.getProperty("separator")
				+classInstance.getName()+".xml");
		
		System.out.println(instance.getAbsolutePath());
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(instance));
			writer.write(classInstance.toXml());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}