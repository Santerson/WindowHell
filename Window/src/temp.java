
public class temp {
	
	private boolean playing;
	
	public temp() {
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
