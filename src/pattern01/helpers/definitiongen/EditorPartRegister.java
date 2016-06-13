package pattern01.helpers.definitiongen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pattern01.helpers.temporal_containers.Element;

public class EditorPartRegister {

	private static final String sustitution_token = "<<CLASS_SUSTITUTION>>";
	private static final String regularExpression="(<extension)(\\s*)"
			+ "(point=\\\"org.eclipse.ui.editors\\\">)(\\s*)(<editor)(\\s*)"
			+ "(class=\\\""+sustitution_token+"\\\") (\\s*\\S*\\.*)*(</extension>)";

	//TODO Igualmente debe recibir el nombre del editor part no el prettyName() 
	//TODO (Ampliar logica para que contengan las classes generadas tal como así se continen el ID, tambien los nombres )
	//TODO en los elements.
	public static String generatePluginCode(String pluginCode, Element element){
		String replaceableCode = "";
		Pattern pattern = Pattern.compile(regularExpression.replace(sustitution_token, element.getPrettyName()));
		Matcher matcher = pattern.matcher(pluginCode);
		if (matcher.find()){
			 replaceableCode = matcher.group(0);
			 pluginCode.replace(replaceableCode, replacementCode);
		}
		return pluginCode;
	}
}