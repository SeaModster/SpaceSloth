package ftg.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class opening extends BasicGameState {
	
	Image opening = null;
	
	int timer;
	
	protected int stateID = 0;
	
	public opening(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame stg) throws SlickException {
		opening = new Image("res/opening.png");
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stg, Graphics g) throws SlickException {
		g.drawImage(opening, 0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame stg, int delta) throws SlickException {
		Input in = gc.getInput();

		if(in.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit();
		}
		if(in.isKeyPressed(Input.KEY_ENTER)) {
			stg.enterState(SlothType.MAINMENU);
		}
		if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			stg.enterState(SlothType.MAINMENU);
		}
	
	}
}
