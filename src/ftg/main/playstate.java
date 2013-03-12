package ftg.main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ftg.AI.AI_Asteroids;
import ftg.AI.BulletStandard;

public class playstate extends BasicGameState {
	
	//Images
	Image ShopOverlay, SpeedIncrease, SpeedIncreaseAlt, Background, ship, MaxHealth, MaxHealthAlt, Asteroid1, Cannon, Bullet1 = null;
	
	//Values
	float ShipSpeed = 0.1f;
	float AsteroidSpeed = 0.1f;
	static float x = 395;
	static float y = 460;
	static int SHIPX;
	static int SHIPY;
	static int CANNONX;
	static int CANNONY;
	static int StandardBulletCount = 0;
	static int StandardBulletLimit = 10;
	static int BULLETS = 10;
	static int BULLET_SPEED = 4;
	int MouseX, MouseY;
	int BackX, BackY;
	int points = 0;
	int health = 3;
	int deltaTrack = 0;
	int timer;
	
	// Store Booleans
	boolean ShopOpened = false;
	boolean SpeedIncreaseButton = false;
	boolean HealthIncreaseButton = false;
	
	// Game Boolean
	
	
	// Objects
	Graphics g2d;
	Circle asteroid = new Circle(x, y, 5);
	AI_Asteroids aia = new AI_Asteroids();
	BulletStandard[] bullets = new BulletStandard[StandardBulletLimit];
	
	protected int stateID = 2;
	
	public playstate(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}
	
