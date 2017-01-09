package entityy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class bullets {

	public static final int BULLET_SPEED = 500;
	public static final int defaultY = 40;
	public static Texture texture;
	
	float bulletX, bulletY;
	
	public boolean remove_BULLET;
	
	public bullets(float bulletX){
		this.bulletX = bulletX;
		this.bulletY = defaultY;
		
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
		batch.draw(texture, bulletX, bulletY);
	}
}
