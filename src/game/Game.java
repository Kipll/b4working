package game;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import levels.Level;
import network.ServerListener;
import network.ClientListener;
import network.Server;
import network.MainServer;
import weapons.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.concurrent.ConcurrentLinkedQueue;


public class Game{
	
	
	public boolean isRunning;
	public KeyboardInput keyboard;
	public MouseInput mouse;
	
	public double roomW;//in game units
	public double roomH;//in game units
	public ConcurrentLinkedQueue<Player> players;
	public LinkedList<Monster> monsters;
	public LinkedList<Entity> entities;
	public LinkedList<Monster> monstersWaiting;
	public LinkedList<Entity> entitiesWaiting;
	
	public Server server;
	private final int[] ENDGAMEMESSAGE = new int[]{-6,0,0,0,0,0,0,0,0,0};
	public static int port = 4445;
	
	//private Viewport viewport;
	
	//Animation arena;
	
	private Level level;
	
	public Random rand;
	
	public double maxSpawnTime;
	public double minSpawnTime;
	public double timeUntilSpawn;
	
	int score = 0;
	private boolean gameOver;
	
	public void spawnEntity(Entity e){
		entitiesWaiting.add(e);
	}
	public void spawnMonster(Monster e){
		monstersWaiting.add(e);
	}
	
	public void setScore(int s){
		score = s;
		
		Iterator<Player> pit = players.iterator();
		while(pit.hasNext()){
			Player pl = pit.next();
			int[] ints = new int[]{
					-5,
					0,
					score,
					0,
					0,
					0,
					0,
					0,
					0,
					0
			};
			pl.viewport.server.addToQueue(ints);
//			pl.viewport.server.addToQueue(-5);
//			pl.viewport.server.addToQueue(0);
//			pl.viewport.server.addToQueue(score);
//			pl.viewport.server.addToQueue(0);
//			pl.viewport.server.addToQueue(0);
//			pl.viewport.server.addToQueue(0);
//			pl.viewport.server.addToQueue(0);
//			pl.viewport.server.addToQueue(0);
//			pl.viewport.server.addToQueue(0);
//			pl.viewport.server.addToQueue(0);
		}
	}
	
	//private levels.Level level = new levels.TestLevel(this);
	//java is dumb
	
	public Game(KeyboardInput keyboard, MouseInput mouse){
		isRunning = true;
		gameOver = false;
		this.keyboard = keyboard;
		this.mouse = mouse;
		
		players = new ConcurrentLinkedQueue<Player>();
		monsters = new LinkedList<Monster>();
		entities = new LinkedList<Entity>();
		monstersWaiting = new LinkedList<Monster>();
		entitiesWaiting = new LinkedList<Entity>();
		
		
		
		
		try {
			level = new Level("Resources/LevelFiles/10x10grassy.xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		roomW = level.getLevelWidth();
		roomH = level.getLevelHeight();
		
		/*Player p = new Player(this, keyboard, mouse);
		p.viewport = new Viewport(this, p);
		players.add(p);
		
		
		
		server = new Server();
		
		
		p.viewport.server = server;
		
		server.listen(port, p);*/
		
		new MainServer(port, this).start();
		
		//arena = new Animation(SpritesheetEnum.ARENA,0,0,1,Animation.AnimationMode.LOOP);
		
		rand = new Random();
		maxSpawnTime = 4.3;
		minSpawnTime = 1.1;
	}
	
	public void update(double delta){
		
		monsters.addAll(monstersWaiting);
		monstersWaiting = new LinkedList<Monster>();
		entities.addAll(entitiesWaiting);
		entitiesWaiting = new LinkedList<Entity>();
		
		Iterator<Player> pit = players.iterator();
		int noPlayers = players.size();
		int deadPlayers = 0;
		while(pit.hasNext()){
			Player p = pit.next();
			p.update(delta, this);
			if(p.isDead){
				deadPlayers++;
			}
			if(p.disposable()){ 
				pit.remove();
			}
		}
		if(noPlayers != 0 && deadPlayers == noPlayers){
			gameOver = true;
			isRunning = false;
			this.gameOver = true;
			isRunning = false;
			this.server.addToQueue(ENDGAMEMESSAGE);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
			
		}
		ListIterator<Monster> mit = monsters.listIterator(0);
		while(mit.hasNext()){Monster m = mit.next(); m.update(delta, this); if(m.disposable()) mit.remove();}
		ListIterator<Entity> eit = entities.listIterator(0);
		while(eit.hasNext()){Entity e = eit.next(); e.update(delta, this); if(e.disposable()) eit.remove();}
		
		
		if(!players.isEmpty()){
			if(timeUntilSpawn <= 0)
			{
				timeUntilSpawn = minSpawnTime + rand.nextDouble()*(maxSpawnTime - minSpawnTime);
				spawnMonster(new DefaultMonster(this, new Point2D.Double(rand.nextDouble()*roomW, rand.nextDouble()*roomH)));
				if(monsters.size()%4==0) {spawnMonster(new Monster2(this, new Point2D.Double(rand.nextDouble()*roomW, rand.nextDouble()*roomH)));
							  spawnMonster(new Monster3(this, new Point2D.Double(rand.nextDouble()*roomW, rand.nextDouble()*roomH)));}
			}
			else timeUntilSpawn -= delta;
		}
	}
	
	public Level getLevel(){
		return level;
	}
	
	public void render(Graphics2D g){
		Iterator<Player> pit = players.iterator();
		while(pit.hasNext()){drawOnViewport(g, pit.next().viewport);}
	}
	
	public void drawOnViewport(Graphics2D g, Viewport viewport){
		int ints[] = new int[10];
		for(int i=0;i<ClientListener.inputSize;i++)
			ints[i] = -1;
			viewport.server.addToQueue(ints);
	
	
		viewport.drawRectAbsolute(new Point(0,0), viewport.screenW, viewport.screenH, Color.BLACK, g);
		
		
		//g.setColor(Color.GREEN);
		//Point roomCoord = viewport.toScreenCoord(new Point2D.Double(0,0));
		//g.fillRect(roomCoord.x, roomCoord.y, (int)(roomW*viewport.ppu), (int)(roomH*viewport.ppu));
		level.drawBackTiles(g, viewport);
		level.drawMiddleTiles(g, viewport);
		//viewport.drawSprite(new Rectangle.Double(0,0,roomW,roomH), arena, g);
		
		Iterator<Player> pit = players.iterator();
		while(pit.hasNext())pit.next().draw(g, viewport);
		ListIterator<Monster> mit = monsters.listIterator(0);
		while(mit.hasNext())mit.next().draw(g, viewport);
		ListIterator<Entity> eit = entities.listIterator(0);
		while(eit.hasNext())eit.next().draw(g, viewport);
		
		level.drawFrontTiles(g, viewport);
		
		viewport.drawRectAbsolute(new Point(30,60),100,10, Color.BLACK, g);
		viewport.drawRectAbsolute(new Point(30,60),(int)(100 * viewport.p.hp / viewport.p.maxHp),10, Color.RED, g);
	}
}
