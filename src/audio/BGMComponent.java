package audio;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSlider;

import menus.MenuButtonHandler;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 * @author Jack Marshman - Changes to the class are aesthetic 
 */

public class BGMComponent extends JPanel
{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param bgm BGM object
	 * @param min The minimum value of slider
	 * @param max The maximum value of slider
	 * @param initial The initial position of slider
	 */
	public BGMComponent(BGM bgm, int min, int max, int initial)
	{
		super();
		setLayout(null);
		
		//model
		BGMModel model = new BGMModel(bgm);
		
		//view
		BGMView est = new BGMView(model);
		
		//make view observe model
		model.addObserver(est);
		
		//create control
		JSlider slider = new JSlider(min, max, initial);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing((max - min) / 4);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels((max - min) / 4));
		slider.setBackground(Color.BLACK);
		slider.setForeground(Color.WHITE);
		slider.setBounds(200, 250, 500, 50);
		slider.setOpaque(false);
		
		
		//create listener
		BGMSliderListener listen = new BGMSliderListener(model, slider);
		
		//make listeners listen to controls
		slider.addChangeListener(listen);
		
		BGMMuteButton mute = new BGMMuteButton(model);
		mute.setOpaque(false);
		mute.setBounds(360, 320, 180, 100);
	
		
		est.setOpaque(false);
		est.setBounds(370, 100, 200, 200);
		//place views and controls on panel
		//Set locations
		add(slider);
		add(est);
		add(mute);
	}
}
