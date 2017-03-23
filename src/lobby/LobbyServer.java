package lobby;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import menus.HostLobby;

public class LobbyServer extends Thread {

	private Socket socket;
	private LobbySender sender;
	private LobbyListener listener;
	private HostLobby owner;
	public LobbyServer(Socket socket, HostLobby owner){
		this.socket = socket;
		this.sender = null;
		this.listener = null;
		this.owner = owner;
	}
	
	public void run(){
		try {

			this.sender = new LobbySender(new DataOutputStream(socket.getOutputStream()));
			this.listener = new LobbyListener(new DataInputStream(socket.getInputStream()), owner);
			sender.start();
			listener.start();
		} catch (SocketException e1){
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void addToQueue(String input){
		this.sender.addToQueue(input);
	}

	public void reset() {
		sender.addToQueue("**RESET**");
		sender.interrupt();
		listener.interrupt();
		try {
			this.socket.close();
		} catch (IOException e) {
		}
	}
	
}
