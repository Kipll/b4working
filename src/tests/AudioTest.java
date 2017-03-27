package tests;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JSlider;

import org.junit.Before;
import org.junit.Test;

import audio.*;

public class AudioTest {

	@Test
	public void test() {
		JFrame frame = new JFrame("BGM Tester");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BGM bgm = new BGM(50,"/Music/BGM_Asian_Gravedrum.wav");
		BGMComponent panel = new BGMComponent(bgm, 0, 100, 50);
		frame.add(panel);
		bgm.play();
		frame.setVisible(true);
		assertEquals(true,bgm.isplay());
		bgm.stop();
		assertEquals(false,bgm.isplay());
	}
	
	@Test
	public void volumeValue(){
		JFrame frame = new JFrame("BGM Tester");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BGM bgm = new BGM(50,"/Music/BGM_Asian_Gravedrum.wav");
		
		assertEquals(50,bgm.getValue());
		
		bgm.setValue(100);
		assertEquals(100,bgm.getValue());
	}
	
	@Test
	public void muteValue(){
		JFrame frame = new JFrame("BGM Tester");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BGM bgm = new BGM(50,"/Music/BGM_Asian_Gravedrum.wav");
		
		assertEquals(false,bgm.getMuteStatus());
		
		bgm.setMuteStatus(true);
		assertEquals(true,bgm.getMuteStatus());
		
	}
	
	@Test
	public void muteButton(){
		JFrame frame = new JFrame("BGM Tester");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BGM bgm = new BGM(50,"/Music/BGM_Asian_Gravedrum.wav");
		BGMModel model = new BGMModel(bgm);
		BGMMuteButton button = new BGMMuteButton(model);
		frame.add(button);
		
		assertEquals(false,bgm.getMuteStatus());
		
		button.mute.doClick();
		assertEquals(true,bgm.getMuteStatus());
	}

	
	
}
