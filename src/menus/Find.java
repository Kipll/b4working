package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import audio.BGM;
import game.Main;


/*
 * @author Jack Marshman
 */
public class Find extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Mainframe m;
	private BGM click;
	public JButton btnJoinGame;
	public JButton btnBack;
	public JTextField enterIP;

	public Find(Mainframe m)
	{
		super();
		this.m = m;
		click = new BGM(10,"/Music/SFX_Click.wav");
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		/*
		 * Join Game Button
		 */
		btnJoinGame = new JButton();
		btnJoinGame.setBounds(694, 450, 180, 100);
		ImageIcon btnJoinGameIcon = new ImageIcon(new ImageIcon("Resources/Images/join_game_button.png").getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
		btnJoinGame.setIcon(btnJoinGameIcon);
		btnJoinGame.setBorderPainted(false);
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				//TO BE IMPLEMENTED
				
				/*
				 String[] args = new String[2];
				args[0] = "localhost";
				args[1] = "4444";
				Client.main(args);
				//m.setMenu(5);
				 */
			}
		});
		add(btnJoinGame);
		
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
				//test = "back";
				m.setMenu(1);
			}
		});
		add(btnBack);
		
	
		
		JLabel lblGameIp = new JLabel("Game IP:");
		lblGameIp.setForeground(Color.WHITE);
		lblGameIp.setFont(new Font("Calibri", Font.BOLD, 20));
		lblGameIp.setBackground(Color.BLACK);
		lblGameIp.setBounds(130, 82, 90, 39);
		add(lblGameIp);
		
		JTextField textField = new JTextField();
		textField.setBounds(222, 92, 86, 20);
		textField.setColumns(10);
		add(textField);
		
		/*
		 * Background JLabel
		 */
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		background.setIcon(new ImageIcon(new ImageIcon("Resources/Images/betrayal_background.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT)));
		add(background);
	}

}
