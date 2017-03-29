package pattern01.helpers;

import pattern01.helpers.definitiongen.EnumGenerator;
import pattern01.helpers.temporal_containers.Element;

public class DataTypeConversion {

	private static final String quotscape = "\"";
	private static final String simplequotscape = "'";	
	
	public static String getProcessedValue(String data_type, String value ){
		String processed_value = "";
		if (data_type.equalsIgnoreCase("boolean") || data_type.equalsIgnoreCase("java.lang.Boolean")){
			if (value.equalsIgnoreCase("") || value.equalsIgnoreCase("false") || value.equalsIgnoreCase("0")){
				processed_value = "false";
			}else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1")){
				processed_value = "true";
			}else{
				processed_value = "false";
			}
		}else if (data_type.equalsIgnoreCase("java.lang.String")){
			if (value == null){
				processed_value = quotscape+quotscape;
			}else{
				processed_value = quotscape+value+quotscape;				
			}
		}else if (data_type.equalsIgnoreCase("char")){
			if (value == null){
				processed_value = simplequotscape+simplequotscape;				
			}else{
				processed_value = simplequotscape+value+simplequotscape;				
			}
		}else if (value.contains("#{")){ //Custom type
			String processedType = data_type
					.substring(data_type.indexOf("#{")+2, data_type.indexOf("}"));
			processed_value = processedType+"."+value.substring(value.lastIndexOf(".")+1,value.indexOf("}"));
			processed_value = EnumGenerator.classPackage+"."+processed_value;
		}else {
			processed_value = value;
		}
		return processed_value;
	}
	
	/***
	 * This method receives the raw data type like #{Generator} and returns pattern01.helpers.generated.Generator 
	 * @param data_type
	 * @return
	 */
	public static String getProcessedType(String data_type){
		String processedType = "";
		if (data_type.contains("#{")){ //Custom type
			processedType = data_type
					.substring(data_type.indexOf("#{")+2, data_type.indexOf("}"));
		}
		return processedType;
	}
	
	public static String getDataTypeWrapper(String data_type, String value){
		String processed_wrapper = "";
		if (data_type.equalsIgnoreCase("boolean") || data_type.equalsIgnoreCase("java.lang.Boolean")){
			processed_wrapper = "java.lang.Boolean.valueOf("+value+").booleanValue()";
		}else if (data_type.equalsIgnoreCase("int") || data_type.equalsIgnoreCase("java.lang.Integer")){
			processed_wrapper = "java.lang.Integer.valueOf("+value+").intValue();";
		}else if (data_type.contains("#{")){ //Custom type
			String processedType = data_type
					.substring(data_type.indexOf("#{")+2, data_type.indexOf("}"));
			processed_wrapper = Element.classPackage+"."+processedType+".valueOf("+value+")";
		}else{
			processed_wrapper = value;
		}
		return processed_wrapper;
	}
}
