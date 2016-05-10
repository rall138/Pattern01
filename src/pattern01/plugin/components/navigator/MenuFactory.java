package pattern01.plugin.components.navigator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import pattern01.helpers.instancegen.PatternInstanceParser;

@SuppressWarnings("unused")
public class MenuFactory {
	
	private Tree parent = null;

	public MenuFactory(){}
	
	public void generateDisplayableOptions(NodeType nodeType, Tree parent){
		this.parent = parent;
		Menu menu =  new Menu(parent);
		MenuItem auxiliarMenuItem = null;
		switch (nodeType) {
			case classType:
				auxiliarMenuItem = new MenuItem(menu, SWT.CASCADE);
				auxiliarMenuItem.setText("Patterns");
				parent.setMenu(menu);
	
				menu = new Menu(auxiliarMenuItem);
				auxiliarMenuItem.setMenu(menu);
				MenuItem auxiliarMenuItem2 = new MenuItem(menu, SWT.CASCADE);
				auxiliarMenuItem2.setText("Workwith");
				
				menu = new Menu(auxiliarMenuItem2);
				auxiliarMenuItem2.setMenu(menu);
				MenuItem applyfromDefaultInstance = new MenuItem(menu, SWT.PUSH);
				applyfromDefaultInstance.setText("Generate default instance");
				
				applyDefaultPatternListener(applyfromDefaultInstance);
				break;
			default:
				break;
		}
		if (parent.getMenu() == null){
			parent.setMenu(menu);
		}
		auxiliarMenuItem = new MenuItem(parent.getMenu(), SWT.PUSH);
		auxiliarMenuItem.setText("Properties");
	}
	
	private void applyDefaultPatternListener(MenuItem menu_item){
		menu_item.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				PatternInstanceParser parser = new PatternInstanceParser(parent.getSelection()[0]);
				parent.getSelection()[0].removeAll();
				parser.generateTreeFromDefaultDefinition();
				parent.getSelection()[0] = parser.getInstance();
			}
		});
	}
}
