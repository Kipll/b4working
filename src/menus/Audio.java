package menus;

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
		ImageIcon btnBackIcon = MenuButtonHandler.loadImageIcon("Resources/Images/back_button_2.png", 180, 100);
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
		ImageIcon btnExitIcon = MenuButtonHandler.loadImageIcon("Resources/Images/exit_button_2.png", 180, 100);
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
		audioPanel.setOpaque(false);
		audioPanel.setBounds(0, 0, 900, 600);
		add(audioPanel);
		
		/*
		 * Background
		 */
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		ImageIcon backgroundIcon = MenuButtonHandler.loadImageIcon("Resources/Images/audio_background.png", 900, 600);
		background.setIcon(backgroundIcon);
		add(background);
	}
}
