package game;
import java.awt.geom.Point2D;
import java.util.NoSuchElementException;

public class Monster3 extends Monster{
	public Monster3(Game game, Point2D.Double pos){
		super(game, 10, 0.3, pos, 100, 100, new Animation(SpritesheetEnum.MONSTER3, 0, 0, 0.2, Animation.AnimationMode.LOOP));
		
		try{
			Player towards = game.players.peek();
			if(towards == null) throw new NoSuchElementException();
				addBehaviour(new LinearHome(1.0, towards.getPos()));
		}
		catch(NoSuchElementException e){}
	}
}
