package menus;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class MenuButtonHandler {

	public static ImageIcon loadImageIcon(String p, int s1, int s2) {

		File f = new File(p);
			if (f.exists())
				return new ImageIcon(new ImageIcon(p).getImage().getScaledInstance(s1, s2, Image.SCALE_DEFAULT));
			
			else 
				return new ImageIcon(new ImageIcon("../" + p).getImage().getScaledInstance(s1, s2, Image.SCALE_DEFAULT));


}
}