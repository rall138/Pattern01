package pattern01.plugin.components.navigator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;

@SuppressWarnings("unused")
public class MenuFactory {

	public MenuFactory(){}
	
	public void generateDisplayableOptions(NodeType nodeType, Tree parent){
		Menu menu = null;
		MenuItem auxiliarMenuItem = null;
		switch (nodeType) {
		case classType:
			menu = new Menu(parent);
			auxiliarMenuItem = new MenuItem(menu, SWT.CASCADE);
			auxiliarMenuItem.setText("Patterns");
			parent.setMenu(menu);

//			MenuItem auxiliarMenuItem2 = new MenuItem(menu, SWT.CASCADE);
//			auxiliarMenuItem2.setText("Workwith");
//			menu = new Menu(parent);
//			auxiliarMenuItem2.setMenu(menu);
//			
//			auxiliarMenuItem = new MenuItem(auxiliarMenu, SWT.PUSH);
//			auxiliarMenuItem.setText("Workwith");
			
			break;

		default:
			break;
		}
	}
}
