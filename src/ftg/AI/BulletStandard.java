package ftg.AI;

import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class BulletStandard extends Entity {

	public static String BULLET = "bullet";
	public BulletStandard(float x, float y) {
		super(x, y);
		Image img = ResourceManager.getImage("res/Bullet1.png");
		setGraphic(img);
		setHitBox(0, 0, img.getWidth(), img.getHeight());
		addType(BULLET);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.MOUSE_LEFT_BUTTON)) {
			
		}
	}
}
