package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.kaptaan.monte_enterprise.SpaceGame;

public class MainGameScreen implements Screen{
	

	
	float x;
	float y;
	public static final float SPEED = 300;
	public static final float ANIMATION_SPEED = 0.5f;
	public static final int Ship_Pixel_WIDTH = 17;
	public static final int Ship_Pixel_HEIGHT = 32;
	public static final int Ship_WIDTH = Ship_Pixel_WIDTH *3;
	public static final int Ship_HEIGHT = Ship_Pixel_HEIGHT *3;
	float stateTime; //It stores how long the animation has been running
	int roll;
	public static final float ROLL_SWITCH_TIMER = 0.1f; //Essential for switching the roll stage; how much time it take every time it rolls
	float ROLL_TIMER;
	
	@SuppressWarnings("rawtypes")
	Animation[] rolls;
	float positions []; 
	
	SpaceGame spaceG;
	
	


	public MainGameScreen(SpaceGame spaceG){
		this.spaceG = spaceG;
		y = 15;
		x = SpaceGame.screen_width/2 - Ship_WIDTH/2;
		roll = 2;
		rolls = new Animation[5];
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("ship.png"),Ship_Pixel_WIDTH,Ship_Pixel_HEIGHT);
		ROLL_TIMER = 0;
		rolls[0] = new Animation(ANIMATION_SPEED, rollSpriteSheet[2]);//All left
		rolls[1] = new Animation(ANIMATION_SPEED, rollSpriteSheet[1]);
		rolls[2] = new Animation(ANIMATION_SPEED, rollSpriteSheet[0]);//No tilt
		rolls[3] = new Animation(ANIMATION_SPEED, rollSpriteSheet[3]);
		rolls[4] = new Animation(ANIMATION_SPEED, rollSpriteSheet[4]);//Right
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//System.out.println(Gdx.graphics.getDeltaTime());
	
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			x-=SPEED*Gdx.graphics.getDeltaTime();
			if(x < 0){
				x=0;
			}
			
			ROLL_TIMER -= Gdx.graphics.getDeltaTime();
			
			if(Math.abs(ROLL_TIMER) > ROLL_SWITCH_TIMER){
				ROLL_TIMER = 0;
				roll--;
				if(roll<0){
					roll = 0;
				}
			}
			
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			x+=SPEED*Gdx.graphics.getDeltaTime();
			
			if(x > spaceG.screen_width-Ship_WIDTH){
				x = spaceG.screen_width-Ship_WIDTH;
			}
			
			ROLL_TIMER += Gdx.graphics.getDeltaTime();
			if(Math.abs(ROLL_TIMER) > ROLL_SWITCH_TIMER){
				ROLL_TIMER = 0;
				roll+= 1;
				if(roll>4){
					roll = 4;
				}
			}
		}
		stateTime += delta;
		spaceG.batch.begin();



		//spaceG.batch.draw(img, x, y);
		spaceG.batch.draw((TextureRegion)rolls[roll].getKeyFrame(stateTime, true), x, y, Ship_WIDTH, Ship_HEIGHT);
		spaceG.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		
		
	}

}
