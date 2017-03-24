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
	public Animation anim;
	public Animation anim2;
	public Animation anim3;
	
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
		anim = new Animation(SpritesheetEnum.BOXGUN, 0, 0, 0.1, Animation.AnimationMode.PLAYONCE);
		anim2 = new Animation(SpritesheetEnum.BOXSWORD, 0, 0, 0.1, Animation.AnimationMode.PLAYONCE);
		anim3 = new Animation(SpritesheetEnum.BOXLASER, 0, 0, 0.1, Animation.AnimationMode.PLAYONCE);
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
			if(weapon.getDropColor()==Color.ORANGE){
			viewport.drawCircleSprite(hitbox.center, size, anim, g);}
			else if(weapon.getDropColor()==Color.CYAN) {
			viewport.drawCircleSprite(hitbox.center, size, anim2, g);}
			else if(weapon.getDropColor()==Color.RED) {
			viewport.drawCircleSprite(hitbox.center, size, anim3, g);}
		//viewport.drawCircle(hitbox.center, size, weapon.getDropColor(), g);
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
	
	@Override
	public Entity clone(){
		WeaponDrop wd = new WeaponDrop((Point2D.Double)(getPos().clone()), weapon);
		cloneBehaviour(wd);
		return wd;
	}
}
