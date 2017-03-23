package audio;

import java.io.File;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */

public class BGM
{
	private boolean mute;
	private int value;
	private Clip clip;
	public FloatControl volume;
	public BooleanControl muteControl;
	private String linuxfile = "../Resources";
	private String windowsfile  = "../b4/Resources";
	
	/**
	 * Read the audio file and opens the clip
	 * @param value	The current volume of audio 
	 * @param s 	The audio file
	 */
	public BGM(int value, String s)
	{
		this.value = value;
		
		try {
//		      
//			AudioInputStream ais =
//				AudioSystem.getAudioInputStream(
//					getClass().getResourceAsStream(s)
//				);
//			AudioFormat baseFormat = ais.getFormat();
//			AudioFormat decodeFormat = new AudioFormat(
//				AudioFormat.Encoding.PCM_SIGNED,
//				baseFormat.getSampleRate(),
//				16,
//				baseFormat.getChannels(),
//				baseFormat.getChannels() * 2,
//				baseFormat.getSampleRate(),
//				false
//			);
//			AudioInputStream dais =
//				AudioSystem.getAudioInputStream(
//					decodeFormat, ais);
//			clip = AudioSystem.getClip();
//			clip.open(dais);
//			
			clip = AudioSystem.getClip();
				if(new File(windowsfile + s).exists()){
					
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File(windowsfile + s));
				clip.open(ais);
				} else {
				
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File(linuxfile + s));
				clip.open(ais);
				}
			
			
			
			volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);			
			muteControl = (BooleanControl)clip.getControl(BooleanControl.Type.MUTE);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
		
	/**
	 * 
	 * @return	The audio volume 
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * 
	 * @param value	The audio volume from slider	
	 */
	public void setValue(int value)
	{
		this.value = value;
			
	}
	
	/**
	 * 
	 * @return mute value
	 */
	public boolean getMuteStatus(){
		return mute;
	}
	
	/**
	 * 
	 * @param isMute value of mute when user click mute button
	 */
	public void setMuteStatus(boolean isMute){
		this.mute = isMute;
	}
	
	/**
	 * updates the audio volume 
	 * audio will turn off if mute==true
	 */
	public void update(){
		double dB1 = (double)value / 100;
		float gain1 = (float)(Math.log(dB1)/Math.log(10.0)*20.0);
		volume.setValue(gain1);
//		System.out.println("updating..");
		muteControl.setValue(mute);
	}
	
	/**
	 * starts the audio and keep updating for any changes
	 * audio will loop until stop() is called
	 */
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		//System.out.println("test");
		
//		while(clip.isOpen()){
//			double test = (double)value/100;
//			//System.out.println("dB :" + test + " <-> " + "Volume :" + value);
//			//System.out.println(mute);
//			update();
//		}

	}
	
	/**
	 * plays the audio once
	 */
	public void playOnce(){
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		update();
		clip.start();
		
	}
	
	/**
	 * stop the audio
	 */
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	/**
	 * close the audio line
	 */
	public void close() {
		stop();
		clip.close();
	}
	
	/**
	 * 
	 * @return audio play status
	 */
	public boolean isplay(){
		if(clip.isRunning()){
			return true;
		}else return false;
	}
	
		public String toString()
	{
		return "Volume : " + this.value + "%";
	}
}
