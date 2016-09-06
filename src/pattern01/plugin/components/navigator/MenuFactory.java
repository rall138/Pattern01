package pattern01.plugin.components.navigator;

import java.lang.reflect.Method;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import pattern01.helpers.instancegen.PatternInstanceParser;
import pattern01.plugin.components.editors.generated.DialogPatternEditor;
import pattern01.plugin.components.editors.generated.JFaceDialogPatternInstance;
import pattern01.plugin.components.editors.generated.PatternInstancePatternEditor;
import pattern01.plugin.components.editors.generated.RegisterPatternEditor;
import pattern01.plugin.components.editors.generated.RelatedFilePatternEditor;
import pattern01.plugin.components.editors.generated.SelectionPatternEditor;

@SuppressWarnings("unused")
public class MenuFactory {
	
	private Tree parent = null;

	public MenuFactory(){}
	
	public void generateDisplayableOptions(NodeType nodeType, Tree parent){
		this.parent = parent;
		Menu menu =  new Menu(parent);
		MenuItem auxiliarMenuItem = null;
		switch (nodeType) {
			case CLASS:
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
		propertiesListener(auxiliarMenuItem);
	}
	
	private void generateAddMenuOptions(NodeType nodeType, Menu menu){
		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("Add");
		switch (nodeType){
			case PATTERNINSTANCE:
				menuItem = new MenuItem(menu, SWT.PUSH);
				menuItem.setText("RelatedFiles");
				
				
		}
	}
	
	private void addElement(NodeType nodeType){
		TreeItem treeItem = new TreeItem(this.parent.getSelection()[0], 0);
		switch (nodeType){
			case RELATEDFILES:
				
		}
	}
	
	private void propertiesListener(MenuItem menu_item){
		menu_item.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				//TODO Flexibilizar esta logica				
				if(parent.getSelection()[0].getData("type") != null){
						try{
							DefaultEditorInput dei = new DefaultEditorInput();
							dei.setInstanceTree(parent);
							NodeType nodetype_pointer = (NodeType)parent.getSelection()[0].getData("type");
							if (nodetype_pointer == NodeType.PATTERNINSTANCE){
								JFaceDialogPatternInstance jfd = new JFaceDialogPatternInstance(parent.getShell());
								jfd.create();
								jfd.open();
								//page.openEditor(dei, PatternInstancePatternEditor.ID);
							}else if (nodetype_pointer == NodeType.RELATEDFILE){
								page.openEditor(dei, RelatedFilePatternEditor.ID);
							}else if (nodetype_pointer == NodeType.SELECTION){
								page.openEditor(dei, SelectionPatternEditor.ID);
							}else if (nodetype_pointer == NodeType.DIALOG){
								page.openEditor(dei, DialogPatternEditor.ID);
							}else if (nodetype_pointer == NodeType.REGISTER){
								page.openEditor(dei, RegisterPatternEditor.ID);
							}
						} catch (PartInitException e) {
							e.printStackTrace();
						}
					}
				}
		} );
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
