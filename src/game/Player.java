package game;

import weapons.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import game.Animation.AnimationMode;

import audio.BGM;

public class Player extends Entity {
	public KeyboardInput keyboard;
	public MouseInput mouse;
	public Viewport viewport;

	public Point2D.Double pos;
	public double size;
	public double speed;
	public Game game;
	public Circle hitbox;

	public double maxHp;
	public double hp;
	public double immunityTime;
	public double maxImmunityTime;
	public Animation anim;

	public boolean isDead;

	private int lastSet;
	
	public double exp;
	
	public Weapon[] weapon;
	
	public void setExp(int exp){
		this.exp = exp;
		int[] ints = new int[]{
				-5,
				1,
				exp,
				0,
				0,
				0,
				0,
				0,
				0,
				0
		};
		viewport.server.addToQueue(ints);
	}
		/*		viewport.server.addToQueue(-5);
		viewport.server.addToQueue(1);
		viewport.server.addToQueue(exp);
		viewport.server.addToQueue(0);
		viewport.server.addToQueue(0);
		viewport.server.addToQueue(0);
		viewport.server.addToQueue(0);
		viewport.server.addToQueue(0);
		viewport.server.addToQueue(0);
		viewport.server.addToQueue(0);
	}
*/
	public BGM sword_swing, pew_pew;
	
	private static final double sqrt2 = Math.sqrt(2);

	public double getAngle(){
		return Math.atan2(viewport.toGameCoord(mouse.getPos()).y - pos.y, viewport.toGameCoord(mouse.getPos()).x - pos.x);
	}

	public Player(Game game, KeyboardInput keyboard, MouseInput mouse) {
		super();
		this.game = game;
		this.mouse = mouse;
		// this.viewport = viewport;
		this.keyboard = keyboard;
		pos = new Point2D.Double(0.25, 0.25);
		size = 0.14; // used to be 0.28
		speed = 1.0;
		weapon = new Weapon[2];
		weapon[0] = new Gun(game, this);
		weapon[1] = new Sword(game, this);
		hitbox = new Circle(size, pos);
		maxHp = 200;
		hp = maxHp;
		maxImmunityTime = 0.7;
		immunityTime = 0;
		isDead = false;
		anim = new Animation(SpritesheetEnum.PLAYER, 0, 0, 0.1, Animation.AnimationMode.LOOP);
		exp = 0;

		sword_swing = new BGM(50, "/Music/SFX_Swoosh.wav");
		pew_pew = new BGM(50, "/Music/SFX_Hit_2.wav");
	}

	@Override
	public void update(double delta, Game game) {
		
		super.update(delta, game);
		keyboard.poll();
		mouse.poll();

		viewport.update(delta);
		
		
		if (keyboard.keyDown(KeyEvent.VK_UP))
			viewport.ppu += 20.0 * delta;
		if (keyboard.keyDown(KeyEvent.VK_DOWN))
			viewport.ppu -= 20.0 * delta;

		if (hp > 0) {
			Point2D.Double oldPos = new Point2D.Double(pos.x, pos.y);
			
			double dx = 0;
			double dy = 0;
			
		//	anim.setSet(0);
			anim.update(delta);
			if(keyboard.keyDownOnce(KeyEvent.VK_Q)){
				ListIterator<Entity> eit = game.entities.listIterator(0);
				while(eit.hasNext())eit.next().playerInteracted(this, KeyEvent.VK_Q);
			}
			if(keyboard.keyDownOnce(KeyEvent.VK_E)){
				ListIterator<Entity> eit = game.entities.listIterator(0);
				while(eit.hasNext())eit.next().playerInteracted(this, KeyEvent.VK_E);
			}
			
			// adjust speed so it's the same in all directions
			if (keyboard.keyDown(KeyEvent.VK_W)) {
				dy += Math.max(pos.y - speed * delta, size) - pos.y;
			}
			if (keyboard.keyDown(KeyEvent.VK_A)) {
				dx += Math.max(pos.x - speed * delta, size) - pos.x;
				//anim.setSet(1);
				//lastSet=1;
			}
			if (keyboard.keyDown(KeyEvent.VK_S)) {
				dy += Math.min(pos.y + speed * delta, game.roomH - size) - pos.y;
			}
			if (keyboard.keyDown(KeyEvent.VK_D)) {
				dx += Math.min(pos.x + speed * delta, game.roomW - size) - pos.x;
				anim.setSet(0);
				lastSet=0;
			}
			
			//if moving diagonally, adjust speed
			if((dy != 0) && (dx != 0)){
				dy = dy/sqrt2;
				dx = dx/sqrt2;
			}
			
			pos.x += dx;
			pos.y += dy;
			
			//Check if valid position and move back to valid position
			if (!game.getLevel().validPos(hitbox)){
				pos.x = oldPos.x;
				if(!game.getLevel().validPos(hitbox)){
					pos.x = oldPos.x + dx;
					pos.y = oldPos.y;
					if(!game.getLevel().validPos(hitbox)){
						pos.x = oldPos.x;
					}
				}
			}
			
			// adjust speed so it's the same in all directions
	/*		if (keyboard.keyDown(KeyEvent.VK_W)) {
				pos.y = Math.max(pos.y - speed * delta, size);
			}
			if (keyboard.keyDown(KeyEvent.VK_A)) {
				pos.x = Math.max(pos.x - speed * delta, size);
				anim.setSet(1);
			}
			if (keyboard.keyDown(KeyEvent.VK_S)) {
				pos.y = Math.min(pos.y + speed * delta, game.roomH - size);
			}
			if (keyboard.keyDown(KeyEvent.VK_D)) {
				pos.x = Math.min(pos.x + speed * delta, game.roomW - size);
				anim.setSet(0);
			}
			*/
			
			if (!game.keyboard.keyDown(KeyEvent.VK_W) && !game.keyboard.keyDown(KeyEvent.VK_A)
					&& !game.keyboard.keyDown(KeyEvent.VK_S) && !game.keyboard.keyDown(KeyEvent.VK_D)) {
				anim.setSet(lastSet);
			}
			
			
			if (mouse.isPressed(0) && hp > 0) {
				weapon[0].use(viewport.toGameCoord(mouse.getPos()));

				sword_swing.playOnce();
			}
			if (mouse.isPressed(1) && hp > 0) {
				weapon[1].use(viewport.toGameCoord(mouse.getPos()));

				pew_pew.playOnce();
			}

			int l = weapon.length;
			for (int i = 0; i < l; i++)
				weapon[i].update(delta);

			if (immunityTime > 0)
				immunityTime -= delta;
		} else {
			BGM scream = new BGM(50, "/Music/SFX_Man_Scream_1.wav");
			scream.playOnce();
			if(!isDead){
				isDead = true;
				viewport.server.addToQueue(new int[]{
					-420,
					0,
					0,
					0,
					0,
					0,
					0,
					0,
					0,
					0
				});
			}
		}
		
	}

	@Override
	public void draw(Graphics2D g, Viewport vp) {
		vp.drawCircleSprite(pos, size, anim, g, getAngle());
		if(vp == viewport){
			vp.drawCircle(new Point2D.Double(pos.x, pos.y-size), size/4, Color.WHITE, g);
		}
	}

	@Override
	public Point2D.Double getPos() {
		return pos;
	}

	@Override
	public boolean disposable() {
		return isDead;
	}
	
	
	public void hit(Projectile proj){
		if (hitbox.intersects(proj.hitbox)) {
			if (immunityTime <= 0) {
				hp -= proj.damage;
				immunityTime = maxImmunityTime;
			}
		}
	}
	
	public Entity clone(){
		return this;
	}
	
}
