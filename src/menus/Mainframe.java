package menus;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import audio.BGM;
import lobby.LobbyMServer;

/*
 * @author Jack Marshman
 */

//TODO
//Comment correctly
public class Mainframe extends JFrame
{
	private static final long serialVersionUID = 1L;
	public BGM bgm = new BGM(50, "/Music/BGM_Asian_Gravedrum.wav");
	//public BGM bgm = new BGM(50, "/Music/Mask Off.wav");
	private JPanel cardPanel;
	private CardLayout cardLayout = new CardLayout();
	private Start start;
	private Control controls;
	private Audio audio;
	private Find find;
	private Pause pause;
	private LobbyMember lobbym;

	private HostLobby lobbyo;

	public  Mainframe()
	{
		
		super();
		this.start = new Start(this);
		this.controls = new Control(this);
		this.audio = new Audio(this, bgm);
		this.lobbyo = new HostLobby(this);
		this.lobbym = new LobbyMember(this);
		this.find = new Find(this);
		this.pause = new Pause(this);
		this.setBounds(100, 100, 900, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setResizable(false);
		this.cardPanel = new JPanel(cardLayout);
		cardPanel.add(start, "1");
		cardPanel.add(controls, "2");
		cardPanel.add(audio, "3");
		cardPanel.add(lobbyo, "4");
		cardPanel.add(find, "5");
		cardPanel.add(pause, "6");
		cardPanel.add(lobbym, "7");
		this.setContentPane(cardPanel);
		this.setVisible(true);
		this.bgm.play();
		this.revalidate();
	}
	
	/*
	 * setMenu which treats each menu as a panel, and revalidates the
	 * Menuframe when a button is pressed:
	 * 1 = Start
	 * 2 = Controls
	 * 3 = Audio
	 * 4 = Lobby
	 * 5 = Find
	 * 6 = Pause
	 */
	/*
	 * Not sure about the System.out.println's so I've just commented them out for now - JM
	 */
	public void setMenu(int menuNum)
	{
		switch(menuNum)
		{
			//Start
			case 1:
			{
				cardLayout.show(cardPanel, "1");
				this.revalidate();
				break;
			}
				
			//Controls
			case 2:
			{
				cardLayout.show(cardPanel, "2");
				this.revalidate();
				break;
			}
				
			//Audio
			case 3:
			{
				cardLayout.show(cardPanel, "3");
				this.revalidate();
				break;
			}	
			
			//Lobby Host
			case 4:
			{
				cardLayout.show(cardPanel, "4");
				this.lobbyo.newLobby();
				this.revalidate();
				break;
			}
			
			//Find
			case 5:
			{
				//System.out.println("got case 5");
				cardLayout.show(cardPanel, "5");
				
				this.revalidate();
				break;
			}
			
			//Pause
			case 6:
			{

				cardLayout.show(cardPanel, "6");
				this.revalidate();
				break;
			}
			//Lobby Member
			case 7:
			{
				System.out.println("trying to connect to : " + this.find.IP);
				if(this.lobbym.joinLobby(this.find.txtNickname.getText(), this.find.txtHostIP.getText()) == true){
					cardLayout.show(cardPanel, "7");
				} else {
					this.find.couldntFind();
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		new Mainframe();
	}

}