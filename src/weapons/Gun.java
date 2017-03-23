package weapons;
import game.*;

import java.awt.geom.Point2D;
import java.awt.Color;

public class Gun extends Weapon{
	public Game game;
	public double damage;
	public double speed;
	public double size;
	public double immunity;
	public double knockbackLen;
	public Animation anim;
	
	public Gun(Game game, Player p){
		super(0.1, p);
		this.game = game;
		
		damage = 4.1;
		speed = 6.0;
		size = 0.02;
		immunity = 0.15;
		knockbackLen = 0.04;
		anim = new Animation(SpritesheetEnum.BULLET,0,0,0.1,Animation.AnimationMode.PLAYONCE);
	}
	
	@Override
	public void use(Point2D.Double pos){
		if(!usable())return;
		super.use(pos);
		
		Projectile bullet = new Projectile(damage, new Circle(size, new Point2D.Double(player.pos.x, player.pos.y)), new Point2D.Double((pos.x-player.pos.x),(pos.y-player.pos.y)), immunity, player, true, 8.0, true, Color.ORANGE, anim, 0, true, true);
		bullet.knockbackAmp = knockbackLen;
		bullet.addBehaviour(new LinearMotion(speed, pos, player.pos));
		game.spawnEntity(bullet);
	}
	
	@Override
	public Color getDropColor(){
		return Color.ORANGE;
	}
}
