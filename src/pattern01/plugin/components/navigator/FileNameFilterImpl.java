package pattern01.plugin.components.navigator;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameFilterImpl implements FilenameFilter{

	@Override
	public boolean accept(File dir, String name) {
		if(name.endsWith(".xml")){
			return true;
		}
		return false;
	}
}