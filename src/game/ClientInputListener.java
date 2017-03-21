package game;

import java.awt.event.*;

import network.ClientSender;


import java.awt.Point;

public class ClientInputListener implements MouseMotionListener, MouseListener, KeyListener, ComponentListener {

	private static final int KEY_COUNT = 256;
	
	public ClientSender sender;

	public ClientInputListener(ClientSender sender) {

		this.sender = sender;
	}

	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			int[] ints = new int[]{
					3,
					-1,
					-1
			};
			sender.addToQueue(ints);
			break;
		case MouseEvent.BUTTON3:
			ints = new int[]{
					5,
					-1,
					-1
			};
			sender.addToQueue(ints);
			break;
		}
	}

	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			int[] ints = new int[]{
					2,
					-1,
					-1
			};
			sender.addToQueue(ints);
			break;
		case MouseEvent.BUTTON3:
			ints = new int[]{
					4,
					-1,
					-1
			};
			sender.addToQueue(ints);
			break;
		}
	}

	public void mouseMoved(MouseEvent e) {
		int[] ints = new int[]{
				6,
				e.getX(),
				e.getY()
		};
		sender.addToQueue(ints);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		int[] ints = new int[]{
				6,
				e.getX(),
				e.getY()
		};
		sender.addToQueue(ints);
		}

	public synchronized void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode >= 0 && keyCode < KEY_COUNT) {
			int[] ints = new int[]{
					1,
					keyCode,
					-1
			};

			sender.addToQueue(ints);
			

		}

	}

	public synchronized void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode >= 0 && keyCode < KEY_COUNT) {
			int[] ints = new int[]{
					0,
					keyCode,
					-1
			};
			
			sender.addToQueue(ints);
		}

	}

	public void keyTyped(KeyEvent e) {

		// Not needed

	}
	
	public void componentResized(ComponentEvent e) {
	
			int[] ints = new int[]{
					7,
					e.getComponent().getWidth(),
					e.getComponent().getHeight()
			};
			sender.addToQueue(ints);
	}
	
	
	public void componentHidden(ComponentEvent e){}
	public void componentMoved(ComponentEvent e){}
	public void componentShown(ComponentEvent e){}
}
