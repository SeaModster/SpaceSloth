package ftg.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class instructionsstate extends BasicGameState {
	
	// Images
	Image Instructions, Back, BackAlt = null;
	
	// booleans
	boolean IsInBackButton = false;
	
	// Values
	int MouseX, MouseY;
	
	protected int stateID = 3;
	
	public instructionsstate(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame stg) throws SlickException {
		Instructions = new Image("res/InstructionsScreen.png");
		Back = new Image("res/BackButton.png");
		BackAlt = new Image("res/BackButtonAlt.png");
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stg, Graphics g) throws SlickException {
		Instructions.draw(0, 0);
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
		if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (IsInBackButton){
				stg.enterState(SlothType.MAINMENU);
			}
        }
	}
}
