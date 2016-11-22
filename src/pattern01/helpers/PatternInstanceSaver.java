package pattern01.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;

import pattern01.helpers.generated.Patterninstance;

public class PatternInstanceSaver {
	
	public static void SaveInstance(PatternInstance patternInstance){
		try {
			URI uri = new URI("file:///"+LocationHelper.getActiveWorkSpace());
			File workspaceFolder = new File(uri);
			File projectFolder = null;
			for(int index = 0; index < workspaceFolder.listFiles().length; index++){
				if(workspaceFolder.listFiles()[index].isDirectory()){
					int hindex = 0;
					boolean itemFound = false;
					projectFolder = workspaceFolder.listFiles()[index];					
					while (hindex < projectFolder.listFiles().length && !itemFound){
						if (projectFolder.listFiles()[hindex].isDirectory() &&
								projectFolder.listFiles()[hindex].getName().equalsIgnoreCase("patternfolder")){
							itemFound = true;
						}else{
							hindex++;
						}
					}
					if (itemFound){
						generateFile(projectFolder.listFiles()[hindex], patternInstance);
					}
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private static void generateFile(File patternFolder, PatternInstance patternInstance){
		File instance_xml;
		try {
			instance_xml = new File(new URI("file:///"+patternFolder.toURI()+"/"+
					patternInstance.getName()+".xml"));
			OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream(instance_xml));
			fos.write(patternInstance.toXml());
			fos.flush();
			fos.close();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}

}
