package pattern01.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import pattern01.helpers.CommonPathFix.PATH_NAME;

public class ImageHelper {

	public static URI getFileURI(String imageName){
		File file = null;
		file = new File(CommonPathFix.getHardCodedPath(PATH_NAME.ICONFOLDER).getPath()+
				imageName);
		return file.toURI();
	}
	
	public static ImageDescriptor getImageDescriptor(String imageName){
		try {
			return ImageDescriptor.createFromURL(getFileURI(imageName).toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Image getImage(String imageName){
		Image img = null;
		try{
			img = getImageDescriptor(imageName).createImage();
		}catch(Exception ex){
			ex.printStackTrace(System.err);
		}
		return img;
	}
	
	
}