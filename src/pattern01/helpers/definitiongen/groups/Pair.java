package pattern01.helpers.definitiongen.groups;

/***
 * Class generated for attribute group generation.
 * @author rlomez
 *
 */
public class Pair {
	
	private Object name;
	private Object value;

	public Pair(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public Object getName(){
		return this.name;
	}
	
	public Object getValue(){
		return this.value;
	}
}
