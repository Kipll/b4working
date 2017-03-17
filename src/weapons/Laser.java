package weapons;

import game.*;
import java.awt.geom.Point2D;
import java.awt.Color;

public class Laser extends Weapon{
	public Game game;
	public double damage;
	public double len;
	public double arc;
	public double lifetime;
	public double immunityTime;
	
	public Laser(Game game, Player p){
		super(1.05, p);
		this.game = game;
		
		damage = 19;
		len = 10.0;
		arc = 0.02;
		
		lifetime = 0.3;
		immunityTime = 0.2;
	}
	
	@Override
	public void use(Point2D.Double pos){
		if(!usable())return;
		super.use(pos);
		
		double arcStart = Math.atan2(pos.y-player.pos.y,pos.x-player.pos.x)-arc/2;
		while(arcStart<0)arcStart += 2*Math.PI;
		game.spawnEntity(new Projectile(damage, new CircleSector(len, player.pos, arcStart, arc), new Point2D.Double(0,0), immunityTime, player, true, lifetime, false, Color.RED, null, 0, true, true));
	}
	
	@Override
	public Color getDropColor(){
		return Color.RED;
	}
}
