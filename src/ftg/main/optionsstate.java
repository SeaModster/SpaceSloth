package ftg.main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class optionsstate extends BasicGameState {
	
	// Images
	Image OptionsBackground, Back, BackAlt = null;
	
	// booleans
	boolean IsInBackButton = false;
	boolean IsInDebugButton = false;
	boolean IsInCollsionButton = false;
	
	// Values
	int MouseX, MouseY;
	
	Rectangle debug = new Rectangle(190, 247, 350, 25);
	Rectangle collsion = new Rectangle(190, 285, 375, 25);
	
	protected int stateID = 4;
	
	public optionsstate(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame stg) throws SlickException {
		OptionsBackground = new Image("res/OptionsScreen.png");
		Back = new Image("res/BackButton.png");
		BackAlt = new Image("res/BackButtonAlt.png");
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stg, Graphics g) throws SlickException {
		OptionsBackground.draw(0, 0);
		g.setColor(Color.green);
		g.draw(debug);
		g.setColor(Color.white);
		g.drawString("Enable/Disable Debugging mode: "+SlothType.DEBUGMODE, 200, 250);
		g.setColor(Color.green);
		g.draw(collsion);
		g.setColor(Color.white);
		g.drawString("Enable/Disable ship collision box: "+SlothType.COLLISIONTEST, 200, 287);
		if(IsInBackButton) {
			BackAlt.draw(15, 485);
		}else{
			Back.draw(15, 485);
		}
	}

	// TODO: Make switch
	@Override
	public void update(GameContainer gc, StateBasedGame stg, int delta) throws SlickException {
		Input in = gc.getInput();
		MouseX = in.getAbsoluteMouseX();
		MouseY = in.getAbsoluteMouseY();
		if((MouseX >= 15 && MouseX <= 15 + Back.getWidth()) && (MouseY >= 485 && MouseY <= 485 + Back.getHeight())) {
			IsInBackButton = true;
		}else{
			IsInBackButton = false;
		}
		if((MouseX >= 190 && MouseX <= 190 + debug.getWidth()) && (MouseY >= 245 && MouseY <= 245 + debug.getHeight())) {
			IsInDebugButton = true;
		}else{
			IsInDebugButton = false;
		}
		if((MouseX >= 190 && MouseX <= 190 + collsion.getWidth()) && (MouseY >= 285 && MouseY <= 285 + collsion.getHeight())) {
			IsInCollsionButton = true;
		}else{
			IsInCollsionButton = false;
		}
		if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (IsInBackButton){
				stg.enterState(SlothType.MAINMENU);
			}
			if (IsInDebugButton && SlothType.DEBUGMODE){
				SlothType.DEBUGMODE = false;
			}else if(IsInDebugButton && SlothType.DEBUGMODE == false) {
				SlothType.DEBUGMODE = true;
			}
			if (IsInCollsionButton && SlothType.COLLISIONTEST){
				SlothType.COLLISIONTEST = false;
			}else if(IsInCollsionButton && SlothType.COLLISIONTEST == false) {
				SlothType.COLLISIONTEST = true;
			}
        }
	}
}
