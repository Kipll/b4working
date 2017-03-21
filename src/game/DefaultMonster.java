package game;
import java.awt.geom.Point2D;
import java.util.NoSuchElementException;

public class DefaultMonster extends Monster{
	public DefaultMonster(Game game, Point2D.Double pos){
		super(game, 10, 0.3, pos, 20, 100, new Animation(SpritesheetEnum.MONSTER, 0, 0, 0.2, Animation.AnimationMode.LOOP));
		
		try{
			Player towards = game.players.peek();
			if(towards == null) throw new NoSuchElementException();
				addBehaviour(new LinearHome(0.18, towards.getPos()));
		}
		catch(NoSuchElementException e){System.err.println("please dont leave exceptions empty");}
		
		addBehaviour(new JumpAround(2.8, 0.4));
	}
}
