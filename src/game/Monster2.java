package game;
import java.awt.geom.Point2D;
import java.util.NoSuchElementException;

public class Monster2 extends Monster{
	public Monster2(Game game, Point2D.Double pos){
		super(game, 10, 0.3, pos, 100, 100, new Animation(SpritesheetEnum.MONSTER2, 0, 0, 0.2, Animation.AnimationMode.LOOP));
		
		try{
			Player towards = game.players.peek();
			if(towards == null) throw new NoSuchElementException();
				//addBehaviour(new JumpAround(1.8, 2.4));
				addBehaviour(new LinearHome(0.18, towards.getPos()));
				  addBehaviour(new RotateBehaviour(0.7, 0.3, pos));
		}
		catch(NoSuchElementException e){}
		
		 addBehaviour(new RotateBehaviour(0.7, 0.6, pos));
	}
}
