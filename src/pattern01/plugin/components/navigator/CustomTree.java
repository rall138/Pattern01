package pattern01.plugin.components.navigator;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import pattern01.helpers.generated.Generator;
import pattern01.helpers.generated.PatternInstance;

public class CustomTree extends Tree{
	
	public CustomTree(Composite parent, int style){
		super(parent, style);
	}
	
	public void toXml(TreeItem treeItemPatternInstance){
		PatternInstance patternInstance = new PatternInstance();
		patternInstance.setName(treeItemPatternInstance.getData("name").toString());
		patternInstance.setGenerator((Generator)treeItemPatternInstance.getData("generator"));
		if ()
	
	}

	private String recursiveToXml(TreeItem currentItem, String xml){
		if (currentItem.getData("type")!= null && 
				((NodeType)currentItem.getData("type")) == NodeType.patternInstanceType){
			PatternInstance patternInstance = new PatternInstance();
			patternInstance.setName(currentItem.getData("name").toString());
			patternInstance.setGenerator((Generator)currentItem.getData("generator"));
		}else if (((NodeType)currentItem.getData("type")) == NodeType.))
	}
}
