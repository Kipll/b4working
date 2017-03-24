package game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.Image;
import java.awt.image.BufferedImage;

public final class SpritesheetEnum {

	public static final int MONSTER = 0;
	public static final int PLAYER = 1;
	public static final int ARENA = 2;
	public static final int WALL = 3;
	public static final int TERRAIN = 4;
	public static final int MONSTER2 = 5;
	public static final int MONSTER3 = 6;
	public static final int HP = 7;
	public static final int BULLET = 8;
	public static final int FAIRY = 9;
	public static final int SWORD = 10;
	public static final int BULLET2 = 11;
	public static final int BOXGUN = 12;
	public static final int BOXSWORD = 13;
	public static final int BOXLASER= 14;
	
	
	
	public static final int ENUMSIZE = 15;

	private static Spritesheet getSpritesheet(int val) {
		switch (val) {
		case MONSTER:
			try {
				String path = "Resources/Images/monster3.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
					System.out.println("we have read an image");
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 4, 4, new int[] { 4, 4, 4, 4 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}

		case PLAYER:
			try {
				String path = "Resources/Images/player.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 3, 3, new int[] { 3, 3, 1 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}

		case ARENA:
			try {
				String path = "Resources/Images/arena.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 1, 1, new int[] { 1 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}

		case WALL:
			try {
				String path = "Resources/Images/wall.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 1, 1, new int[] { 1 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}

		case TERRAIN:
			try {
				String path = "Resources/Images/terrain.png";
				BufferedImage i = null;
				int[] colsInRow = new int[32];
				Arrays.fill(colsInRow, 32);
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				System.out.print("Image width: " + i.getWidth());
				System.out.println(" | Image width: " + (int)i.getWidth()/32);
				return new Spritesheet(i, 32, 32, colsInRow);
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}

		case MONSTER2:
			try {
				String path = "Resources/Images/monster2.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 4, 3, new int[] { 4, 4, 4 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}

		case MONSTER3:
			try {
				String path = "Resources/Images/monster1.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 4, 4, new int[] { 4, 4, 4 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}
			
			case HP:
			try {
				String path = "Resources/Images/hp.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 1, 1, new int[] { 1});
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}
		
		case BULLET:
			try {
				String path = "Resources/Images/bullet.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 1, 1, new int[] { 1});
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}
			
		case FAIRY:
			try {
				String path = "Resources/Images/fairy.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 1, 1, new int[] {1});
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}
			
		case SWORD:
			try {
				String path = "Resources/Images/swordsprite_2.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 6, 2, new int[] { 6,6 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}
		
		case BULLET2:
			try {
				String path = "Resources/Images/monster_bullet.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 1, 1, new int[] { 1 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}
		
		case BOXGUN:
			try {
				String path = "Resources/Images/gunbox.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 1, 1, new int[] { 1 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}
			
		case BOXSWORD:
			try {
				String path = "Resources/Images/swordbox.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 1, 1, new int[] { 1 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}
			
		case BOXLASER:
			try {
				String path = "Resources/Images/laserbox.png";
				BufferedImage i = null;
				try {
					i = ImageIO.read(new File(path));
				} catch (IOException e) {
					i = ImageIO.read(new File("../" + path));
				}
				return new Spritesheet(i, 1, 1, new int[] { 1 });
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
				System.err.println(e.getMessage());
				System.exit(1);
			}
		}

		return null;
	}

	private static Spritesheet[] sprites = new Spritesheet[ENUMSIZE];

	public static Spritesheet getSprite(int val) {
		try {
			if (sprites[val] == null) {
				sprites[val] = getSpritesheet(val);
			}
		} catch (Exception e) {
			System.err.println("please dont leave exceptions empty");
		}

		return sprites[val];
	}
}
