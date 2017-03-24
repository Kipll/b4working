package network;

import java.io.IOException;
import java.awt.*;

import game.Spritesheet;
import game.SpritesheetEnum;
import game.ClientWindow;


import java.awt.Color;
import java.awt.Graphics2D;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ClientListener extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;
	private byte[] recievedData;
	private ClientWindow panel;
	public Graphics2D g;
	int[] info;
	
	public static final int inputSize = 10;
	public static final int infoSize = 2;
	int[] input;
	private boolean isRunning;

	public ClientListener(DatagramSocket socket, ClientWindow panel) {
		this.isRunning = true;
		this.panel = panel;
		this.input = new int[inputSize];
		this.info = new int[infoSize];
		this.socket = socket;
		this.recievedData = new byte[inputSize * 4];
		this.packet = new DatagramPacket(recievedData, recievedData.length);
		//this.g = (Graphics2D)(panel.getGraphics());
	}

	public void run() {
		while (isRunning = false) {
			try {
				socket.receive(packet);
				recievedData = packet.getData();
				input = ByteConversion.toInts(recievedData);
				
				/* WHY ARE WE DOING GRAPHICS WORK IN MY NETWORKING PACKAGE GUYS >:(  Sam. */
				if(input[0]>=0){
					
						Spritesheet sprs = SpritesheetEnum.getSprite(input[0]);
					
						g.drawImage(sprs.img,
							input[1], input[2], input[3], input[4],
							sprs.offsetW + sprs.spriteW * input[5], sprs.offsetH + sprs.spriteH * input[6], sprs.offsetW + sprs.spriteW * (input[5] + 1) - 1, sprs.offsetH + sprs.spriteH * (input[6] + 1) - 1,
							null);
					}
						
					else if(input[0]==-1){
					
						g.setColor(Color.WHITE);
						g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
						g.drawString("Global killcount: " + info[0], 30, 30);
						g.drawString("Experience: " + info[1], 30, 80);
		
						panel.paintImmediately(panel.getBounds());
					}
					else if(input[0]==-2){
						g.setColor(new Color(input[1], input[2], input[3]));
						g.fillOval(input[4], input[5], input[6], input[7]);
					}
					else if(input[0]==-3){
						g.setColor(new Color(input[1], input[2], input[3]));
						g.fillRect(input[4], input[5], input[6], input[7]);
					}
					else if(input[0]==-4){
						g.setColor(new Color(input[1], input[2], input[3]));
						g.fillArc(input[4], input[5], input[6], input[7], input[8], input[9]);
					}
					else if(input[0]==-5){
						info[input[1]]=input[2];
					} else if(input[0] ==-6){
						this.isRunning = false;
						this.panel.dispose();
						
					}
				
			
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassCastException e2) {
			 System.out.println("caught classcastException");
			}
		}
	}

	
}
/*public class ClientListener extends Thread {

	private DataInputStream in;
	public ClientWindow panel;
	public Graphics2D g;
	public int[] info;

	public static final int inputSize = 10;
	public static final int infoSize = 2;
	int[] input;

	public ClientListener(DataInputStream in, ClientWindow panel) {
		this.in = in;
		this.panel = panel;
		
		input = new int[inputSize];
		info = new int[infoSize];
	}

	public void run() {
		try {
			while (true) {
				//while(g == null);
				
				for(int i=0;i<inputSize;i++) input[i] = in.readInt();
				
				if(input[0]>=0){
				
					Spritesheet sprs = SpritesheetEnum.getSprite(input[0]);
				
					g.drawImage(sprs.img,
						input[1], input[2], input[3], input[4],
						sprs.offsetW + sprs.spriteW * input[5], sprs.offsetH + sprs.spriteH * input[6], sprs.offsetW + sprs.spriteW * (input[5] + 1) - 1, sprs.offsetH + sprs.spriteH * (input[6] + 1) - 1,
						null);
				}
					
				else if(input[0]==-1) {
					
					g.setColor(Color.WHITE);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
					g.drawString("Global killcount: " + info[0], 30, 30);
					g.drawString("Experience: " + info[1], 30, 80);
		
					panel.paintImmediately(panel.getBounds());
				}
				else if(input[0]==-2){
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillOval(input[4], input[5], input[6], input[7]);
				}
				else if(input[0]==-3){
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillRect(input[4], input[5], input[6], input[7]);
				}
				else if(input[0]==-4){
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillArc(input[4], input[5], input[6], input[7], input[8], input[9]);
				}
				else if(input[0]==-5){
					info[input[1]]=input[2];
				}
			}
		}
		catch (IOException e) {
			System.out.println(e + " : Connection was lost");
		}
	}
}
*/
