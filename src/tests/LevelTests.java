package tests;


import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import game.Circle;
import levels.Level;

import org.junit.Assert;

public class LevelTests {

	private Level level;

	@Before
	public void setUp() throws Exception {
		level = new Level("Resources/LevelFiles/10x10grassy.xml");
	}

	@Test
	public final void testGetNoCols() {
		int expected = 10;
		int actual = level.getNoCols();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testGetNoRows() {
		int expected = 10;
		int actual = level.getNoRows();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testGetLevelWidth() {
		double expected = 5;
		double actual = level.getLevelWidth();
		Assert.assertEquals(expected, actual, 0);
	}

	@Test
	public final void testGetLevelHeight() {
		double expected = 5;
		double actual = level.getLevelHeight();
		Assert.assertEquals(expected, actual, 0);
	}

	@Test
	public final void testValidPos() {
		Circle circle = new Circle(0.14, new Point2D.Double(0.25, 0.25));
		Assert.assertTrue(level.validPos(circle));
		
		circle = new Circle(10, new Point2D.Double(5, 5));
		Assert.assertFalse(level.validPos(circle));
	}

}
