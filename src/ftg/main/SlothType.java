package ftg.main;

import java.io.File;
import java.io.IOException;

import org.ini4j.Wini;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SlothType extends StateBasedGame {
	
	public static final int OPENINGSTATE = 0;
	public static final int MAINMENU = 1;
	public static final int GAMESTATE = 2;
	public static final int INSTRUCTIONSSTATE = 3;
	public static final int OPTIONSSTATE = 4;
	public static final int CREDITSSTATE = 5;
	
	// --- Options --- //
	public static boolean DEBUGMODE = false;
	public static boolean COLLISIONTEST = false;
	public static final boolean SOUND = true;
	
	public static int ScreenWidth = 800;
	public static int ScreenHeight = 600;

	public SlothType() {
		super("Fuck This Game");
		
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setIcons(new String[] {"res/SlothIcon.png", "res/SlothIcon32.png"});
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
		this.addState(new opening(OPENINGSTATE));
		this.addState(new playstate(GAMESTATE));
		this.addState(new mainmenustate(MAINMENU));
		this.addState(new instructionsstate(INSTRUCTIONSSTATE));
		this.addState(new optionsstate(OPTIONSSTATE));
		this.addState(new creditsstate(CREDITSSTATE));
		try { createINI("res/config/options.ini"); System.out.println("System file created."); } catch (IOException e) { e.printStackTrace(); }
	}

	    // # - Begin - # //
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new SlothType());
		app.setDisplayMode(ScreenWidth, ScreenHeight, false);
		app.start();
	}
	
	public void createINI(String filename) throws IOException {
		Wini ini = new Wini(new File(filename));
		ini.add("[testing]");
		ini.store();
	}
}
