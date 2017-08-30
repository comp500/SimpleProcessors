package link.infra.simpleprocessors.script;

import java.util.concurrent.Executors;
import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
import link.infra.simpleprocessors.script.apis.Console;
import link.infra.simpleprocessors.script.apis.Require;

public class SandboxRunner {

	private NashornSandbox sandbox;

	public SandboxRunner() {
		sandbox = NashornSandboxes.create();
		sandbox.setMaxCPUTime(1000); // prevent while(true)
		sandbox.setExecutor(Executors.newSingleThreadExecutor());
		sandbox.setDebug(true);
	}

	public void init() {
		// inject "native" functions into Nashorn
		sandbox.inject("console", new Console());
		sandbox.inject("require_native", new Require());
		injectRequireFix();
	}

	/*
		var module = {exports: {}};
		var exports = module.exports;
		var require = (function(require_native) {
			return function(str) {
				var req = require_native.getnative(str);
				if (req == null) {
					var codeString = require_native.get(str);
					if (codeString) {
						var oldModule = module;
						var oldExports = exports;
						module = {exports: {}};
						exports = module.exports;
						eval(codeString);
						var fin = module.exports;
						module = oldModule;
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
		sandbox.eval("var module={exports:{}},exports=module.exports,require=function(d){return function(a){var b=d.getnative(a);if(null==b){var c=d.get(a);return c?(a=module,b=exports,module={exports:{}},exports=module.exports,eval(c),c=module.exports,module=a,exports=b,c):null}return b}}(require_native);delete require_native;");
	}
	
	public Object evalCode(String code) {
		return sandbox.eval(code);
	}

}
