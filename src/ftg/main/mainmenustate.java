package ftg.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class mainmenustate extends BasicGameState {
	
	// Images
	Image Menu, Instructions, InsAlt, Play, PlayAlt, Options, OptionsAlt, Quit, QuitAlt, Credits, CreditsAlt = null;
	
	// Button Boolean
	boolean IsInPlay = false;
	boolean IsInInstructions = false;
	boolean IsInOptions = false;
	boolean IsInQuit = false;
	boolean IsInCredits = false;
	
	// Values
	int MouseX, MouseY;
	
	protected int stateID = 1;
	
	public mainmenustate(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame stg) throws SlickException {
		Menu = new Image("res/Menu.png");
		Instructions = new Image("res/Instructions.png");
		InsAlt = new Image("res/InstructionsAlt.png");
		Play = new Image("res/Play.png");
		PlayAlt = new Image("res/PlayAlt.png");
		Options = new Image("res/Options.png");
		OptionsAlt = new Image("res/OptionsAlt.png");
		Quit = new Image("res/Quit.png");
		QuitAlt = new Image("res/QuitAlt.png");
		Credits = new Image("res/Credits.png");
		CreditsAlt = new Image("res/CreditsAlt.png"); 
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stg, Graphics g) throws SlickException {
		g.drawImage(Menu, 0, 0);
		if(IsInPlay) {
			PlayAlt.draw(80, 235);
		}else{
			Play.draw(80, 235);
		}
		if(IsInInstructions) {
			InsAlt.draw(80, 350);
		}else{
			Instructions.draw(80, 350);		
		}
		if(IsInOptions) {
			OptionsAlt.draw(80, 465);
		}else{
			Options.draw(80, 465);
		}
		if(IsInQuit) {
			QuitAlt.draw(525, 465);
		}else{
			Quit.draw(525, 465);
		}
		if(IsInCredits) {
			CreditsAlt.draw(425, 235);
		}else{
			Credits.draw(425, 235);
		}
	}

	// TODO: Make switch
	@Override
	public void update(GameContainer gc, StateBasedGame stg, int delta) throws SlickException {
		Input in = gc.getInput();
		MouseX = in.getAbsoluteMouseX();
		MouseY = in.getAbsoluteMouseY();
		if(in.isKeyPressed(Input.KEY_ENTER)) {
			stg.enterState(SlothType.GAMESTATE);
		}
		if((MouseX >= 80 && MouseX <= 80 + Play.getWidth()) && (MouseY >= 235 && MouseY <= 235 + Play.getHeight())) {
			IsInPlay = true;
		}else{
			IsInPlay = false;
		}
		if((MouseX >= 80 && MouseX <= 80 + Instructions.getWidth()) && (MouseY >= 350 && MouseY <= 350 + Instructions.getHeight())) {
			IsInInstructions = true;
		}else{
			IsInInstructions = false;
		}
		if((MouseX >= 80 && MouseX <= 80 + Options.getWidth()) && (MouseY >= 465 && MouseY <= 465 + Options.getHeight())) {
			IsInOptions = true;
		}else{
			IsInOptions = false;
		}
		if((MouseX >= 525 && MouseX <= 525 + Quit.getWidth()) && (MouseY >= 450 && MouseY <= 450 + Quit.getHeight())) {
			IsInQuit = true;
		}else{
			IsInQuit = false;
		}
		if((MouseX >= 425 && MouseX <= 425 + Credits.getWidth()) && (MouseY >= 235 && MouseY <= 235 + Credits.getHeight())) {
			IsInCredits = true;
		}else{
			IsInCredits = false;
		}
		if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (IsInPlay){
				stg.enterState(SlothType.GAMESTATE);
			}
			if(IsInInstructions){
				stg.enterState(SlothType.INSTRUCTIONSSTATE);
			}
			if(IsInOptions){
				stg.enterState(SlothType.OPTIONSSTATE);
			}
			if(IsInQuit){
				gc.exit();
			}
			if(IsInCredits){
				stg.enterState(SlothType.CREDITSSTATE);
			}
        }
	}
}
