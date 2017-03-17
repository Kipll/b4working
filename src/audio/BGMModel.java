package audio;

import java.util.Observable;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */

//Model of Easter class. 

public class BGMModel extends Observable 
{
	private BGM bgm;
	
	public BGMModel(BGM bgm)
	{
		super();
		this.bgm = bgm;
	}
	
	/**
	 Get the value of volume
	 @return value of volume
	 */
	public int getValue()
	{
		return bgm.getValue();
	}
	
	/**
	 * Get the value of mute
	 * @return value of mute
	 */
	public boolean getMuteStatus(){
		return bgm.getMuteStatus();
	}

	/**
	 * Change the mute value
	 * @param mute The value of mute
	 */
	public void setMuteStatus(boolean mute){
		bgm.setMuteStatus(mute);
		bgm.update();
		setChanged();
		notifyObservers();
	}
	
	/**
	 Change the value of volume 
	 @param value The value of volume
	 */
	public void setValue(int value)
	{
		bgm.setValue(value);
		bgm.update();
		setChanged();
		notifyObservers();
	}
}
