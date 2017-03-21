package levels;

import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

public class LevelTest {

	public static void main(String[] args) {
		try {
			Level level = new Level("Resources/LevelFiles/custom.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
