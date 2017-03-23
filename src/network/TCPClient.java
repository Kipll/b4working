package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import game.ClientWindow;



public class TCPClient {
	
	private int port;
	private String name;
	private UDPClient udpC;
	
	public TCPClient(int port, String name) {
		this.port = port;
		this.name = name;
		
		try {
			Socket s = new Socket(name, port); //create new socket with given port and name;
			int serverPort = s.getPort(); // gets the port number of the server;
			int localPort = s.getLocalPort();
			InetAddress serverAddress = s.getInetAddress(); // gets the Inet address of the server
			
			int p = new DataInputStream(s.getInputStream()).readInt();
			s.close();
			
			DatagramSocket socket = new DatagramSocket(localPort);
			this.udpC = new UDPClient(socket, p, serverAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
	}
	
	public void addToQueue(int[] ints) {
		udpC.addToQueue(ints);
	}
	
	public static void main(String[] args){
		TCPClient c = new TCPClient(Integer.parseInt(args[0]), args[1]);
	}
}
