package pattern01.helpers.definitiongen.groups;

import java.util.Comparator;

public class GroupCollectionSorter implements Comparator<Pair>{

	@Override
	public int compare(Pair pair1, Pair pair2) {
		return pair1.getValue().toString().compareTo(pair2.getValue().toString());
	}
	
}
