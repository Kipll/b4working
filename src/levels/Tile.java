package levels;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import game.Animation;
import game.Entity;
import game.Viewport;

public class Tile extends Entity {
	
	public final static double width = 0.5;
		
	private Animation anim;
	private Rectangle2D.Double dest;

	private Point2D.Double pos;

	public Tile(int x, int y, Animation anim) {
		this.anim = anim;
		pos = new Point2D.Double(x * width, y * width);
		dest = new Rectangle2D.Double(pos.getX(), pos.getY(), width, width);
	}
	
	@Override
	public Entity clone() {
		return this;
	}

	@Override
	public boolean disposable() {
		return false;
	}

	@Override
	public void draw(Graphics2D g, Viewport viewport) {
		viewport.drawSprite(dest, anim, g);
	}

	protected Animation getAnimation() {
		return anim;
	}

	protected Rectangle2D.Double getDestination() {
		return dest;
	}

	@Override
	public Point2D.Double getPos() {
		return pos;
	}

	protected void setAnimation(Animation anim) {
		this.anim = anim;
	}

	@Override
	public String toString() {
		return pos.toString();
	}
}
