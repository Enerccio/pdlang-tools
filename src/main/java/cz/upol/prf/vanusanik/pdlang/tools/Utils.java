package cz.upol.prf.vanusanik.pdlang.tools;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class Utils {

	public static void exportToTmp(byte[] classData, String fqName) {
		File f = new File("tmp");
		f.mkdirs();
		if (f.exists()){
			File expF = new File(f, fqName+".class");
			expF.getParentFile().mkdirs();
			try {
				FileUtils.writeByteArrayToFile(expF, classData);
			} catch (Exception e){
				
			}
		}
	}

	public static String mkDesc(String returnType, List<String> args) {
		return "(" + StringUtils.join(args, "") + ")" + returnType;
	}
	
	public static String dots2slashes(String path) {
		return path.replaceAll(Pattern.quote("."), "/");
	}
	
}
