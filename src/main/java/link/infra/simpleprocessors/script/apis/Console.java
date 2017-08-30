package link.infra.simpleprocessors.script.apis;

import link.infra.simpleprocessors.script.IOutputBuffer;
import link.infra.simpleprocessors.script.IRunEnv;

public class Console {
	
	private IOutputBuffer buffer;
	
	public Console(IRunEnv env) {
		buffer = env.getOutputBuffer();
	}
	
	public void log(String str) {
		buffer.addLine(str);
	}
	
	public void error(String str) {
		buffer.addErr(str);
	}
	
	public void info(String str) {
		buffer.addInfo(str);
	}
	
	public void warn(String str) {
		buffer.addWarn(str);
	}

}
