
public class PlayBoolean {
	
	private boolean playing;
	
	public PlayBoolean() {
		playing =  false;
	}
	
	public void play() {
		playing = true;
	}
	
	public void endPlay() {
		playing = false;
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
}
