package weapons;

import game.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.LinkedList;

public class Projectile extends Entity{
	public boolean isDisposable = false;
	public double damage;
	public Point2D.Double knockbackDir;
	public double knockbackAmp;
	//the monsters hit will be immune to this projectile for this amount of time
	public double immunityTime;
	public game.Shape hitbox;
	public Player player;
	public boolean hasLifetime;
	public double lifetime;
	public boolean breaksOnContact;
	public double rotationSpeed;
	public boolean hitsPlayers;
	public boolean hitsMonsters;
	
	public Color color;
	public Animation anim;
	
	public Projectile(double damage, game.Shape hitbox, Point2D.Double knockback, double immunityTime, Player player, boolean hasLifetime, double lifetime, boolean breaksOnContact, Color color, Animation anim, double rotationSpeed, boolean hitsMonsters, boolean hitsPlayers){
		super();
		this.damage = damage;
		this.hitbox = hitbox;
		this.immunityTime = immunityTime;
		this.player = player;
		this.hasLifetime = hasLifetime;
		this.lifetime = lifetime;
		this.breaksOnContact = breaksOnContact;
		this.knockbackAmp = Math.sqrt(knockback.x*knockback.x+knockback.y*knockback.y);
		this.knockbackDir = (knockbackAmp > 0)?(new Point2D.Double(knockback.x / knockbackAmp, knockback.y / knockbackAmp)):(new Point2D.Double(0,0));
		this.rotationSpeed = rotationSpeed;
		this.color = color;
		this.anim = anim;
		this.hitsPlayers = hitsPlayers;
		this.hitsMonsters = hitsMonsters;
	}
	
	@Override
	public Point2D.Double getPos(){return hitbox.getCenter();}
	
	public double getSize(){return hitbox.getRadius();}
	
	public Point2D.Double getKnockback(){
		return new Point2D.Double(knockbackDir.x * knockbackAmp, knockbackDir.y * knockbackAmp);
	}
	
	@Override
	public boolean disposable(){
		return isDisposable;
	}
	
	@Override
	public void update(double delta, Game game){
		if(hasLifetime)
		{
			lifetime-=delta;
			if(lifetime<=0) isDisposable = true;
		}
		
		super.update(delta, game);
		
		if(hitbox instanceof CircleSector) ((CircleSector)hitbox).setArcStart(((CircleSector)hitbox).getArcStart() + rotationSpeed*delta);
		
		if(hitsMonsters){
			ListIterator<Monster> mit = game.monsters.listIterator(0);
			while(mit.hasNext()){
				Monster m = mit.next();
				if(hitbox.intersects(m.hitbox)){m.hit(this); if(breaksOnContact){isDisposable = true; break;}}
			}
		}
		
		if(hitsPlayers){
			Iterator<Player> pit = game.players.iterator();
			while(pit.hasNext()){
				Player p = pit.next();
				if(p == player)continue;
				if(hitbox.intersects(p.hitbox)){p.hit(this); if(breaksOnContact){isDisposable = true; break;}}
			}
		}
		
		if(anim != null)anim.update(delta);
	}
	
	@Override
	public void draw(Graphics2D g, Viewport viewport){
		if(anim == null) {
			if(hitbox instanceof CircleSector) viewport.drawCircleSector(getPos(), getSize(), ((CircleSector)hitbox).getArcStart(), ((CircleSector)hitbox).getArcLen(), color, g);
			else if(hitbox instanceof Circle) viewport.drawCircle(getPos(), getSize(), color, g);
		}
		else viewport.drawCircleSprite(getPos(), getSize(), anim, g);
	}
}
