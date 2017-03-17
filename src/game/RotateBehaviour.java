package game;
import java.awt.geom.Point2D;

public class RotateBehaviour extends LinearHome{

	public double r;
	public Point2D.Double around;
	
	public double angle;
	
	public RotateBehaviour(double speed, double r, Point2D.Double around){
		super(speed, (Point2D.Double)(around.clone()));
		
		this.r = r;
		this.around = around;
		
		this.angle = 0;
		towards.x += r;
	}
	
	public boolean disposable(){
		return false;
	}
	
	public void update(Game game, Point2D.Double pos, double delta){
		
		super.update(game, pos, delta);
		
		angle += speed*delta/r;
		
		towards.x = around.x + r*Math.cos(angle);
		towards.y = around.y + r*Math.sin(angle);
	}

}
