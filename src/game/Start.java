package game;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import audio.BGM;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * @author Jack Marshman
 */


//TODO

//Create and add graphics to new JButtons
//Add events and sounds to button clicks	//Farrah: added click sound
//Integrate into game
//Comment Start.java correctly
//Add graphics to resources
public class Start extends JFrame
{
	/**
	 * Initial JPanel
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try
				{
					Start frame = new Start();
					frame.setVisible(true);
				} 
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Frame constructor
	 */
	public Start()
	{
		/*
		 * Creates content pane
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		/*
		 * Find Game button
		 */
		JButton btnFindGame = new JButton();
		btnFindGame.setBounds(10, 88, 414, 50);
		ImageIcon btnFindGameIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(90, 50, Image.SCALE_DEFAULT));
		btnFindGame.setIcon(btnFindGameIcon);
		btnFindGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{

			}
		});
		contentPane.add(btnFindGame);
		
		/*
		 * Controls button
		 */
		JButton btnControls = new JButton();
		btnControls.setBounds(10, 201, 90, 50);
		ImageIcon btnControlsIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(90, 50, Image.SCALE_DEFAULT));
		btnControls.setIcon(btnControlsIcon);
		btnControls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{

			}
		});
		contentPane.add(btnControls);
		
		/*
		 * Audio button
		 */
		JButton btnAudio = new JButton();
		btnAudio.setBounds(171, 201, 90, 50);
		ImageIcon btnAudioIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(90, 50, Image.SCALE_DEFAULT));
		btnAudio.setIcon(btnAudioIcon);
		btnAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

			}
		});
		contentPane.add(btnAudio);
		
		/*
		 * Exit button
		 */
		JButton btnExit = new JButton();
		btnExit.setBounds(334, 201, 90, 50);
		ImageIcon btnExitIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(90, 50, Image.SCALE_DEFAULT));
		btnExit.setIcon(btnExitIcon);
		btnExit.addActionListener(e -> System.exit(0));
		contentPane.add(btnExit);
		
		/*
		 * Betrayal logo JLabel
		 */
		int logoIconWidth = 250;
		int logoIconHeight = 80;
		JLabel logoLabel = new JLabel();
		logoLabel.setBounds(96, 11, logoIconWidth, logoIconHeight);
		ImageIcon logoIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(logoIconWidth, logoIconHeight, Image.SCALE_DEFAULT));
		logoLabel.setIcon(logoIcon);
		contentPane.add(logoLabel);
		
	}
}