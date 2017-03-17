package game;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
public final class SpritesheetEnum {
	public static final int MONSTER = 0;
	public static final int PLAYER = 1;
	public static final int ARENA = 2;
	public static final int WALL = 3;
	public static final int DAWNOFTHEGODS = 4;
	
	public static final int ENUMSIZE = 4;
	
	private static Spritesheet getSpritesheet(int val){
		switch(val){
			case MONSTER:
				try{
				String path = "Resources/Images/monster_bird.png";
				BufferedImage i = null;
				try{i = ImageIO.read(new File(path));}catch(IOException e){i = ImageIO.read(new File("../"+path));}
					return new Spritesheet(i,4,4, new int[]{4,4,4,2});
				} catch (IOException e) {
					System.err.println(e.getStackTrace());
					System.err.println(e.getMessage());
					System.exit(1);
				}
			
			case PLAYER:
				try{	String path = "Resources/Images/player.png";
				BufferedImage i = null;
				try{i = ImageIO.read(new File(path));}catch(IOException e){i = ImageIO.read(new File("../"+path));}
					return new Spritesheet(i,3,3, new int[]{3,3,1});
				} catch (IOException e) {
					System.err.println(e.getStackTrace());
					System.err.println(e.getMessage());
					System.exit(1);
				}
				
			case ARENA:
				try{	String path = "Resources/Images/arena.png";
				BufferedImage i = null;
				try{i = ImageIO.read(new File(path));}catch(IOException e){i = ImageIO.read(new File("../"+path));}
					return new Spritesheet(i,1,1, new int[]{1});
				} catch (IOException e) {
					System.err.println(e.getStackTrace());
					System.err.println(e.getMessage());
					System.exit(1);
				}
				
			case WALL:
				try{	String path = "Resources/Images/wall.png";
				BufferedImage i = null;
				try{i = ImageIO.read(new File(path));}catch(IOException e){i = ImageIO.read(new File("../"+path));}
					return new Spritesheet(i,1,1, new int[]{1});
				} catch (IOException e) {
					System.err.println(e.getStackTrace());
					System.err.println(e.getMessage());
					System.exit(1);
				}	
				
			case DAWNOFTHEGODS:
				try{	String path = "Resources/Images/dawn_of_the_gods.png";
				BufferedImage i = null;
				try{i = ImageIO.read(new File(path));}catch(IOException e){i = ImageIO.read(new File("../"+path));}
					return new Spritesheet(i,1,1, new int[]{1});
				} catch (IOException e) {
					System.err.println(e.getStackTrace());
					System.err.println(e.getMessage());
					System.exit(1);
				}	
		}
		return null;
		
	}
	
	private static Spritesheet[] sprites = new Spritesheet[ENUMSIZE];
	
	public static Spritesheet getSprite(int val){
		try{
			if(sprites[val]==null){
				sprites[val] = getSpritesheet(val);
			}
		} catch(Exception e){}
		
		return sprites[val];
	}
}
