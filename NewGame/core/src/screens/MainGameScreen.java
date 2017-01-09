package screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import entityy.bullets;
import me.kaptaan.monte_enterprise.SpaceGame;

public class MainGameScreen implements Screen{
	

	
	float x;
	float y;
	public static final float SPEED = 350;
	public static final float ANIMATION_SPEED = 0.5f;
	public static final int Ship_Pixel_WIDTH = 17;
	public static final int Ship_Pixel_HEIGHT = 32;
	public static final int Ship_WIDTH = Ship_Pixel_WIDTH *3;
	public static final int Ship_HEIGHT = Ship_Pixel_HEIGHT *3;
	public static final float Bullet_Wait_Timer = 0.2f;
	float stateTime; //It stores how long the animation has been running
	int roll;
	public static final float ROLL_SWITCH_TIMER = 0.2f; //Essential for switching the roll stage; how much time it take every time it rolls
	float bulletTimer;//Keeps track of bullet timer
	float ROLL_TIMER;//Actual timer to keep track of the ROLL_SWITCH_TIMER
	
	ArrayList<bullets> bullet;
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	Animation[] rolls;
	float positions []; 
	
	SpaceGame spaceG;
	
	


	public MainGameScreen(SpaceGame spaceG){
		this.spaceG = spaceG;

		y = 15;
		x = SpaceGame.screen_width/2 - Ship_WIDTH/2;
		bullet = new ArrayList<bullets>();
		
		roll = 2;
		rolls = new Animation[5];
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("ship.png"),Ship_Pixel_WIDTH,Ship_Pixel_HEIGHT);
		ROLL_TIMER = 0;
		bulletTimer = 0;
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
		bulletTimer += delta;
		//Bullet code
		ArrayList<bullets> bullet_removal_service = new ArrayList<bullets>();
		if(Gdx.input.isKeyPressed(Keys.SPACE) && bulletTimer >= Bullet_Wait_Timer){
			if(roll != 2){
				bullet.add(new bullets(x + 12));
				bullet.add(new bullets(x + Ship_WIDTH - 14));
			}else{
			bullet.add(new bullets(x + 4));
			bullet.add(new bullets(x + Ship_WIDTH - 4));
			}
			bulletTimer = 0;
		}
		//Bullet updater
		 for(bullets bullets: bullet){
			 bullets.update(delta);
			 if(bullets.remove_BULLET){
				 bullet_removal_service.add(bullets);
			 }
		 }
		 bullet.removeAll(bullet_removal_service);
		
		//Code for ship movement
		
		//System.out.println(Gdx.graphics.getDeltaTime());
	
		//This part is just for fun, maybe later on this could be a feature 
			/*
			if(Gdx.input.isKeyPressed(Keys.UP)){
				y+=SPEED*Gdx.graphics.getDeltaTime();
			}
			if(Gdx.input.isKeyPressed(Keys.DOWN)){
					y-=SPEED*Gdx.graphics.getDeltaTime();
			}*/
			
		//Roll Updater 
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			x-=SPEED*Gdx.graphics.getDeltaTime();
			if(x < 0){
				x=0;
			}
			//Roll update if button is clicked for a short period of time/ just one press
			if(Gdx.input.isKeyJustPressed(Keys.LEFT) && !Gdx.input.isKeyJustPressed(Keys.RIGHT) && roll > 0){
				ROLL_TIMER = 0;
				roll--;
			}
			
			//Roll update
			ROLL_TIMER -= Gdx.graphics.getDeltaTime();
			
			if(Math.abs(ROLL_TIMER) > ROLL_SWITCH_TIMER && roll > 0){
				ROLL_TIMER -= ROLL_SWITCH_TIMER;
				roll--;
				
			}
			
		}else{
			if(roll < 2 && roll < 4){ //If the ship is already at the max roll position on a particular side then it will return back to the center/natural position
				//x+= SPEED*Gdx.graphics.getDeltaTime();
				ROLL_TIMER += Gdx.graphics.getDeltaTime();
				if(Math.abs(ROLL_TIMER) > ROLL_SWITCH_TIMER){
					ROLL_TIMER = 0;
					roll+= 1;
			}
		}
		}
			
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			x+=SPEED*Gdx.graphics.getDeltaTime();
			
			if(Gdx.input.isKeyJustPressed(Keys.RIGHT) && !Gdx.input.isKeyJustPressed(Keys.LEFT) && roll > 0){
				ROLL_TIMER = 0;
				roll++;
				if(roll>4){
					roll = 4;
				}
			}
			
			if(x > spaceG.screen_width-Ship_WIDTH){
				x = spaceG.screen_width-Ship_WIDTH;
			}
			
			ROLL_TIMER += Gdx.graphics.getDeltaTime();
			if(Math.abs(ROLL_TIMER) > ROLL_SWITCH_TIMER){
				ROLL_TIMER -= ROLL_SWITCH_TIMER;
				roll+= 1;
				if(roll>4){
					roll = 4;
				}
			}
		}else{
			if(roll > 2 ){
				//x-=SPEED*Gdx.graphics.getDeltaTime();
				if(x < 0){
					x=0;
				}
				
				ROLL_TIMER -= Gdx.graphics.getDeltaTime();
				
				if(Math.abs(ROLL_TIMER) > ROLL_SWITCH_TIMER){
					ROLL_TIMER -= ROLL_SWITCH_TIMER;
					roll--;
					if(roll<0){
						roll = 0;
					}
				}
			}
		}
		stateTime += delta;
		spaceG.batch.begin();
		
		for(bullets bullet: bullet ){
			bullet.render(spaceG.batch);
		}


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
