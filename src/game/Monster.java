package game;

import weapons.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

//import audio.SFX;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Monster extends Projectile{
	public double maxHp;
	public Game game;
	public double expWorth;
	
	public boolean dead;
	public double hp;
	
	public Projectile lastHitBy;
	public TreeMap<Projectile, Double> immunities;
	
	private Random random;
	
	public Monster(Game game, double contactDamage, double size, Point2D.Double pos, double hp, double expWorth, Animation anim){
		super(contactDamage, new Circle(size, pos), new Point2D.Double(0,0), 0.1, null, false, 0, false, null, anim, 0, false, true);
		this.random = new Random();
		this.game = game;
		//pos = new Point2D.Double(random.nextDouble()*game.roomW, random.nextDouble()*game.roomH);
		this.expWorth = expWorth;
		this.hp = hp;
		this.maxHp = hp;
		dead = false;
		lastHitBy = null;
		immunities = new TreeMap<Projectile, Double>();
		
		//anim = new Animation(SpritesheetEnum.MONSTER,0,0,0.5,Animation.AnimationMode.LOOP);
		
		/*
		try{
		 Player towards = game.players.peek();
		 if(towards == null) throw new NoSuchElementException();
			addBehaviour(new LinearHome(0.18, towards.getPos()));
		}
		catch(NoSuchElementException e){}
		
		addBehaviour(new JumpAround(2.8, 0.4));	*/
	}
	
	public void hit(Projectile p){
		if(!immunities.containsKey(p)){
			//decrease damage
			p.player.setExp((int)(p.player.exp + expWorth*Math.min(hp,p.damage)/maxHp));
			hp -= p.damage;
			
			//apply knockback
			addBehaviour(new Knockback(p.getKnockback(), 0.1));
			
			//make it immune to this object
			immunities.put(p, new Double(p.immunityTime));
		}
		lastHitBy = p;
	}
	
	public boolean disposable(){
		return dead;
	}
	
	public void update(double delta, Game game){
		if(hp<=0){
		//	SFX deathSound = new SFX(100, "/Music/SFX_Monster_3.wav");
		//	deathSound.play();
			dead = true;
			game.setScore(game.score+1);
			try{
				if(random.nextDouble()<0.33)game.spawnEntity(new Fairy(7.0, 0.1, 10, 0.2, 2.8, new Point2D.Double(getPos().x, getPos().y), lastHitBy.player, game));
				
				double dropType = random.nextDouble();
				if(dropType<0.1){
					Weapon w = null;
					double wchance = random.nextDouble();
					if(wchance<0.33){
						w = new Sword(game,null);
					} else if(wchance<0.66){
						w = new Laser(game,null);
					} else {
						w = new Gun(game,null);
					}
					game.spawnEntity(new WeaponDrop((Point2D.Double)(getPos().clone()),w));
				}
				else if(dropType<0.35){
					game.spawnEntity(new FirstAid(65,(Point2D.Double)(getPos().clone()),0.03));
				}
			}
			catch(NullPointerException e){}
		}
		
		super.update(delta, game);
		
		Iterator<Map.Entry<Projectile, Double>> pIt = immunities.entrySet().iterator();
		LinkedList<Projectile> imtorem = new LinkedList<Projectile>();
		while(pIt.hasNext()){
			Map.Entry<Projectile, Double> e = pIt.next();
			e.setValue(e.getValue() - delta);
			if(e.getValue()<=0)imtorem.add(e.getKey());
		}
		Iterator<Projectile> imtoremit = imtorem.iterator();
		while(imtoremit.hasNext())immunities.remove(imtoremit.next());
	}
	
	@Override
	public void draw(Graphics2D g, Viewport viewport){
		super.draw(g, viewport);
		double hpBarH = 0.03;
		//Point coord = viewport.toScreenCoord(new Point2D.Double(getPos().x-getSize(), getPos().y-getSize()));
		
		viewport.drawRect(new Point2D.Double(getPos().x-getSize(), getPos().y-getSize()-hpBarH), 2*getSize(), hpBarH, Color.BLACK, g);
		viewport.drawRect(new Point2D.Double(getPos().x-getSize(), getPos().y-getSize()-hpBarH), 2*getSize()*hp/maxHp, hpBarH, Color.RED, g);
	}
}
