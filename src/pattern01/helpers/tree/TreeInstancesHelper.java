package pattern01.helpers.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.TreeItem;

import pattern01.helpers.temporal_containers.Element;

public class TreeInstancesHelper {
	
	private static TreeInstancesHelper helper;
	private List<TreeItem> treeinstances = new ArrayList<>();
	
	private TreeInstancesHelper(){}
	
	public static TreeInstancesHelper getSingleton(){
		if (helper == null)
			helper = new TreeInstancesHelper();
		return helper;
	}
	
	public void addIntoList(TreeItem item){
		this.treeinstances.add(item);
	}
	
	public boolean removeFromList(TreeItem item){
		return treeinstances.remove(item);
	}
	
	public void changeDirtyPropertyOnInstance(TreeItem item, boolean dirty){
		treeinstances.get(treeinstances.indexOf(item)).setData("dirty", dirty);
	}
	
	public List<Element> getDirtyInstances(){
		ArrayList<Element> dirtyList = new ArrayList<>();
		for(TreeItem item : treeinstances){
			if (Boolean.getBoolean(item.getData("dirty").toString())){
				dirtyList.add((Element)item.getData("class_instance"));
			}
		}
		return dirtyList;
	}

	public List<TreeItem> getDirtyTreeItems(){
		ArrayList<TreeItem> dirtyList = new ArrayList<>();
		for(TreeItem item : treeinstances){
			if (Boolean.getBoolean(item.getData("dirty").toString())){
				dirtyList.add(item);
			}
		}
		return dirtyList;
	}
	
	//Inmutable list
	public List<TreeItem> getTreeInstances(){
		return new ArrayList<>(treeinstances);
	}

}
