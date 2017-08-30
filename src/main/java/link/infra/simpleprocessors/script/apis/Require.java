package link.infra.simpleprocessors.script.apis;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Require {
	
	Map<String, NativeApi> apis;
	
	public Require() {
		apis = new HashMap<String, NativeApi>();
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
	
	public NativeApi getApi(String name) {
		return apis.get(name);
	}
	
	public void addApi(NativeApi api) {
		apis.put(api.getName(), api);
	}

}
