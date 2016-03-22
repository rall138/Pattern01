package pattern01;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class PatternNav extends ViewPart{

	Action searchPatternAction;
	
	@Override
	public void createPartControl(Composite parent) {
		searchPatternAction = new Action("search..") {
			public void run(){
				//Aca va el código de la acción
			}
		};
		searchPatternAction.setImageDescriptor(getImageDescriptor("linux.png"));
		
		
		Label label = new Label(parent, 0);
		label.setText("Nodo padre de view");
	
	}
	
	private ImageDescriptor getImageDescriptor(String path){
		String iconPath= "src\\icons\\";

		try{
			return ImageDescriptor.createFromURL(new URL(iconPath + path));
		}catch(MalformedURLException ex){
			ex.printStackTrace(System.err);
		}
		return null;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
