package link.infra.simpleprocessors.script;

import java.util.concurrent.Executors;
import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
import link.infra.simpleprocessors.script.apis.Console;
import link.infra.simpleprocessors.script.apis.Require;

public class SandboxRunner {

	private NashornSandbox sandbox;
	private Require require;
	private IRunEnv env;

	public SandboxRunner(Require req, IRunEnv environment) {
		sandbox = NashornSandboxes.create();
		sandbox.setMaxCPUTime(1000); // prevent while(true)
		sandbox.setExecutor(Executors.newSingleThreadExecutor());
		sandbox.setDebug(true);
		require = req;
		env = environment;
	}

	public void init() {
		// inject "native" functions into Nashorn
		sandbox.inject("console", new Console(env));
		sandbox.inject("require_native", require);
		injectRequireFix();
	}

	/*
		var exports = {};
		var require = (function(require_native) {
			return function(str) {
				var req = require_native.getApi(str);
				if (req == null) {
					var codeString = require_native.get(str);
					if (codeString) {
						var oldExports = exports;
						exports = {};
						eval(codeString);
						var fin = exports;
						exports = oldExports;
						return fin;
					} else {
						return null;
					}
				} else {
					return req;
				}
			}
		})(require_native);
		delete require_native;
	 */

	public void injectRequireFix() {
		// load require override with eval() to allow module loading
		sandbox.eval("var exports={},require=function(c){return function(b){var a=c.getApi(b);return null==a?(a=c.get(b))?(b=exports,exports={},eval(a),a=exports,exports=b,a):null:a}}(require_native);delete require_native;");
	}
	
	public Object evalCode(String code) {
		return sandbox.eval(code);
	}

}
