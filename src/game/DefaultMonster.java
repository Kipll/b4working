package game;
import java.awt.geom.Point2D;
import java.util.NoSuchElementException;

public class DefaultMonster extends Monster{
	
	/**
	 * Create new monster that spawns randomly on map
	 * @param game Game object
	 * @param pos The position of monster
	 */
	public DefaultMonster(Game game, Point2D.Double pos){
		super(game, 10, 0.3, pos, 100, 100, new Animation(SpritesheetEnum.MONSTER, 0, 0, 0.2, Animation.AnimationMode.LOOP));
		
		try{
			Player towards = game.players.peek();
			if(towards == null) throw new NoSuchElementException();
				addBehaviour(new LinearHome(0.18, towards.getPos()));
		}
		catch(NoSuchElementException e){}
		
		addBehaviour(new JumpAround(2.8, 0.4));
	}
}
