package lobby;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LobbySender extends Thread {
		private DataOutputStream out;
		private BlockingQueue<String> queue;
		private boolean running;

		public LobbySender(DataOutputStream out) {
			this.out = out;
			this.queue = new LinkedBlockingQueue<String>();
			this.running = true;
		}

		public void run() {

			try {
				while (running) {
					String message = queue.take();
					System.out.println("writing message : " + message);
					out.writeUTF(message);
				}
			} catch (IOException e) {
				e.printStackTrace();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("sender is stopping");
		}

		public void addToQueue(String input) {
			
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void reset() {
			this.running = false;
			this.interrupt();
			
		}
}
