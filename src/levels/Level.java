package levels;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sql.rowset.CachedRowSet;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

import game.Circle;
import game.Viewport;

public class Level {

	private static final boolean debug = true;
	private int difficulty;

	private double roomW; // in game units
	private double roomH; // in game units

	private static final String imageFolder = "Resources/Images/";
	private String backgroundImageLocation;
	private int backgroundImageW;
	private int backgroundImageH;

	private ArrayList<Wave> waveList = new ArrayList<Wave>();
	private ArrayList<Prop> propList = new ArrayList<Prop>();

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


	private void parseXML(String fileName) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			File file = new File(fileName);

			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getChildNodes();

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (debug)
					System.out.println("Exploring: " + node.getNodeName());

				switch (node.getNodeName()) {
				case "map":
					NodeList mapChildren = node.getChildNodes();

					break;
				case "monsters":
					break;
				}

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

	protected void addProp(Prop p) {
		propList.add(p);
	}

	public void draw(Graphics2D g, Viewport vp) {
		for (Prop p : propList) {
			p.draw(g, vp);
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
		for (Prop p : propList) {
			if (c.intersects(p.getDestination()))
				return false;
		}

		return true;

	}
}
