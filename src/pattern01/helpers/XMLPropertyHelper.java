package pattern01.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import org.eclipse.swt.widgets.TreeItem;

import pattern01.helpers.generated.PatternInstance;

/***
 * 
 * @author rlomez
 * Class made for read and write properties in xml files. 
 *
 */
public class XMLPropertyHelper {

	public static void saveProperties(TreeItem selectedTreeItem){

		//La referencia es el verdadero identificador de la instancia
		UUID reference = UUID.fromString(selectedTreeItem.getData("reference").toString());
		
		//Project folder item
		String projectFolder = selectedTreeItem.getParent().getData("project_path").toString(); 
		
		PatternInstance patternInstanceObj = null;
		if (!(selectedTreeItem.getData("class_instance") instanceof PatternInstance)){
			//Obtenemos la clase asociada al primer elemento del pattern
			patternInstanceObj = (PatternInstance)selectedTreeItem.getData("parent_reference");
		}else{
			patternInstanceObj = (PatternInstance)selectedTreeItem.getData("class_instance");
		}
		
		try {
			File instance = new File(LocationHelper.searchPatternFolderPath(projectFolder)
					+System.getProperty("file.separator")+reference+".xml");
			
			//Eliminamos el archivo y lo volvemos a crear
			instance.delete();
			
			instance = new File(LocationHelper.searchPatternFolderPath(projectFolder)
					+System.getProperty("file.separator")+reference+".xml");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(instance));
			writer.write(patternInstanceObj.toXml());
			writer.flush();
			writer.close();
	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}