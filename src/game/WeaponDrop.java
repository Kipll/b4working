package game;

import weapons.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;

public class WeaponDrop extends Entity{

	public Circle hitbox;
	public Weapon weapon;
	
	public static final double size = 0.1;

	/**
	 * 
	 * @param pos
	 * @param weapon
	 */
	public WeaponDrop(Point2D.Double pos, Weapon weapon){
		super();
		this.weapon = weapon;
		this.hitbox = new Circle(size, pos);
	}
	
	/**
	 * Get the position of weapon dropped
	 */
	public Point2D.Double getPos(){
		return hitbox.center;
	}
	
	public boolean disposable(){
		return false;
	}
	
	/**
	 * Draw object on viewport
	 */
	public void draw(Graphics2D g, Viewport viewport){
		viewport.drawCircle(hitbox.center, size, weapon.getDropColor(), g);
	}
	
	/**
	 * Change weapon
	 */
	public void playerInteracted(Player p, int event){
		if(event == KeyEvent.VK_Q && p.hitbox.intersects(hitbox)){
			weapon.player = p;
			Weapon w = p.weapon[0];
			p.weapon[0] = weapon;
			weapon = w;
		}
		if(event == KeyEvent.VK_E && p.hitbox.intersects(hitbox)){
			weapon.player = p;
			Weapon w = p.weapon[1];
			p.weapon[1] = weapon;
			weapon = w;
		}
	}
}
