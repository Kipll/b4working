package game;

import weapons.*;
import java.awt.geom.Point2D;
import java.util.NoSuchElementException;
import java.awt.Color;
import java.util.List;
import java.util.LinkedList;

public class DefaultMonster extends Monster{
	public DefaultMonster(Game game, Point2D.Double pos){
		super(game, 10, 0.1, pos, 20, 100, new Animation(SpritesheetEnum.MONSTER, 0, 0, 0.2, Animation.AnimationMode.LOOP));
		
				
		LinkedList<LinkedList<Entity>> toSpawn = new LinkedList<LinkedList<Entity>>();
		LinkedList<Entity> spawnload = new LinkedList<Entity>();
		LinkedList<Double> probs = new LinkedList<Double>();
		
		Projectile proj = new Projectile(9, new Circle(0.1, new Point2D.Double()), new Point2D.Double(), 0, null, true, 8.0, false, Color.RED, new Animation(SpritesheetEnum.BULLET2,0,0,0.1,Animation.AnimationMode.PLAYONCE), 0, false, true);
		proj.addBehaviour(new LinearMotionToPlayer(0.2, new Point2D.Double(), new Point2D.Double()));
		spawnload.add(proj);
		toSpawn.add(spawnload);
		probs.add(1.0);
		
		addBehaviour(new PeriodicalSpawnBehaviour(5.0, toSpawn, probs));
		
		addBehaviour(new GreedyFollowBehaviour(0.18));
		addBehaviour(new JumpAround(2.8, 0.4));
	}
}
