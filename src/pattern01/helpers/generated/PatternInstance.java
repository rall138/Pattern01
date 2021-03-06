
package pattern01.helpers.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Sat Mar 25 22:48:29 UYT 2017
	* Creator: rlomez
	**/
public class PatternInstance implements IPatternElement{
	private Generator generator = pattern01.helpers.generated.Generator.WEB;
	private PatternType type = pattern01.helpers.generated.PatternType.WORKWITH;
	private java.lang.String name;
	private java.lang.String description;
	private Form patternInstance_form;

	public Generator getGenerator(){
		return this.generator;
	}

	public void setGenerator(Generator generator){
		this.generator = generator;
	}

	public PatternType getType(){
		return this.type;
	}

	public void setType(PatternType type){
		this.type = type;
	}

	public java.lang.String getName(){
		return this.name;
	}

	public void setName(java.lang.String name){
		this.name = name;
	}

	public java.lang.String getDescription(){
		return this.description;
	}

	public void setDescription(java.lang.String description){
		this.description = description;
	}

	public Form getpatternInstance_form(){
		return this.patternInstance_form;
	}

	public void setForm(Form patternInstance_form){
		this.patternInstance_form = patternInstance_form;
	}

	public java.lang.String getProperty(java.lang.String propertyName){
		java.util.Properties properties = new java.util.Properties();
		try{
			java.io.FileInputStream input = new java.io.FileInputStream("Custom.properties");
			properties.load(input);
		} catch (java.io.IOException e) {
			e.printStackTrace(System.err);
		}
		return properties.getProperty(propertyName);
	}

	@Override
	public java.lang.String toXml(){
		java.lang.String xml="<PatternInstance ";
		xml+="generator='"+this.generator+"' ";
		xml+="type='"+this.type+"' ";
		xml+="name='"+this.name+"' ";
		xml+="description='"+this.description+"' ";
		xml+=">";
		xml+=this.patternInstance_form.toXml();
		xml+="</PatternInstance>";
		return xml;
	}

	@Override
	public IPatternElement fromXml(java.lang.String xmlDocument){
		java.lang.String xpathuri = "null";
		return null;
	}
}