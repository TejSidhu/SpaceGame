package entityy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Boom {
	public static final float frameLength = 0.5f;
	public static final int ofset = 15; //This will help in finding the center of the asteroid as it takes 16 from 2 sides to find the centre
	public static final int Boom_SIZE = 64;
	public static final int sixe = 32; //MEant to be size of image but too late for fixing the typo
	
	//public static Animation anime = null;
	Animation[] anime1;
	public int boom_STAGE;
	float x, y; //Loaction of the asteroid explosion
	float stateTimer;
	
	public boolean explosion_removal_service = false;
	
	@SuppressWarnings("unchecked")
	public Boom (float x, float y ){
		this.x = x-ofset;
		this.y = y - ofset;
		stateTimer  = 0;
		boom_STAGE = 0;
		anime1 = new Animation[3];
		TextureRegion[] boomSprite = TextureRegion.split(new Texture("explosion.png"), sixe, sixe)[0];
		anime1[0] = new Animation(frameLength, boomSprite[0]);
		anime1[1] = new Animation(frameLength, boomSprite[1]);
		anime1[2] = new Animation(frameLength, boomSprite[2]);
		System.out.println(stateTimer);
	}
	public void update(float deltaTime){
		stateTimer += deltaTime;
		if(anime1[boom_STAGE].isAnimationFinished(stateTimer)){
			explosion_removal_service = true;
		}

	}
	public void render(SpriteBatch batch){
		batch.draw((TextureRegion)anime1[boom_STAGE].getKeyFrame(stateTimer), x, y, Boom_SIZE, Boom_SIZE);
		
	}
}

