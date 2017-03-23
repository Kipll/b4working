package lobby;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import menus.LobbyMember;


public class LobbyClient {


	private LobbySender sender;
	private LobbyListener listener;
	private boolean connected;
	private LobbyMember member;

	public LobbyClient(LobbyMember member) {
		this.sender = null;
		this.listener = null;
		this.connected = false;
		this.member = member;
	}

	public boolean connect(int port, String name) {
		try {

			Socket server = new Socket(name, port);
			sender = new LobbySender(new DataOutputStream(server.getOutputStream()));
			listener = new LobbyListener(new DataInputStream(server.getInputStream()), member);
			sender.start();
			listener.start();
			return true;
		} catch (IOException e) {
			System.out.println("couldnt connect to specified host, connection refused.");
			return false;
		}
	}
	
	public void addToQueue(String input){
		sender.addToQueue(input);
	}
	
}
