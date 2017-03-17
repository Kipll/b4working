package levels;

import game.Game;
import game.Viewport;

public class TestLevel extends Level {

	public TestLevel(double roomW, double roomH) {
		super(roomW, roomH);
		this.addProp(new Wall(0.5, 1, 1, 1));
	}

}
