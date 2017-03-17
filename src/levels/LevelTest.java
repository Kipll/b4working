package levels;

import javax.xml.parsers.ParserConfigurationException;

public class LevelTest {

	public static void main(String[] args) {
		try {
			Level level = new Level("Resources/Levels/levelx.tmx");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
