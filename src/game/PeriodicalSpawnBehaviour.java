package game;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.LinkedList;

public class PeriodicalSpawnBehaviour implements Behaviour{
	
	double timeLeft;
	double time;
	
	public Random random;
	
	public LinkedList<LinkedList<Entity>> toSpawn;
	public LinkedList<Double> probs;
	
	public PeriodicalSpawnBehaviour(double time, LinkedList<LinkedList<Entity>> toSpawn, LinkedList<Double> probs){//THE SIZES OF THE 2 LISTS MUST AGREE
		this.time = time;
		this.timeLeft = time;
		this.toSpawn = toSpawn;
		this.probs = probs;
		this.random = new Random();
	}

	public boolean disposable(){
		return false;
	}
	
	public void update(Game game, Point2D.Double pos, double delta){
		
		timeLeft -= delta;
		
		while(timeLeft<=0){
			timeLeft += time;
			ListIterator<Double> pit;
			ListIterator<Entity> lit;
			ListIterator<LinkedList<Entity>> llit;
			LinkedList<Entity> le;
			double sum = 0;
			pit = probs.listIterator();
			while(pit.hasNext()){sum += pit.next();}
			double prob = random.nextDouble()*sum;
			pit = probs.listIterator();
			llit = toSpawn.listIterator();
			while(pit.hasNext()){
				prob -= pit.next();
				le = llit.next();
				if(prob<0){
					lit = le.listIterator();
					while(lit.hasNext()){
						Entity ent = lit.next().clone();
						Point2D.Double pp = ent.getPos();
						pp.x = pos.x;
						pp.y = pos.y;
						game.spawnEntity(ent); System.out.println("Ooga-Booga-Booga");
					}
					break;
				}
			}
			
		}
	}
	
	
	public Behaviour clone(){
		return this;
	}
}
