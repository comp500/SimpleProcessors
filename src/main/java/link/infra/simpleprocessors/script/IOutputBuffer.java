package link.infra.simpleprocessors.script;

public interface IOutputBuffer {
	
	public void addLine(String msg);
	
	public void addInfo(String msg);
	
	public void addWarn(String msg);
	
	public void addErr(String msg);

}
