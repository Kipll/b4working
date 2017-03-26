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

	// private static final boolean dodebug = true;
	// private int difficulty;

	private int noCols; // in game units
	private int noRows; // in game units

	private static final String imageFolder = "Resources/Images/";
	private String backgroundImageLocation;
	private int backgroundImageW;
	private int backgroundImageH;

	// private ArrayList<Wave> waveList = new ArrayList<Wave>();
	private ArrayList<Tile> backTiles = new ArrayList<Tile>();
	// tiles behind all others
	private ArrayList<Tile> middleTiles = new ArrayList<Tile>();
	// tiles on top of background but behind monsters/players
	private ArrayList<Tile> frontTiles = new ArrayList<Tile>();
	// tiles on top of monsters/players
	private ArrayList<Tile> collisionTiles = new ArrayList<Tile>();

	private int spritesheetVal = SpritesheetEnum.TERRAIN;

	public Level(String xml) throws ParserConfigurationException {
		parseXML(xml);
	}

	private void parseXML(String fileName) {
		HashMap<Integer, Animation> intAnimationMap = new HashMap<Integer, Animation>();
		// ArrayList<Character> charCollisionList = new ArrayList<Character>();

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = null;
			try {
				doc = builder.parse(new File(fileName));
			} catch (IOException e) {
				doc = builder.parse(new File("../" + fileName));
			}

			doc.getDocumentElement().normalize();

			NodeList mapNodes = doc.getElementsByTagName("map");

			for (int i = 0; i < mapNodes.getLength(); i++) {
				Node mapNode = mapNodes.item(i);
				System.out.println("\nCurrent Element :" + mapNode.getNodeName());

				Element mapElement = (Element) mapNode;
				int layer = Integer.parseInt(mapElement.getAttribute("layer").trim());

				String mapStr = mapElement.getTextContent();

				String[] mapLines = mapStr.split("\\r?\\n");

				// roomW = mapLines.
				if (mapLines.length <= 2) {
					throw new IllegalArgumentException("No map found");
				}

				noRows = mapLines.length - 2;
				noCols = mapLines[1].split(",").length;
				System.err.println("noRows: " + noRows);
				System.err.println("noCols: " + noCols);

				for (int j = 1; j < noRows + 1; j++) {
					String[] rowItems = mapLines[j].split(",");

					if (rowItems.length != noCols && j != noRows - 1) {
						for (int z = 0; z < rowItems.length; z++) {
							System.out.println(rowItems[z]);
						}
						System.out.println(mapLines[j].length());
						System.out.println(rowItems.length);
						System.out.println(noCols);
						System.out.println(noRows);
						System.out.println(j);
						throw new IllegalArgumentException("Widths are not consistent");
					}
					// debug(mapLines[j]);
					// System.out.println(mapLines[j].length());

					for (int k = 0; k < noCols; k++) {
						// System.out.println(k);
						int currentInt = Integer.parseInt(rowItems[k]);

						if (currentInt != 0) {

							int x = k;
							int y = j - 1;
							int spriteX = (currentInt - 1) % 32;
							int spriteY = (currentInt - 1) / 32;
							// int x = k;
							// int y = j - 1;
							Animation anim;
							if (intAnimationMap.containsKey(currentInt)) {
								anim = intAnimationMap.get(currentInt);
							} else {
								anim = new Animation(spritesheetVal, spriteX, spriteY, 1,
										Animation.AnimationMode.PLAYONCE);
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
								throw new IllegalArgumentException("Map layer must be in the range -1 to 2");
							}

						}

					}

				}

			}

		} catch (IOException e) {
			System.err.println("Level file not found.");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// Error reading file
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

	public int getNoCols() {
		return noCols;
	}

	public int getNoRows() {
		return noRows;
	}

	public double getLevelWidth() {
		return (double) noCols * Tile.width;
	}

	public double getLevelHeight() {
		return (double) noRows * Tile.width;
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
