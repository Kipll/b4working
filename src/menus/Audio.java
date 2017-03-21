package menus;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import audio.*;
import menus.Mainframe;

/*
 * @author Jack Marshman
 */

//TODO
//Finish general menu layout
//Create and add graphics to new JButtons w/ Miruna
//Add graphics to resources
public class Audio extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mainframe m;

	public BGM click;
	public JButton btnBack;
	public JButton btnExit;
	private BGM bgm;
	
	public String test = null; //for JUnit 
	
	public Audio(Mainframe m, BGM bgm) 
	{
		super();
		this.m = m;
		this.bgm = bgm;
		click = new BGM(10,"/Music/SFX_Click.wav");
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		
		
		/*
		 * Back button
		 */
		btnBack = new JButton();
		btnBack.setBounds(20, 450, 180, 100);
		ImageIcon btnBackIcon = new ImageIcon(new ImageIcon("Resources/Images/back_button.png").getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
		btnBack.setIcon(btnBackIcon);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				test="back";
				m.setMenu(1);
			}
		});
		add(btnBack);
		
		/*
		 * Exit button		
		 */
		btnExit = new JButton();
		btnExit.setBounds(694, 450, 180, 100);
		ImageIcon btnExitIcon = new ImageIcon(new ImageIcon("Resources/Images/exit_button.png").getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
		btnExit.setIcon(btnExitIcon);
		btnExit.setBorderPainted(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				test = "exit";
				System.exit(0);
			}
		});
		add(btnExit);
	
		/*
		 * Audio Controls
		 */
		BGMComponent audioPanel = new BGMComponent(bgm, 0, 100, 50);
		audioPanel.setBounds(100, 100, 500, 500);
		this.add(audioPanel);
			
		/*
		 * Audio title graphic
		 */
		/*
		JLabel titleLabel = new JLabel();
		titleLabel.setBounds(246, 11, 426, 128);
		ImageIcon titleIcon = new ImageIcon(new ImageIcon("Resources/Images/audioLogo1.png").getImage().getScaledInstance(426, 128, Image.SCALE_DEFAULT));
		titleLabel.setIcon(titleIcon);
		add(titleLabel);
		*/
		/*
		 * Background
		 */
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		background.setIcon(new ImageIcon(new ImageIcon("Resources/Images/audio_background.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT)));
		add(background);
	}
}
