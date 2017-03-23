package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import audio.BGM;
import game.Main;
import lobby.LobbyMServer;
import network.TCPClient;

public class HostLobby extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnBack;
	private JButton btnStart;
	private JLabel lblNickname;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtNickname;
	private JScrollPane scrollPane;
	private Font txtFont = new Font("Times New Roman", 30, 14);
	
	
	private LobbyMServer server;
	private Mainframe m;
	public ArrayList<InetAddress> ips;
	public ArrayList<String> nicknames;
	
	private BGM click;
	public HostLobby(Mainframe m){
		
		super();
		this.m = m;
		this.server = new LobbyMServer(4444, this);
		this.click = new BGM(10, "/Music/SFX_Click.wav");
		this.ips = new ArrayList<InetAddress>();
		this.nicknames = new ArrayList<String>();
		this.setLayout(null);
	
		this.addStrBtn();
		this.addBckBtn();
		this.addUsrTbl();
		this.addTxtNNm();
		this.addUsrNcn();			
		this.addBakgnd();
		
		
		
		
	
	}
	/**
	 * starts the lobby's server which waits for others to connect
	 */
	private void startServer(){
		if(this.server.isAlive() == false)
			this.server.start();
	}
	
	
	public void updatePlayers(InetAddress inetAddress, String string){
		Object[] obs = {inetAddress, string};
		tableModel.addRow(obs);
	}
	
	public void updateOwnerNickname(){

		nicknames.remove(0);
		nicknames.add(0, txtNickname.getText());
		tableModel.setValueAt(nicknames.get(0),0,1);
	}
	
	/**
	 * To be called when the lobby is closed (back button pressed) which resets 
	 * the window and notifies all people connected that the lobby has ended
	 */
	private void reset() {
		this.click.playOnce();
		this.ips.clear();
		this.nicknames.clear();
		while(this.tableModel.getRowCount()>0)
		this.tableModel.removeRow(0);
		this.server.reset();
		
		}
		
		
	
	/**
	 * removes a player from the lobby from an inetAddress
	 * @param name the inetAddress of the player they wish to remove
	 */
	public void removePlayer(String name){
		for (int i = 0; i < tableModel.getRowCount(); i++){
			if(tableModel.getValueAt(i, 0).toString().contains(name))
				tableModel.removeRow(i);
		}
			
		
	}

	/**
	 * called when lobby owner screen is to be shown.
	 */
	public void newLobby() {
		this.nicknames.add(0,"its you!");
		try {
			this.ips.add(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
				e.printStackTrace();
		}
		this.tableModel.addRow(new Object[]{this.ips.get(0),this.nicknames.get(0)});
		this.startServer();
	}
	
	
	
	
	
	private void addStrBtn(){
		
		btnStart = new JButton();
		btnStart.setBounds(694, 450, 180, 100);
		ImageIcon btnJoinGameIcon = MenuButtonHandler.loadImageIcon("Resources/Images/join_game_button.png", 180, 100);
		btnStart.setIcon(btnJoinGameIcon);
		btnStart.setBorderPainted(false);
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				click.playOnce();
				Main m = new Main();
				m.start();
				server.StartAll();
				//server.reset();
				
				TCPClient c = new TCPClient(4444, "localhost");
				
				
			}
		});
		
		add(btnStart);

		
	}

	private void addBckBtn() {

		btnBack = new JButton();
		btnBack.setBounds(20, 450, 180, 100);
		ImageIcon btnBackIcon = MenuButtonHandler.loadImageIcon("Resources/Images/back_button.png", 180, 100);
		btnBack.setIcon(btnBackIcon);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				//test = "back";
				reset();
				m.setMenu(1);
			}

		});
		add(btnBack);
	
	}
	

	private void addUsrTbl(){
		String[] col = new String[]{"IPaddress", "nickname"};
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
				
			
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(488,163,386,186);
		scrollPane.setBackground(new Color(139, 69, 19));
		this.add(scrollPane, BorderLayout.CENTER);
		scrollPane.revalidate();
	}

	private void addUsrNcn(){
		lblNickname = new JLabel("New label");
		lblNickname.setIcon(MenuButtonHandler.loadImageIcon("Resources/Images/nickname_label.jpg", 386, 176));
		lblNickname.setBounds(10, 163, 386, 176);
		add(lblNickname);
	}


	private void addTxtNNm(){
	txtNickname = new JTextField("Set Nickname");
	txtNickname.setBounds(202, 237, 130, 23);
	txtNickname.setColumns(10);
	txtNickname.setFont(txtFont);
	txtNickname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	txtNickname.addFocusListener(new FocusListener(){
		public void focusLost(FocusEvent args0)
		{
			if(txtNickname.getText().trim().equals(""))
	        {
	        	txtNickname.setText("Set Nickname");
	        }
	        
	    }
		
		public void focusGained(FocusEvent args0)
		{
	        if(txtNickname.getText().trim().equals("Set Nickname"))
	        {
	        	txtNickname.setText("");
	        }
	    }
	});
	
	txtNickname.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(tableModel.getRowCount() == 0){
        		String[] rowData = new String[]{"", txtNickname.getText()};
        		try {
        			rowData = new String[]{InetAddress.getLocalHost().toString(), txtNickname.getText()};
        		}
				catch (UnknownHostException e) {
					
				}
        		tableModel.addRow(rowData);
        	} else {
        		tableModel.setValueAt(txtNickname.getText(), 0, 1);
        	}
		}
	});
	
	add(txtNickname);
	}
	
	private void addBakgnd(){
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		ImageIcon backgroundIcon = MenuButtonHandler.loadImageIcon("Resources/Images/betrayal_background.png", 900, 600);
		background.setIcon(backgroundIcon);
		add(background);
		}
	public void refresh(String message) {
		
	}
}
