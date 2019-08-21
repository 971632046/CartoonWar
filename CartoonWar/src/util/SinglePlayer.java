package util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class SinglePlayer extends Thread {
	private String mp3Name;
	public SinglePlayer() {
		// TODO Auto-generated constructor stub
	}
	public SinglePlayer(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	public void play (String mp3Name) {
		SinglePlayer singlePlayer = new SinglePlayer(mp3Name);
		singlePlayer.start() ;
	}
	
	
	@Override
	public void run() {
		InputStream resourceAsstream = SoundPlayer.class.getClassLoader().getResourceAsStream(mp3Name);
		try {
			AdvancedPlayer advancedPlayer = new AdvancedPlayer(resourceAsstream);
				advancedPlayer.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
