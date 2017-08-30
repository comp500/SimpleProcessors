package link.infra.simpleprocessors.script.apis;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Require {
	
	public Object getnative(String msg) {
		return null;
	}
	
	public String get(String msg) {
		// todo fix to read from dirs
		try {
			return new String(Files.readAllBytes(Paths.get(msg)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
