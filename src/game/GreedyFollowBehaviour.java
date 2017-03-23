package game;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;

public class GreedyFollowBehaviour extends LinearHome{

	public GreedyFollowBehaviour(double speed){
		super(speed, new Point2D.Double());
	}

	public void update(Game game, Point2D.Double pos, double delta){
		double minr = Double.MAX_VALUE;
	
		Iterator<Player> pit = game.players.iterator();
		while(pit.hasNext()){
			Player player = pit.next();
			double r = Math.sqrt((pos.x - player.getPos().x)*(pos.x - player.getPos().x) + (pos.y - player.getPos().y)*(pos.y - player.getPos().y));
			if(r<minr){
				minr = r;
				towards = player.getPos();
			}
		}
		super.update(game,pos,delta);
	}
}
