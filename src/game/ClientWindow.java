package game;
import javax.swing.JFrame;
import javax.swing.JPanel;


import network.ClientListener;
import network.ClientSender;

import java.awt.*;
import java.io.IOException;

public class ClientWindow extends JPanel{

	public ClientInputListener l;
	public ClientListener cl;
	public Graphics2D g;
	
	private JFrame frame;
	private int[] info = new int[2];
	
	public ClientWindow(ClientSender sender){
		super();
		
		frame = new JFrame("betrayal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640,420);
		
		setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		
		l = new ClientInputListener(sender);
		
		setFocusable(true);
		
		addKeyListener(l);
		addMouseListener(l);
		addMouseMotionListener(l);
		addComponentListener(l);
		
		frame.setVisible(true);
	}
	
	
	public void newMessage(int[] input){

		try{
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

		this.paintImmediately(this.getBounds());
	}
	else if(input[0]==-2){
		g.setColor(new Color(input[1], input[2], input[3]));
		g.fillOval(input[4], input[5], input[6], input[7]);
		this.paint(g);
	}
	else if(input[0]==-3){
		g.setColor(new Color(input[1], input[2], input[3]));
		g.fillRect(input[4], input[5], input[6], input[7]);
		this.paint(g);
	}
	else if(input[0]==-4){
		g.setColor(new Color(input[1], input[2], input[3]));
		g.fillArc(input[4], input[5], input[6], input[7], input[8], input[9]);
		this.paint(g);
	}
	else if(input[0]==-5){
		info [input[1]]=input[2];
	}

} catch (ClassCastException e2) {
System.out.println("caught classcastException");
}
}
	
@Override
	public void paint(Graphics g){
		System.out.println("paint has been called");
		this.g = (Graphics2D)g;
	}
}