	public int getSizeWidth() {
		return SlothType.ScreenWidth;
	}
	public int getSizeHeight() {
		return SlothType.ScreenHeight;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame stg) throws SlickException {
		g2d = gc.getGraphics();
		ship = new Image("res/SlothShip.png");
		Background = new Image("res/Background.png");
		ShopOverlay = new Image("res/SlothShop.png");
		SpeedIncrease = new Image("res/IncreaseSpeed.png");
		SpeedIncreaseAlt = new Image("res/IncreaseSpeedAlt.png");
		MaxHealth = new Image("res/IncreaseMH.png");
		MaxHealthAlt = new Image("res/IncreaseMHAlt.png");
		Asteroid1 = new Image("res/Rock2.png");
		Cannon = new Image("res/Cannon.png");
		Bullet1 = new Image("res/Bullet3.png");
		aia.GenerateAsteroids1();
		gc.setShowFPS(false);
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stg, Graphics g) throws SlickException {
		g.drawImage(Background, BackX, BackY);
		ship.draw(x, y);
		Cannon.draw(x - 8, y + 5);
		for (int i = 0; i < 20; i++) {
			Asteroid1.draw(aia.getAsteroids().get(i).getCenterX(), aia.getAsteroids().get(i).getCenterY(), 0.05f);
		}
		if(SlothType.COLLISIONTEST) {
			g.setColor(Color.green);
			g.draw(new Rectangle(SHIPX, SHIPY, ship.getWidth(), ship.getHeight()));
			for (int i = 0; i < aia.getAsteroids().size(); i++) {
				g.draw(aia.getAsteroids().get(i));
				g.setColor(Color.red);
				g.fill(aia.getAsteroids().get(i));
			}
		}
		if(SlothType.DEBUGMODE) {
			g.setColor(Color.green);
			g.drawString("ShipSpeed: " + ShipSpeed, 0, 0);
			g.drawString("AsteroidSpeed: " + AsteroidSpeed, 0, 20);
			g.drawString("MouseX: " + MouseX, 0, 40);
			g.drawString("MouseY: " + MouseY, 0, 60);
			g.drawString("ShipX: " + SHIPX, 0, 80);
			g.drawString("ShipY: " + SHIPY, 0, 100);
			g.drawString("Timer: " + timer / 1000, 0, 120);
			g.drawString("FPS: " + deltaTrack, 0, 140);
			g.drawString("BulletCount: " + StandardBulletCount, 0, 160);
		}
		if(ShopOpened){
			ShopOverlay.draw(0, 0);
			g.drawString(""+ShipSpeed, 550, 212);
			g.drawString(""+health, 550, 300);
			g.drawString(""+points, 740, 80);
			if(SpeedIncreaseButton){
				SpeedIncreaseAlt.draw(30, 180);
			}else{
				SpeedIncrease.draw(30, 180);
			}
			if(HealthIncreaseButton){
				MaxHealthAlt.draw(30, 280);
			}else{
				MaxHealth.draw(30, 280);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame stg, int delta) throws SlickException {
		Input in = gc.getInput();
		deltaTrack = delta;
		timer += delta;
		MouseX = in.getAbsoluteMouseX();
		MouseY = in.getAbsoluteMouseY();
		SHIPX = (int) x;
		SHIPY = (int) y;
		if(!ShopOpened){
			updateAsteriods();
			updateShip();
			GenerateAsteroidPositions();
			float xDistance = MouseX - x;
			float yDistance = MouseY - y;
			double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
			Cannon.setRotation((float)angleToTurn);
		}
		double hip = ShipSpeed * delta;
		float rotation = ship.getRotation();
		if(in.isKeyDown(Input.KEY_W)) {
			x += hip * Math.sin(Math.toRadians(rotation));
			y -= hip * Math.cos(Math.toRadians(rotation));
		}
		if(in.isKeyDown(Input.KEY_S)) {
			double neghip = -ShipSpeed * delta;
			float negrotation = ship.getRotation();
			x += neghip * Math.sin(Math.toRadians(negrotation));
			y -= neghip * Math.cos(Math.toRadians(negrotation));
		}
		if(in.isKeyDown(Input.KEY_D)) {
			x += hip * Math.cos(Math.toRadians(rotation));
		}
		if(in.isKeyDown(Input.KEY_A)) {
			x -= hip * Math.cos(Math.toRadians(rotation));
		}
		if(in.isKeyPressed(Input.KEY_M)) {
			stg.enterState(SlothType.MAINMENU);
		}
		if(in.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit();
		}
		// ---------- Shop Util ---------- //
		if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if(!ShopOpened) {
				StandardBulletCount++;
				if (StandardBulletCount > StandardBulletLimit - 1)
					StandardBulletCount = 0;
			}
			if (ShopOpened && SpeedIncreaseButton) {
				ShipSpeed += 0.1f;
			}
			if (ShopOpened && HealthIncreaseButton) {
				health += 1;
			}
        }
		if(in.isKeyPressed(Input.KEY_U)) {
			if(ShopOpened){
				ShopOpened = false;
				gc.resume();
			}else{
				ShopOpened = true;
				gc.pause();
			}
		}
		if(ShopOpened) {
			if((MouseX >= 30 && MouseX <= 30 + SpeedIncrease.getWidth()) && (MouseY >= 180 && MouseY <= 180 + SpeedIncrease.getHeight())) {
				SpeedIncreaseButton = true;
			}else{
				SpeedIncreaseButton = false;
			}
			if((MouseX >= 30 && MouseX <= 30 + MaxHealth.getWidth()) && (MouseY >= 280 && MouseY <= 280 + MaxHealth.getHeight())) {
				HealthIncreaseButton = true;
			}else{
				HealthIncreaseButton = false;
			}
		}
	}
	public void updateAsteriods() {
		aia.Move();
	}
	public void GenerateAsteroidPositions() {
		if(timer / 1000 == 5){
			aia.GenerateAsteroids1();
			timer = 0;
		}
	}
	public void updateShip() {
		if(y > 600) {
			y = -getSizeHeight() + 570;
		}else if(y < -60) {
			y = getSizeHeight() - 30;
		}else if(x > 790) {
			x = -getSizeWidth() + 740;
		}else if (x < -60){
			x = getSizeWidth() - 60;
		}
	}
}
