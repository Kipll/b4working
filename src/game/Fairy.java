package game;
import weapons.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Fairy extends Projectile{
	
	/**
	 * 
	 * @param lifetime  The amount of time it spawns
	 * @param size		The size of fairy
	 * @param damage	The amount of damage cause to monster
	 * @param hitrate	The hitrate of fairy
	 * @param acceleration The acceleration of fairy
	 * @param pos		The coordinate of spawn
	 * @param player	The id of player
	 * @param game		Game object
	 */
	public Fairy(double lifetime, double size, double damage, double hitrate, double acceleration, Point2D.Double pos, Player player, Game game){
		super(damage, new Circle(size, pos), new Point2D.Double(0,0), hitrate, player, true, lifetime, false, Color.YELLOW,new Animation(SpritesheetEnum.FAIRY,0,0,0.1,Animation.AnimationMode.PLAYONCE), 0, true, false);
		
		addBehaviour(new Gravitate(player.pos, acceleration));
	}
}
