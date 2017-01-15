package entityy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.kaptaan.monte_enterprise.SpaceGame;
import toolss.CollisionDetection;

public class Asteroid {
	public static final int ASTEROID_SPEED = 200;
	public static final int Width = 32;
	public static final int HEIGHT= 32;
	public static Texture texture;
	public static Texture texture1;
	
	float asteroidX, asteroidY;
	CollisionDetection detect;
	public boolean remove_ASTEROID;
	
	public Asteroid (float asteroidX){
		this.asteroidX = asteroidX;
		this.asteroidY =SpaceGame.screen_height;
		this.detect = new CollisionDetection(asteroidX,asteroidY, Width, HEIGHT);
		if(texture == null){
			texture = new Texture("asteroid.png");
			
		}
		if(texture1 == null){
			texture1 = new Texture("asteroid1.png");
			
		}
	}
	
	public void update(float deltaTime){
		asteroidY -= ASTEROID_SPEED * deltaTime;
		if(asteroidY < -HEIGHT){
			remove_ASTEROID = true;
		}
			
		}
	public void render(SpriteBatch batch){
		detect.move(asteroidX, asteroidY);
		batch.draw(texture, asteroidX, asteroidY, Width, HEIGHT);

	}
	public void render1(SpriteBatch batch){
		detect.move(asteroidX, asteroidY);
		batch.draw(texture1, asteroidX, asteroidY, Width, HEIGHT);

	}
	public CollisionDetection getCollisionDetection(){
		return detect;
	}
	public  float getX(){
		return asteroidX;
	}
	public  float getY(){
		return asteroidY;
	}
}
