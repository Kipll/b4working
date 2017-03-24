package levels;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.sql.rowset.CachedRowSet;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

import game.Animation;
import game.Circle;
import game.SpritesheetEnum;
import game.Viewport;

public class Level {

	private static final double width = 0.25;
	private static final boolean dodebug = true;
	private int difficulty;

	private double roomW; // in game units
	private double roomH; // in game units

	private static final String imageFolder = "Resources/Images/";
	private String backgroundImageLocation;
	private int backgroundImageW;
	private int backgroundImageH;

	private ArrayList<Wave> waveList = new ArrayList<Wave>();
	private ArrayList<Tile> backTiles = new ArrayList<Tile>(); // tiles behind
																// all others
	private ArrayList<Tile> middleTiles = new ArrayList<Tile>(); // tiles on top
																	// of
																	// background
																	// but
																	// behind
																	// monsters/players
	private ArrayList<Tile> frontTiles = new ArrayList<Tile>(); // tiles on top
																// of
																// monsters/players
	private Set<Tile> collisionTiles = new HashSet<Tile>();

	private int spritesheetVal = SpritesheetEnum.TERRAIN;

	/*
	 * public Level() { this.roomW = 20; this.roomH = 16; //
	 * this.propList.add(new CardboardBox()); // CardboardBox cb = new
	 * CardboardBox(); }
	 */

	public Level(double roomW, double roomH) {
		this.roomW = roomW;
		this.roomH = roomH;
	}

	public Level(String xml) throws ParserConfigurationException {
		parseXML(xml);
	}

	private void debug(String s) {
		if (dodebug)
			System.out.println(s);

	}

