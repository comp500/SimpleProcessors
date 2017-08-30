package link.infra.simpleprocessors.script;

import java.util.concurrent.Executors;

import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;

public class SandboxRunner {
	
	private NashornSandbox sandbox;
	
	public SandboxRunner() {
		sandbox = NashornSandboxes.create();
		sandbox.setMaxCPUTime(1000); // prevent while(true)
		sandbox.setExecutor(Executors.newSingleThreadExecutor());
		sandbox.setDebug(true);
	}

}
