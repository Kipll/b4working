package levels;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import game.Animation;
import game.Entity;
import game.Viewport;

// TODO: Auto-generated Javadoc
/**
 * The Class Tile.
 */
public class Tile extends Entity {

	/** The Constant width of every tile. */
	public final static double width = 0.5;

	/** The animation of the tile storing it's image. */
	private Animation anim;

	/**
	 * The rectangle storing the location and size of where to draw on screen in
	 * game units.
	 */
	private Rectangle2D.Double dest;

	/** The top-left position of the tile in game units. */
	private Point2D.Double pos;

	/**
	 * Instantiates a new tile at the position specified on screen with the
	 * specified animation.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param anim
	 *            the anim
	 */
	public Tile(int x, int y, Animation anim) {
		this.anim = anim;
		pos = new Point2D.Double(x * width, y * width);
		dest = new Rectangle2D.Double(pos.getX(), pos.getY(), width, width);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.Entity#clone()
	 */
	@Override
	public Entity clone() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.Entity#disposable()
	 */
	@Override
	public boolean disposable() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.Entity#draw(java.awt.Graphics2D, game.Viewport)
	 */
	@Override
	public void draw(Graphics2D g, Viewport viewport) {
		viewport.drawSprite(dest, anim, g);
	}

	/**
	 * Gets the animation.
	 *
	 * @return the animation
	 */
	public Animation getAnimation() {
		return anim;
	}

	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public Rectangle2D.Double getDestination() {
		return dest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.Entity#getPos()
	 */
	@Override
	public Point2D.Double getPos() {
		return pos;
	}

}
