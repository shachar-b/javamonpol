
/**
 * 
 */
package ui.utils;

import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;


/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class SoundAffect {
	
	AudioInputStream  sound; 
	AudioFormat audioFormat ;
	
	public SoundAffect(String SoundPath) {
		File source;
		try {
			source =new File(SoundPath);
			sound=AudioSystem.getAudioInputStream(source);
		} catch (Exception e) {
			throw new RuntimeException("missing component or IO Erorr :"+SoundPath);
		}
	}

}
