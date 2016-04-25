package pattern01.plugin.components.navigator;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import pattern01.helpers.location.LocationHelper;
import pattern01.helpers.location.LocationHelper.RL_PLUGIN;

public class ImageHelper {

	public static URL getFileUrl(String imageName){
		String iconPath = "icons/";
		Path path = new Path(iconPath + imageName);
		URL fileUrl = null;
		try {
			fileUrl = new URI(LocationHelper.getPluginPath(RL_PLUGIN.PATTERN01)).toURL();
			fileUrl = new URL(fileUrl.getPath()+path);
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
		}
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