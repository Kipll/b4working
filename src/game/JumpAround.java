package game;
import java.awt.geom.Point2D;
import java.util.Random;

public class JumpAround implements Behaviour{

	public double waitingTime;
	public double movingTime;
	
	public double timeLeft;
	public int state;
	
	public static final int waiting = 0;
	public static final int moving = 1;
	public Random random;
	
	public Point2D.Double dest;
	
	public JumpAround(double waitingTime, double movingTime){
		this.waitingTime = waitingTime;
		this.movingTime = movingTime;
		
		timeLeft = waitingTime;
		state=waiting;
		
		dest = new Point2D.Double();
		random = new Random();
	}
	
	public boolean disposable(){
		return false;
	}
	public void update(Game game, Point2D.Double pos, double delta){
		switch(state){
			case waiting:
				timeLeft-=delta;
				if(timeLeft<=0){
					state = moving;
					dest.x = (random.nextDouble()*game.roomW-pos.x)/movingTime;
					dest.y = (random.nextDouble()*game.roomH-pos.y)/movingTime;
					timeLeft+=movingTime;
				}
			break;
			case moving:
				timeLeft-=delta;
				
				pos.x += dest.x * delta;
				pos.y += dest.y * delta;
				
				if(timeLeft<=0){
					state = waiting;
					timeLeft += waitingTime;
				}
			break;
		}
	}
	
	public Behaviour clone(){
		return new JumpAround(waitingTime, movingTime);
	}
}
