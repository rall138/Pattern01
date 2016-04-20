package pattern01.plugin.components.navigator;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

public class ImageHelper {

	public static URL getFileUrl(String imageName){
		String iconPath = "icons/";
		Bundle bundle = Platform.getBundle("Pattern01");
		Path path = new Path(iconPath + imageName);
		URL fileUrl = FileLocator.find(bundle, path, null);
		return fileUrl;
	}
	
	public static ImageDescriptor getImageDescriptor(String imageName){
		return ImageDescriptor.createFromURL(getFileUrl(imageName));
	}
	
	public static Image getImage(String imageName){
		Image img = null;
		try{
			img = ImageDescriptor.createFromURL(getFileUrl(imageName)).createImage();
		}catch(Exception ex){
			ex.printStackTrace(System.err);
		}
		return img;
	}
	
	
}