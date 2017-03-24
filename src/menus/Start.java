package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import audio.BGM;


/*
 * @author Jack Marshman
 */


//TODO
//Create and add graphics to new JButtons w/ Miruna
//Comment Start.java correctly
//Add graphics to resources
public class Start extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mainframe m;
	public JButton btnFindGame;
	public JButton btnHostGame;
	public JButton btnControls;
	public JButton btnAudio;
	public JButton btnExit;
	private BGM click;
	public String test = null; //JUnit
	
	public Start(Mainframe m)
	{
		
		super();
		this.m = m;
		click = new BGM(10,"/Music/SFX_Click.wav");
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		
		
		/*
		 * Find Game button
		 */
		btnFindGame = new JButton();
		btnFindGame.setBounds(42, 176, 360, 100);
		
		
		ImageIcon btnFindGameIcon = MenuButtonHandler.loadImageIcon("Resources/Images/find_game_button_2.png", 360, 100);
		
		btnFindGame.setIcon(btnFindGameIcon);
		btnFindGame.setBorderPainted(false);
		btnFindGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				click.playOnce();
				//test="find";
				m.setMenu(5);
			}
		});
		add(btnFindGame);
		
		/*
		 * Host Game button
		 */
		btnHostGame = new JButton();
		btnHostGame.setBounds(486, 176, 360, 100);
		
		ImageIcon btnHostGameIcon = MenuButtonHandler.loadImageIcon("Resources/Images/host_game_button_2.png", 360, 100);
		
		
		btnHostGame.setIcon(btnHostGameIcon);
		btnHostGame.setBorderPainted(false);
		btnHostGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				m.setMenu(4);
				
			}
		});
		add(btnHostGame);
		
		/*
		 * Controls button
		 */
		
		ImageIcon btnControlsIcon  = MenuButtonHandler.loadImageIcon("Resources/Images/controls_button_2.png", 180, 100);
		
		btnControls = new JButton();
		btnControls.setBounds(20, 450, 180, 100);
		
		btnControls.setIcon(btnControlsIcon);
		btnControls.setBorderPainted(false);
		btnControls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{	
				click.playOnce();
				test="control";
				m.setMenu(2);
			}
		});
		add(btnControls);
		
		/*
		 * Audio button
		 */
		btnAudio = new JButton();
		btnAudio.setBounds(351, 450, 180, 100);
		
		ImageIcon btnAudioIcon = MenuButtonHandler.loadImageIcon("Resources/Images/audio_button_2.png", 180, 100);
		
		
		btnAudio.setIcon(btnAudioIcon);
		btnAudio.setBorderPainted(false);
		btnAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				test="audio";
				m.setMenu(3);
			}
		});
		add(btnAudio);
		
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
		 * Background
		 */
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		ImageIcon backgroundIcon  = MenuButtonHandler.loadImageIcon("Resources/Images/betrayal_background.png", 900,600);
		background.setIcon(backgroundIcon);
		add(background);
		
		
	}
}