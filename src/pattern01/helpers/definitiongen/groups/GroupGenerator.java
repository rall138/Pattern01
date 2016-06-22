package pattern01.helpers.definitiongen.groups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.PropertyHelper;
import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.Element;

public class GroupGenerator {
	
	private Element element = null;
	private List<Attribute> attributeCollection = null;
	private List<Pair> pairCollection = null;
	
	public GroupGenerator(Element element, List<Attribute> attributeCollection){
		this.element = element;
		this.attributeCollection = attributeCollection;
		this.pairCollection = new ArrayList<>();
		generatePairCollection();
		sortCollectionByGroup();
	}
	
	private void generatePairCollection(){
		String group = "", processedName = "", input = "";
		PropertyHelper phelper = new PropertyHelper();
		for(Attribute attr : this.attributeCollection){
			processedName = element.getPrettyName()+"."+attr.getPrettyName()+".Group";
			input = CommonPathFix.getHardCodedPath(PATH_NAME.CUSTOMPROPERTIES_PROPERTIES).getPath();
			group = phelper.getProperty(input, processedName);
			this.pairCollection.add(new Pair(attr.getPrettyName(), group));
		}
	}
	
	private void sortCollectionByGroup(){
		GroupCollectionSorter sorter = new GroupCollectionSorter();
		Collections.sort(this.pairCollection, sorter);
	}
	
	// Inmutable list collection
	public List<Pair> getPairCollection(){
		return new ArrayList<Pair>(this.pairCollection);
	}
}
