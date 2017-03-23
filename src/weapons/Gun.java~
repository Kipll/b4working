package weapons;
import game.*;

import java.awt.geom.Point2D;
import java.awt.Color;

public class Gun extends Weapon{
	public Game game;
	public Player p;
	public double damage;
	public double speed;
	public double size;
	public double immunity;
	public double knockbackLen;
	
	public Gun(Game game, Player p){
		super(0.1, p);
		this.game = game;
		this.p = p;
		
		damage = 4.1;
		speed = 4.2;
		size = 0.02;
		immunity = 0.15;
		knockbackLen = 0.04;
	}
	
	@Override
	public void use(Point2D.Double pos){
		if(!usable())return;
		super.use(pos);
		
		Projectile bullet = new Projectile(damage, new CircleSector(size, new Point2D.Double(p.pos.x, p.pos.y)), new Point2D.Double((pos.x-p.pos.x),(pos.y-p.pos.y)), immunity, p, true, 8.0, true, Color.CYAN);
		bullet.knockbackAmp = knockbackLen;
		bullet.addBehaviour(new LinearMotion(speed, pos, p.pos));
		game.spawnEntity(bullet);
	}
}
