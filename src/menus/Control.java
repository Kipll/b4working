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

import audio.BGM;
import menus.Mainframe;

/*
 * @author Jack Marshman
 */

//TODO
//Create and add graphics to new JButtons w/ Miruna
//Update controls graphic to fix background graphic
//Add graphics to resources
public class Control extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Mainframe m;
	
	public JButton btnBack;
	public JButton btnExit;
	public String test = null; //JUnit
	private BGM click;
	
	public Control(Mainframe m) 
	{
		
		super();
		this.m = m;
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
				test = "back";
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
		 * Controls graphic
		 */
		int controlsWidth = 700;
		int controlsHeight = 240;
		JLabel controlsLabel = new JLabel();
		controlsLabel.setBounds(95, 172, controlsWidth, controlsHeight);
		ImageIcon controlsIcon = new ImageIcon(new ImageIcon("Resources/Images/controls4.png").getImage().getScaledInstance(controlsWidth, controlsHeight, Image.SCALE_DEFAULT));
		controlsLabel.setIcon(controlsIcon);
		add(controlsLabel);	
		
		/*
		 * Background JLabel
		 */
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		background.setIcon(new ImageIcon(new ImageIcon("Resources/Images/controls_background.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT)));
		add(background);
	}
}
