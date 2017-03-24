package lobby;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import menus.HostLobby;

public class LobbyMServer extends Thread {

	private int port;
	private HostLobby lobby;
	private LobbyServer sc;
	private boolean running;
	private ServerSocket socket;
	private ArrayList<LobbyServer>connections =  new ArrayList<LobbyServer>();
	public LobbyMServer(int port, HostLobby lobby) {
		//this.connections = new LinkedList<Server>();
		this.port = port;
		this.lobby  = lobby;
		this.sc = null;
		this.running = true;
		this.socket = null;
		try {
			 this.socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

	public void run() { 
		
		
		while (running){
			try {
	
				System.out.println("to connect use name : " + getLocalName());
				Socket clientSocket = socket.accept();
				System.out.println("connection accepted from" + clientSocket.getLocalAddress().toString());
				sc = new LobbyServer(clientSocket, lobby);
				String nickname;
				nickname = new DataInputStream(clientSocket.getInputStream()).readUTF();
			
				this.lobby.updatePlayers(clientSocket.getInetAddress(), nickname);
				sc.start();
				this.connections.add(sc);
			
			}catch(SocketException e1){
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void addtToQueue(String input){
		for (int i = 0; i < connections.size(); i++) {
			connections.get(i).addToQueue(input);
		}
	}

	/**
	 * firstly sends a message to all connections that the server is closing
	 * then sets the boolean running to false so the run loop will stop itterating 
	 * finally closes the server socket. 
	 */
	public void reset() {	
		for (int i = 0; i < connections.size(); i++) {
			connections.get(i).reset();
			connections.clear();
		}
	}

	/**
	 * This method should be invoked when the lobby owner wishes to start the game.
	 */
	public void startAll() {
		for (int i = 0; i < connections.size(); i++) {
			connections.get(i).addToQueue("**STARTGAME**");;
		}
		
	}

	
	private String getLocalName() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostName();
	}
	
}
