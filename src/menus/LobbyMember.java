package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import audio.BGM;
import game.Main;
import lobby.LobbyClient;
import lobby.LobbyMServer;
import network.TCPClient;

public class LobbyMember extends JPanel {

	
	private JLabel lblNickname;
	private static final long serialVersionUID = 1L;
	private LobbyClient client;
	private Mainframe mainframe;
	private JButton btnBack;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtNickname;
	private Font txtFont = new Font("Times New Roman", 30, 14);
	private String hostIP;
	
	public ArrayList<InetAddress> ips;
	public ArrayList<String> nicknames;
	
	private BGM click;
	public LobbyMember(Mainframe m){
		
		super();
		this.mainframe = m;
		this.click = new BGM(10, "/Music/SFX_Click.wav");
		this.client = new LobbyClient(this);
		this.hostIP = null;
//		this.setSize(640,420);

		
		setLayout(null);
	
		String[] col = new String[]{"IPaddress", "nickname"};
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		
		this.ips = new ArrayList<InetAddress>();
		this.nicknames = new ArrayList<String>();
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			ips.add(local);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		//ips.add(3)
		
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(488,163,386,186);
		scrollPane.setBackground(new Color(139, 69, 19));
		this.add(scrollPane, BorderLayout.CENTER);
	
		
		
		
	//	setLayout(new BorderLayout());
		
		btnBack = new JButton();
		btnBack.setBounds(20, 450, 180, 100);
		ImageIcon btnBackIcon = MenuButtonHandler.loadImageIcon("Resources/Images/back_button.png", 180, 100);
		btnBack.setIcon(btnBackIcon);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				ips.clear();
				nicknames.clear();
				
				m.setMenu(1);
			}
		});
		add(btnBack);
	
		lblNickname = new JLabel("New label");
		lblNickname.setIcon(MenuButtonHandler.loadImageIcon("Resources/Images/nickname_label.jpg", 386, 176));
		lblNickname.setBounds(10, 163, 386, 176);
		add(lblNickname);
		
		
		txtNickname = new JTextField();
		txtNickname.setBounds(199, 235, 149, 33);
		txtNickname.setColumns(10);
		txtNickname.setFont(txtFont);
		txtNickname.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		add(txtNickname);
		
	
		
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		ImageIcon backgroundIcon = MenuButtonHandler.loadImageIcon("Resources/Images/betrayal_background.png", 900, 600);
		background.setIcon(backgroundIcon);
		add(background);
		
		
	
	}
	
	public void updatePlayers(InetAddress inetAddress, String string){
		Object[] obs = {inetAddress, string};
		tableModel.addRow(obs);
	}
	
	public boolean startClient(String address, int port){
		return this.client.connect(port, address);
	}
	
	public void ownsetNickname(String name){
		this.txtNickname.setText(name);
		if(!nicknames.isEmpty())
			nicknames.remove(0);
			nicknames.add(0,name);
			txtNickname.setText(nicknames.get(0));
			txtNickname.setEnabled(false);
	}
	public void setIP(String ip) throws UnknownHostException{
			ips.add(InetAddress.getByName(ip));
		
	}
	public void setlocalIP(){
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			ips.add(0,local);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
	}
	public boolean startClient(int port, String name){
		return this.client.connect(port, name);
	}
//	public void updateOwnerNickname(){
//
//		nicknames.remove(0);
//		nicknames.add(0, txtNickname.getText());
//		tableModel.setValueAt(nicknames.get(0),0,1);
//	}

	public boolean joinLobby(String name) {
		if(this.client.connect(4444, "test") == true){
			this.txtNickname.setText(name);
			this.client.addToQueue(name);
			this.hostIP = name;
			return true;
		}else {
			return false;
		}
	}

	public void startGame() {
		new TCPClient(4445, hostIP);
		
		
	}

	public void leave() {
		this.mainframe.setMenu(5);
		this.ips.clear();
		this.nicknames.clear();
		this.table.clearSelection();
		while(this.tableModel.getRowCount() > 0)
		this.tableModel.removeRow(0);
		
		
	}
}


