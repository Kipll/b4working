package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import audio.BGM;
import game.Main;
import network.TCPClient;

/*
 * @author Jack Marshman
 */
public class Host extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Mainframe m;
	private BGM click;
	public JButton btnStartGame;
	public JButton btnBack;
	public JLabel lblLocalIP;
	public JLabel lblPlayers;
	public JLabel background;

	public Host(Mainframe m)
	{
		super();
		this.m = m;
		click = new BGM(10,"/Music/SFX_Click.wav");
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		/*
		 * StartGame button
		 */
		btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(694, 450, 180, 100);
		ImageIcon btnStartGameIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
		btnStartGame.setIcon(btnStartGameIcon);
		btnStartGame.setBorderPainted(false);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				Main m =  new Main();
				m.start();
				
				TCPClient c = new TCPClient(4444, "localhost");
				
				c.start();
				System.out.println("past c.start");
				//TO BE IMPLEMENTED.
				
			}
		});
		add(btnStartGame);
		
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
		
		/*
		 * JLabel which shows the host's IP address for connecting
		 */
		lblLocalIP = new JLabel("Local IP:[###.###.#.#]");
		lblLocalIP.setForeground(Color.WHITE);
		lblLocalIP.setBackground(Color.BLACK);
		lblLocalIP.setFont(new Font("Calibri", Font.BOLD, 20));
		lblLocalIP.setBounds(124, 68, 183, 39);
		add(lblLocalIP);
		
		/*
		 * JLabel which shows the current connected players
		 */
		lblPlayers = new JLabel("Players:[             ]");
		lblPlayers.setForeground(Color.WHITE);
		lblPlayers.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPlayers.setBackground(Color.BLACK);
		lblPlayers.setBounds(124, 94, 183, 112);
		add(lblPlayers);
		
		/*
		 * Background JLabel
		 */
		background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		background.setIcon(new ImageIcon(new ImageIcon("Resources/Images/betrayal_background.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT)));
		add(background);
	}

}
