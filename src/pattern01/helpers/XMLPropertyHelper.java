package pattern01.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import org.eclipse.swt.widgets.TreeItem;

import pattern01.helpers.generated.IPatternElement;
import pattern01.helpers.generated.PatternInstance;

/***
 * 
 * @author rlomez
 * Class made for read and write properties in xml files. 
 *
 */
public class XMLPropertyHelper {

	
	public static void saveProperties(TreeItem selectedTreeItem){
		UUID reference = UUID.fromString(selectedTreeItem.getData("reference").toString());
		
		//Project folder item
		String projectFolder = selectedTreeItem.getParent().getData("project_path").toString(); 
		
		IPatternElement patternInstanceObj = null;
		if (!(selectedTreeItem.getData("class_instance") instanceof PatternInstance)){
			//Obtenemos la clase asociada al primer elemento del pattern
			patternInstanceObj = (IPatternElement)selectedTreeItem.getData("parent_reference");
		}else{
			patternInstanceObj = (IPatternElement)selectedTreeItem.getData("class_instance");
		}
		
		saveProperties(patternInstanceObj, reference, projectFolder);
	}
	
	
	public static void saveProperties(IPatternElement patternInstanceObj, UUID reference, String projectFolder){

		try {
			File instance = new File(LocationHelper.searchPatternFolderPath(projectFolder)
					+System.getProperty("file.separator")+reference+".xml");
			
			//Eliminamos el archivo y lo volvemos a crear
			instance.delete();
			
			instance = new File(LocationHelper.searchPatternFolderPath(projectFolder)
					+System.getProperty("file.separator")+reference+".xml");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(instance));
			writer.write(((PatternInstance)patternInstanceObj).toXml());
			writer.flush();
			writer.close();
	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}