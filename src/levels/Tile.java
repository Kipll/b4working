package levels;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.Animation;
import game.Circle;
import game.Game;
import game.Spritesheet;
import game.Viewport;
import game.SpritesheetEnum;

public class Tile extends game.Entity {

	private boolean collision;
	// private double width; //internal width not number of pixels
	// private double height;
	// private boolean circleHitbox;
	// private double hitboxTop, hitboxBottom, hitboxLeft, hitboxRight;
	// private Circle hitboxCircle;
	// private Point2D.Double coordinates;

	private Rectangle2D.Double dest;
	private Spritesheet ss;
	private Animation anim;
	private Point2D pos;

	public Tile() {
		
	}

	public Tile(int x, int y, Animation anim) {
		this.anim = anim;
		pos = new Point2D.Double((double)x/4, (double)y/4);
		//setDestination((double)x, (double)y);
		double width = (1 + (double) 1 / (double) 32) / (double) 4;
		width = (double) 1/ (double) 4;
		//width += (double) 1 / (double) 32;
		//width = (double)1 + (double) (1/32);
		//System.out.println("Width: " + width);
		dest = new Rectangle2D.Double(pos.getX(), pos.getY(), width, width);
	}

	public String toString() {
		return pos.toString();
	}

	@Override
	public Point2D.Double getPos() {
		// TODO Auto-generated method stub
		return null;
	}

	protected Rectangle2D.Double getDestination() {
		return dest;
	}

	protected Spritesheet getSpritesheet() {
		return ss;
	}

	protected void setSpritesheet(Spritesheet ss) {
		this.ss = ss;
	}

	protected Animation getAnimation() {
		return anim;
	}

	protected void setAnimation(Animation anim) {
		this.anim = anim;
	}

	public Spritesheet getSprite() {
		return ss;
	}

	@Override
	public boolean disposable() {
		return false;
	}

	@Override
	public void draw(Graphics2D g, Viewport viewport) {
		//double height = viewport.game.roomH;
		viewport.drawSprite(dest, anim, g);
	}
	
	public game.Entity clone(){
		return this;
	}
}
