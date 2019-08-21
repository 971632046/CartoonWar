package util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
* @ClassName: SoundPlayer
* @Description: 播放音乐
* @author smh
* @date 2019年8月21日 下午6:51:02
*/
public class SoundPlayer extends Thread{
	private String mp3Name;
	public SoundPlayer() {
		// TODO Auto-generated constructor stub
	}
	public SoundPlayer(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	public void run() {
		for(;;){
			
	
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
}