	private void parseXML(String fileName) {
		HashMap<Integer, Animation> intAnimationMap = new HashMap<Integer, Animation>();
		ArrayList<Character> charCollisionList = new ArrayList<Character>();

		File file;
		try {
			file = new File(fileName);

		} catch (Exception e) {
			file = new File("../" + fileName);
		}
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();


			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			/*NodeList tilesetNodes = doc.getElementsByTagName("tileset");

			for (int i = 0; i < tilesetNodes.getLength(); i++) {
				Node tilesetNode = tilesetNodes.item(i);
				System.out.println("\nCurrent Element :" + tilesetNode.getNodeName());

				Element tilesetElement = (Element) tilesetNode;
				boolean collision = tilesetElement.getAttribute("collision").trim().contentEquals("1");
				System.out.println("Collision = " + collision);
				NodeList tileNodes = tilesetElement.getElementsByTagName("tile");

				for (int j = 0; j < tileNodes.getLength(); j++) {
					// System.out.println("\nCurrent Element :" +
					// tilesetNode.getNodeName());
					Node tileNode = tileNodes.item(j);
					Element tileElement = (Element) tileNode;

					debug(tileElement.getAttribute("name"));

					int x = Integer.parseInt(tileElement.getAttribute("x").trim());
					int y = Integer.parseInt(tileElement.getAttribute("y").trim());
					Character c = tileElement.getAttribute("char").charAt(0);

					debug("x = " + String.valueOf(x));
					debug("y = " + String.valueOf(y));
					debug("c = " + String.valueOf(c));
					debug("");

					charAnimationMap.put(c, new Animation(spritesheetVal, x, y, 0, Animation.AnimationMode.PLAYONCE));

					if (collision) {
						charCollisionList.add(c);
					}
				}

			}

			System.out.print(charAnimationMap.toString());*/

			NodeList mapNodes = doc.getElementsByTagName("map");

			for (int i = 0; i < mapNodes.getLength(); i++) {
				Node mapNode = mapNodes.item(i);
				System.out.println("\nCurrent Element :" + mapNode.getNodeName());

				Element mapElement = (Element) mapNode;
				int layer = Integer.parseInt(mapElement.getAttribute("layer").trim());

				System.out.println("Layer = " + layer);
				String mapStr = mapElement.getTextContent();
				debug(mapStr);

				String[] mapLines = mapStr.split("\\r?\\n");

				// roomW = mapLines.
				if (mapLines.length <= 2) {
					throw new IllegalArgumentException("No map found");
				}

				roomH = mapLines.length - 2;
				roomW = mapLines[1].split(",").length;

				for (int j = 1; j < roomH; j++) {
					String[] rowItems = mapLines[j].split(",");

					if (rowItems.length != roomW && j != roomH -1) {
						for (int z = 0; z < rowItems.length; z++){
							System.out.println(rowItems[z]);
						}
						System.out.println(mapLines[j].length());
						System.out.println(rowItems.length);
						System.out.println(roomW);
						System.out.println(roomH);
						System.out.println(j);
						throw new IllegalArgumentException("Widths are not consistent");
					}
					// debug(mapLines[j]);
					// System.out.println(mapLines[j].length());

					for (int k = 0; k < roomW; k++) {
						// System.out.println(k);
						int currentInt = Integer.parseInt(rowItems[k]);

						if (currentInt != 0) {

							int x = k;
							int y = j - 1;
							int spriteX = (currentInt -1) % 32;
							int spriteY = (currentInt -1) / 32;
							// int x = k;
							// int y = j - 1;
							Animation anim;
							if (intAnimationMap.containsKey(currentInt)) {
								anim = intAnimationMap.get(currentInt);
							} else {
								anim = new Animation(spritesheetVal, spriteX, spriteY, 1, Animation.AnimationMode.PLAYONCE);
								intAnimationMap.put(currentInt, anim);
							}
							
							Tile tile = new Tile(x, y, anim);

							switch (layer) {
							case -1:
								collisionTiles.add(tile);
								break;
							case 0:
								backTiles.add(tile);
								break;
							case 1:
								middleTiles.add(tile);
								break;
							case 2:
								frontTiles.add(tile);
								break;
							default:
								throw new IllegalArgumentException("Map layer must be in the range 0-2");
							}

							/*if (charCollisionList.contains(currentChar)) {
								// TODO Add position to a list of collisions
								collisionTiles.add(tile);

							}*/
						}

					}
				}

				/*
				 * for (int j = 0; j < tileNodes.getLength(); j++) { //
				 * System.out.println("\nCurrent Element :" + //
				 * tilesetNode.getNodeName()); Node tileNode =
				 * tileNodes.item(j); Element tileElement = (WElement) tileNode;
				 * 
				 * debug(tileElement.getAttribute("name"));
				 * 
				 * int x =
				 * Integer.parseInt(tileElement.getAttribute("x").trim()); int y
				 * = Integer.parseInt(tileElement.getAttribute("y").trim());
				 * char c = tileElement.getAttribute("char").charAt(0);
				 * 
				 * debug("x = " + String.valueOf(x)); debug("y = " +
				 * String.valueOf(y)); debug("c = " + String.valueOf(c));
				 * debug(""); }
				 */

			}

		} catch (ParserConfigurationException e) {
			// Error creating builder
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// Error getting input
			e.printStackTrace();
		} catch (SAXException e) {
			// Error parsing
			e.printStackTrace();
		} catch (IOException e) {
			// Error parsing
			e.printStackTrace();
		}

	}

	protected void addTile(Tile t) {
		// tileList.add(t);
	}

	public void drawBackTiles(Graphics2D g, Viewport vp) {
		for (Tile t : backTiles) {
			t.draw(g, vp);
		}
		return;
	}

	public void drawMiddleTiles(Graphics2D g, Viewport vp) {
		for (Tile t : middleTiles) {
			t.draw(g, vp);
		}
		return;
	}

	public void drawFrontTiles(Graphics2D g, Viewport vp) {
		for (Tile t : frontTiles) {
			t.draw(g, vp);
		}
		return;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public double getRoomW() {
		return roomW;
	}

	public double getRoomH() {
		return roomH;
	}

	public String getImageLocation() {
		return imageFolder + backgroundImageLocation;
	}

	public Image getBackgroundImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(imageFolder + backgroundImageLocation));
		} catch (IOException e) {
		}
		return image;
	}

	public int getBackgroundImageW() {
		return backgroundImageW;
	}

	public int getBackgroundImageH() {
		return backgroundImageH;
	}

	/**
	 * @param c
	 *            Circle of hitbox
	 * @return true if valid position
	 */
	public Boolean validPos(Circle c) {

		/*
		 * double x = c.getCenter().getX(); double y = c.getCenter().getY();
		 * double radius = c.getRadius();
		 * 
		 * int left = (int) Math.round((x - radius)/width); double right = x +
		 * radius; double top = y - radius; double bottom = y + radius;
		 */

		for (Tile t : collisionTiles) {
			if (c.intersects(t.getDestination())) {
				return false;
			}
		}

		return true;

	}
}
