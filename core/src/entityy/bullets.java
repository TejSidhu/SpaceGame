package entityy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import toolss.CollisionDetection;

public class bullets {

	public static final int BULLET_SPEED = 500;
	public static final int defaultY = 40;
	public static Texture texture;
	public static final int Width = 3;
	public static final int Height = 12;
	
	float bulletX, bulletY;
	
	CollisionDetection detect;
	public boolean remove_BULLET;
	
	public bullets(float bulletX){
		this.bulletX = bulletX;
		this.bulletY = defaultY;

		this.detect = new CollisionDetection(bulletX,bulletY, Width, Height);
		if(texture == null){
			texture = new Texture("bullet.png");
			
		}
	}
	
	public void update(float deltaTime){
		bulletY += BULLET_SPEED * deltaTime;
		if(bulletY > Gdx.graphics.getHeight()){{
			remove_BULLET = true;
		}
			
		}
	}
	public void render(SpriteBatch batch){
		detect.move(bulletX, bulletY);
		batch.draw(texture, bulletX, bulletY);
	}
	public CollisionDetection getCollisionDetection(){
		return detect;
	}
}
