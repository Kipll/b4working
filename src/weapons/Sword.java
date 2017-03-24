package weapons;

import game.*;
import java.awt.geom.Point2D;
import java.awt.Color;

public class Sword extends Weapon{
	public Game game;
	public double damage;
	public double len;
	public double arc;
	public double lifetime;
	public double speed;
	public Animation anim;
	
	public Sword(Game game, Player p){
		super(0.3, p);
		this.game = game;
		
		damage = 10;
		len = 0.4; //used to be 0.75
		arc = Math.PI/4;
		anim = new Animation(SpritesheetEnum.SWORD, 0, 0, 0.05, Animation.AnimationMode.LOOP);
		lifetime = 0.2;
		
		speed = 1.8*Math.PI;
	}
	
	@Override
	public void use(Point2D.Double pos){
		if(!usable())return;
		super.use(pos);
		
		double arcStart = Math.atan2(pos.y-player.pos.y,pos.x-player.pos.x)-arc/2-lifetime*speed/2;
		while(arcStart<0)arcStart += 2*Math.PI;
		game.spawnEntity(new Projectile(damage, new CircleSector(len, player.pos, arcStart, arc), new Point2D.Double(0,0), lifetime, player, true, lifetime, false, Color.CYAN, anim, speed, true, true));
	}
	
	@Override
	public Color getDropColor(){
		return Color.CYAN;
	}
}
