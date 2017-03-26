package levels;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import game.Animation;
import game.Circle;
import game.SpritesheetEnum;
import game.Viewport;

/**
 * The Class Level.
 */
public class Level {

	/** The number of columns of tiles in the map. */
	private int noCols;

	/** The number of rows of tiles in the map. */
	private int noRows;

	/** The tiles behind all others. */
	private ArrayList<Tile> backTiles = new ArrayList<Tile>();

	/** The tiles on top of background but behind monsters/players. . */
	private ArrayList<Tile> middleTiles = new ArrayList<Tile>();

	/** The tiles on top of monsters/players. */
	private ArrayList<Tile> frontTiles = new ArrayList<Tile>();

	/** The tiles which act as collisions to player. */
	private ArrayList<Tile> collisionTiles = new ArrayList<Tile>();

	/** The spritesheet enum specifying which tilesheet to use. */
	private int spritesheetEnum = SpritesheetEnum.TERRAIN;

	/**
	 * Instantiates a new level by reading level data from an XML file..
	 *
	 * @param xml
	 *            the path to the XML level file
	 */
	public Level(String xml) {
		parseXML(xml);
	}

	/**
	 * Parses the XML Level file specified and converts it into Tiles which are
	 * then added to backTiles, middleTiles, frontTiles or collisionTiles.
	 *
	 * @param fileName
	 *            the path to the XML level file
	 */
	private void parseXML(String fileName) throws IllegalArgumentException {
		HashMap<Integer, Animation> intAnimationMap = new HashMap<Integer, Animation>();

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

				Element mapElement = (Element) mapNode;
				int layer = Integer.parseInt(mapElement.getAttribute("layer").trim());

				String mapStr = mapElement.getTextContent();

				String[] mapLines = mapStr.split("\\r?\\n");

				if (mapLines.length <= 2) {
					throw new IllegalArgumentException("No map found");
				}

				noRows = mapLines.length - 2;
				noCols = mapLines[1].split(",").length;

				for (int j = 1; j < noRows + 1; j++) {
					String[] rowItems = mapLines[j].split(",");

					for (int k = 0; k < noCols; k++) {
						int currentInt = Integer.parseInt(rowItems[k]);

						if (currentInt != 0) {

							int x = k;
							int y = j - 1;
							int spriteX = (currentInt - 1) % 32;
							int spriteY = (currentInt - 1) / 32;

							Animation anim;
							if (intAnimationMap.containsKey(currentInt)) {
								anim = intAnimationMap.get(currentInt);
							} else {
								anim = new Animation(spritesheetEnum, spriteX, spriteY, 1,
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
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Draw back tiles.
	 *
	 * @param g
	 *            the graphics object to draw to
	 * @param vp
	 *            the viewport of the player
	 */
	public void drawBackTiles(Graphics2D g, Viewport vp) {
		drawTiles(backTiles, g, vp);
	}

	/**
	 * Draw middle tiles.
	 *
	 * @param g
	 *            the graphics object to draw to
	 * @param vp
	 *            the viewport of the player
	 */
	public void drawMiddleTiles(Graphics2D g, Viewport vp) {
		drawTiles(middleTiles, g, vp);
	}

	/**
	 * Draw front tiles.
	 *
	 * @param g
	 *            the graphics object to draw to
	 * @param vp
	 *            the viewport of the player
	 */
	public void drawFrontTiles(Graphics2D g, Viewport vp) {
		drawTiles(frontTiles, g, vp);
	}

	/**
	 * Draw tiles.
	 *
	 * @param tiles
	 *            the arraylist of tiles to draw
	 * @param g
	 *            the graphics object to draw to
	 * @param vp
	 *            the viewport of the player
	 */
	private void drawTiles(Collection<Tile> tiles, Graphics2D g, Viewport vp) {
		for (Tile t : tiles) {
			t.draw(g, vp);
		}
	}

	/**
	 * Gets the number of columns of tiles.
	 *
	 * @return the number of columns of tiles
	 */
	public int getNoCols() {
		return noCols;
	}

	/**
	 * Gets the number of rows of tiles.
	 *
	 * @return the number of rows of tiles
	 */
	public int getNoRows() {
		return noRows;
	}

	/**
	 * Gets the level width in game units.
	 *
	 * @return the level width in game units
	 */
	public double getLevelWidth() {
		return noCols * Tile.width;
	}

	/**
	 * Gets the level height in game units.
	 *
	 * @return the level height in game units
	 */
	public double getLevelHeight() {
		return noRows * Tile.width;
	}

	/**
	 * Checks if the circle intersects with any collision tiles.
	 *
	 * @param c
	 *            the circle to check if intersects
	 * @return true, if circle doesn't intersect any collision tiles.
	 */
	public Boolean validPos(Circle c) {
		for (Tile t : collisionTiles) {
			if (c.intersects(t.getDestination())) {
				return false;
			}
		}

		return true;
	}

}
