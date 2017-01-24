package backend;

public class GameClosedTimer {
	
	private long programCloseTime;
	
	public GameClosedTimer() { }
	
	public long getCurrentTime() {
		return System.currentTimeMillis();
	}
	
	public void setProgramCloseTime(long l) {
		this.programCloseTime = l;
	}
	
	public long getProgramCloseTime() {
		return this.programCloseTime;
	}
	
}


