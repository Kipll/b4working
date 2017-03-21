package audio;

import javax.swing.JLabel;
import java.util.Observer;
import java.awt.Color;
import java.util.Observable;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 * @author Jack Marshman - Changes to the class are aesthetic
 */
//Text view of date of BGM gui

public class BGMView extends JLabel implements Observer
{
	private BGMModel model;
	
	/**
	 Initialise view with model.
	 @param model The underlying bgm model
	 */
	public BGMView(BGMModel model)
	{
		super();
		this.model = model;
		
		boolean mute = model.getMuteStatus();
		int value = model.getValue();
		setText("Volume : " + value + "%");
		
		/*
		 * Changes colour of "Volume"
		 */
		setForeground(Color.WHITE);

	}
	
	public void update(Observable obs, Object obj)
	{
		boolean mute = model.getMuteStatus();
		int value = model.getValue();
		setText("Volume : " + value + "%");

	}

}
