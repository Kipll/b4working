package game;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import audio.BGM;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

/*
 * @author Jack Marshman
 */

//TODO
//Create and add graphics to new JButtons
//Add events and sounds to button clicks	//Farrah: added click sound
//Integrate into game
//Add graphics to resources
public class Control extends JFrame 
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
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try
				{
					Control frame = new Control();
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
	public Control()
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
		 * Back button
		 */
		JButton btnBack = new JButton();
		btnBack.setBounds(10, 201, 90, 50);
		ImageIcon btnBackIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(90, 50, Image.SCALE_DEFAULT));
		btnBack.setIcon(btnBackIcon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

			}
		});
		contentPane.add(btnBack);
		
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
		 * Controls title graphic
		 */
		JLabel titleLabel = new JLabel();
		titleLabel.setBounds(108, 11, 213, 64);
		ImageIcon titleIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(200,64, Image.SCALE_DEFAULT));
		titleLabel.setIcon(titleIcon);
		contentPane.add(titleLabel);
		
		/*
		 * Controls graphic
		 */
		int controlsWidth = 350;
		int controlsHeight = 120;
		JLabel controlsLabel = new JLabel();
		controlsLabel.setBounds(44, 56, 363, 166);
		ImageIcon controlsIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(controlsWidth, controlsHeight, Image.SCALE_DEFAULT));
		controlsLabel.setIcon(controlsIcon);
		contentPane.add(controlsLabel);	
	}
}

