package game;
import java.awt.geom.Point2D;
import java.util.NoSuchElementException;

public class Monster3 extends Monster{
	public Monster3(Game game, Point2D.Double pos){
		super(game, 10, 0.3, pos, 100, 100, new Animation(SpritesheetEnum.MONSTER3, 0, 0, 0.2, Animation.AnimationMode.LOOP));
		
		addBehaviour(new GreedyFollowBehaviour(1.0));
	}
}
