package audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * @author Jack Marshman - Changes to the class are aesthetic 
 */

public class BGMMuteButton extends JPanel{
	
	public JButton mute;
	
	/**
	 * Alternatively set boolean value to muteStatus when button is clicked
	 * @param model
	 */
	public BGMMuteButton(BGMModel model){
		
		super();
		
		mute = new JButton("Mute/Unmute");
		/*
		mute.setBounds(20, 450, 180, 100);
		ImageIcon btnControlsIcon = new ImageIcon(new ImageIcon("Resources/Images/controlsLogo1.png").getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
		mute.setIcon(btnControlsIcon);
		mute.setBorderPainted(false);
		*/
		
		mute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(model.getMuteStatus()==true){
					model.setMuteStatus(false);
				}else if (model.getMuteStatus()==false){
					model.setMuteStatus(true);
				}
				
			}
		});
		
		add(mute);
	}

}
