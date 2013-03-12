package ftg.AI;

import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class AI_Asteroids {
	/*
	public static String BULLET = "bullet";
	public AI_Asteroids(float x, float y) {
		super(x, y);
		Image img = ResourceManager.getImage("res/Rock2.png");
		setGraphic(img);
		setHitBox(0, 0, img.getWidth(), img.getHeight());
		addType(BULLET);
	}
	*/
	private ArrayList<Shape> asteroids;
	
	public void GenerateAsteroids1() {
		asteroids = new ArrayList<Shape>();
		for (int i = 0; i < 20; i++) {
			int randomNum1 = 0 + (int)(Math.random() * ((800 - 0) + 1));
			int randomNum2 = 600 + (int)(Math.random() * ((1000 - 600) + 1));
			asteroids.add(new Rectangle(50, 50, 50, 50));
			asteroids.get(i).setCenterX(randomNum1);
			asteroids.get(i).setCenterY(randomNum2);
			
		}
	}
	public ArrayList<Shape> getAsteroids() {
		return asteroids;
	}
	public void Move() {
		ArrayList<Shape> ast = getAsteroids();
		for (int i = 0; i < 20; i++) {
			Random ran = new Random();
			int picked = ran.nextInt(10);
			Shape a = ast.get(i);
			if(a.getY() > 1000) {
				a.setY(-600 + 400);
			}else if(a.getY() < -400) {
				a.setY(600 - 30);
			}else if(a.getX() > 790) {
				a.setX(-800 + 740);
			}else if (a.getX() < -60){
				a.setX(800 - 60);
			}
			a.setY(a.getY() + picked);	
		}
	}
}
