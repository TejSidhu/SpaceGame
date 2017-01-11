package entityy;
import com.badlogic.gdx.ApplicationAdapter;
//am in the systej, cheeky little me
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Boom {
	public static final float frameLength = 0.2f;
	public static final int ofset = 40; //This will help in finding the center of the asteroid as it takes 16 from 2 sides to find the centre
	public static final int Boom_SIZE = 128;
//	public static final int sixe = 32; //MEant to be size of image but too late for fixing the typo
	
	//public static Animation anime = null;
	@SuppressWarnings("rawtypes")
	Animation anime1;
	float x, y; //Loaction of the asteroid explosion
	float stateTimer;
	AnimatedSprite animatedSPRITE;
	public boolean explosion_removal_service = false;
	
	private TextureAtlas explosionsAtlas;
	private Animation <TextureRegion>animation;
	private float timePassed = 0;
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public Boom (float x, float y ){
		this.x = x-ofset;
		this.y = y - ofset;
		 //stateTimer  = 0;
		//anime1 = new Animation(frameLength,(new TextureRegion(new Texture("explosion1.png"))), (new TextureRegion(new Texture("explosion2.png"))), (new TextureRegion(new Texture("explosion3.png"))));
		//System.out.println(stateTimer);
		explosionsAtlas = new TextureAtlas("explosions.atlas");
		animation = new Animation<TextureRegion>(1/3f, explosionsAtlas.getRegions());
 //hello tej i am in the system
		
	}
	
	public void update(){
		if(animation.isAnimationFinished(stateTimer)){
			explosion_removal_service = true;
		}
	}
	public void render(SpriteBatch batch){
		//batch.draw((TextureRegion)anime1.getKeyFrame(stateTimer += Gdx.graphics.getDeltaTime()), x, y, Boom_SIZE, Boom_SIZE);
		timePassed += Gdx.graphics.getDeltaTime();
		batch.draw(animation.getKeyFrame(timePassed, true), x, y, Boom_SIZE, Boom_SIZE);
		}
	}
		
//im here too

