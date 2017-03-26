package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import game.Animation;
import game.SpritesheetEnum;
import levels.Tile;

public class TileTests {

	int x;
	int y;
	Animation anim;
	Tile tile;

	@Before
	public void setUp() throws Exception {
		x = 9;
		y = 7;
		anim = new Animation(SpritesheetEnum.TERRAIN, 2, 5, 1, Animation.AnimationMode.PLAYONCE);
		tile = new Tile(x, y, anim);
	}

	@Test
	public final void testGetPos() {
		Point2D.Double expected = new Point2D.Double(x, y);
		Point2D.Double actual = tile.getPos();
		Assert.assertEquals(expected.getX(), actual.getX(), 0);
		Assert.assertEquals(expected.getY(), actual.getY(), 0);
	}

	@Test
	public final void testDisposable() {
		boolean expected = false;
		boolean actual = tile.disposable();
		Assert.assertEquals(expected, actual);
	}


	@Test
	public final void testClone() {
		Object expected = tile;
		Object actual = tile.clone();
		assertEquals(expected, actual);
	}

	@Test
	public final void testGetAnimation() {
		Animation expected = anim;
		Animation actual = tile.getAnimation();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testGetDestination() {
		Rectangle2D expected = new Rectangle2D.Double(4.5, 3.5, 0.5, 0.5);
		Rectangle2D actual = tile.getDestination();
		Assert.assertEquals(expected, actual);
	}

}
