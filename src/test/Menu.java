package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import menus.*;

public class Menu {

	public Mainframe m;
	
	@Before
	public void init(){
		m = new Mainframe();
	}
	
	@Test
	public void audiomenu() {
		Audio audio = new Audio(m);
		
		audio.btnBack.doClick();
		assertEquals("back",audio.test);
		
//		audio.btnExit.doClick();
//		assertEquals("exit",audio.test);
		
		audio.tglbtnNewToggleButton.doClick();
		assertEquals("mute",audio.test);
	}

	@Test
	public void controlmenu(){
		Control control = new Control(m);
		
		assertEquals(null,control.test);
		
		control.btnBack.doClick();
		assertEquals("back",control.test);
	}
	
	@Test
	public void startmenu(){
		Start start = new Start(m);
		
		assertEquals(null,start.test);
		
		start.btnAudio.doClick();
		assertEquals("audio",start.test);
		
		start.btnControls.doClick();
		assertEquals("control",start.test);
		
		start.btnFindGame.doClick();
		assertEquals("find",start.test);
	}
	
}
