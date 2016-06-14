package pattern01.helpers.definitiongen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.temporal_containers.Element;

public class EditorPartRegister {

	private static final String tabspace = "\t";
	private static final String quotscape = "\"";	
	private static final String sustitution_token = "<<EDITOR_NAME>>";
	private static final String sustitution_prettyNameToken = "<<PRETTY_NAME>>";
	private static final String regularExpression = "(<extension)(\\s*)(point=\\\"org.eclipse.ui.editors\\\">)"
			+ "(\\s*)(<editor)(\\s*)"
			+ "(class=\\\""+sustitution_token+"\\\")"
			+ "(\\s*\\S*\\.*)*(</extension>)";
	
	private static final String editorDeclaration = tabGen(1)+"<extension"+System.lineSeparator()
         +tabGen(2)+"point="+quotscape+"org.eclipse.ui.editors"+quotscape+">"+System.lineSeparator()
         +tabGen(2)+"<editor"+System.lineSeparator()
            +tabGen(3)+"class="+quotscape+sustitution_token+quotscape+System.lineSeparator()
            +tabGen(3)+" default=\"false\""+System.lineSeparator()
            +tabGen(3)+" id="+quotscape+sustitution_token+quotscape+System.lineSeparator()
            +tabGen(3)+" name="+quotscape+sustitution_prettyNameToken+quotscape+">"+System.lineSeparator()
        +tabGen(2)+"</editor>"+System.lineSeparator()
        +tabGen(1)+"</extension>";

	public static String generatePluginCode(Element element){
		String pluginCode = getPluginCode();
		String processedRegEx = regularExpression
				.replace(sustitution_token,Element.editorPackage+"."+element.getPrettyName()+Element.postFix);
		
		System.out.println("RegEx:"+processedRegEx);
		Pattern pattern = Pattern.compile(processedRegEx);
		Matcher matcher = pattern.matcher(pluginCode);
		if (!matcher.find()){
			System.out.println("Matcher matches:"+matcher.matches());
			String processedSustitution = editorDeclaration
					.replace(sustitution_token, Element.editorPackage+"."+element.getPrettyName()+Element.postFix)
					.replace(sustitution_prettyNameToken, element.getPrettyName()+Element.postFix);
			
			pluginCode = pluginCode.replace("</plugin>", processedSustitution+System.lineSeparator()+"</plugin>");
		}
		return pluginCode;
	}
	
	private static String getPluginCode(){
		CustomStringBuilder pluginCode = new CustomStringBuilder();
		try {
			BufferedReader bfr = 
					new BufferedReader(new FileReader(
							new File(CommonPathFix.getHardCodedPath(PATH_NAME.MANIFEST_XML).getPath())));
			String line = "";
			while((line = bfr.readLine()) != null){
				pluginCode.appendLn(line);
			}
			bfr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pluginCode.toString();
	}
	
	private static String tabGen(int quantity){
		String tabappender = "";
		for(int total = 0; total < quantity; total++){
			tabappender += tabspace;
		}
		return tabappender;
	}

}