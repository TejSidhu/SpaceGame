package screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import entityy.Asteroid;
import entityy.Boom;
import entityy.bullets;
import me.kaptaan.monte_enterprise.SpaceGame;
import toolss.CollisionDetection;

public class MainGameScreen implements Screen{
	

	
	
	public static final float SPEED = 350;
	public static final float ANIMATION_SPEED = 0.5f;
	public static final int Ship_Pixel_WIDTH = 17;
	public static final int Ship_Pixel_HEIGHT = 32;
	public static final int Ship_WIDTH = Ship_Pixel_WIDTH *3;
	public static final int Ship_HEIGHT = Ship_Pixel_HEIGHT *3;
	public static final float Bullet_Wait_Timer = 0.2f;
	public static final float MIN_SPAWN_TIME = 0.1f;
	public static final float MAX_SPAWN_TIME = 0.4f;
	//Float party
	float x;
	float y;
	float stateTime; //It stores how long the animation has been running
	int roll;
	public static final float ROLL_SWITCH_TIMER = 0.2f; //Essential for switching the roll stage; how much time it take every time it rolls
	float bulletTimer;//Keeps track of bullet timer
	float ROLL_TIMER;//Actual timer to keep track of the ROLL_SWITCH_TIMER
	float positions []; 
	float Asteroid_Spawn_TIMER;
	ArrayList<bullets> bullet;
	ArrayList<Asteroid> asteroids;
	ArrayList<Boom> booms;
	Random dice; 
	BitmapFont fontSCORE; 
	int score;
	@SuppressWarnings("rawtypes")
	Animation [] rolls;
	Asteroid asteroid;
	Boom boom;
	SpaceGame spaceG;
	Texture blank;
	float health = 1; //1 = full health, 0 = Dead/No health
	CollisionDetection playerDetect;
	Texture controls;
	boolean removeControls;

	public MainGameScreen(SpaceGame spaceG){
		this.spaceG = spaceG;
		score = 0;
		
		y = 15;
		x = SpaceGame.screen_width/2 - Ship_WIDTH;//Incase an error comes up, here, the ship_width was /2 but i have removed it. This is a reminder just in case
		bullet = new ArrayList<bullets>();
		asteroids = new ArrayList<Asteroid>();
		booms = new ArrayList<Boom>();
		dice = new Random();
		
		if(SpaceGame.mobile_ = true){
			controls = new Texture("controls.png");//Saves memory and stuff in case it isnt on the computer
		}
		
		
		
		
		playerDetect = new CollisionDetection(0, 0, Ship_WIDTH, Ship_HEIGHT);
		fontSCORE = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
		Asteroid_Spawn_TIMER = dice.nextFloat() * (MAX_SPAWN_TIME - MIN_SPAWN_TIME) + MIN_SPAWN_TIME ;
		roll = 2;
		rolls = new Animation[5];
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("ship.png"),Ship_Pixel_WIDTH,Ship_Pixel_HEIGHT);
		ROLL_TIMER = 0;
		bulletTimer = 0;
		rolls[0] = new Animation<TextureRegion>(ANIMATION_SPEED, rollSpriteSheet[2]);//All left
		rolls[1] = new Animation<TextureRegion>(ANIMATION_SPEED, rollSpriteSheet[1]);
		rolls[2] = new Animation<TextureRegion>(ANIMATION_SPEED, rollSpriteSheet[0]);//No tilt
		rolls[3] = new Animation<TextureRegion>(ANIMATION_SPEED, rollSpriteSheet[3]);
		rolls[4] = new Animation<TextureRegion>(ANIMATION_SPEED, rollSpriteSheet[4]);//Right
		blank = new Texture("blank.png");
		spaceG.scroolBG.set_SpeedFixed(false);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.3f, 0.5f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		bulletTimer += delta;
		//Bullet code
		
		ArrayList<bullets> bullet_removal_service = new ArrayList<bullets>();
		if((isLeft() || isRight()) && bulletTimer >= Bullet_Wait_Timer){//Only doing this because of the mobile version, this sucks
			removeControls = true;
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
		
		
		 //Asteroids
		 
		 //Spawn code
		 Asteroid_Spawn_TIMER -= delta;
		 if(Asteroid_Spawn_TIMER < 0 ){
			 Asteroid_Spawn_TIMER = dice.nextFloat() * (MAX_SPAWN_TIME - MIN_SPAWN_TIME) + MIN_SPAWN_TIME ;
			 asteroids.add(new Asteroid(dice.nextInt(SpaceGame.screen_width - Asteroid.Width)));
			 }
		 
		 //Asteroid updater 
		 ArrayList<Asteroid> asteroid_removal_service = new ArrayList<Asteroid>();
		 for(Asteroid asteroid: asteroids){
			 asteroid.update(delta);
			 if(asteroid.remove_ASTEROID){
				 asteroid_removal_service.add(asteroid);
			 }
			
		 } 
		 //Explosion updater
		 ArrayList<Boom> boom_removal_service = new ArrayList<Boom>();
		 for(Boom boom: booms){
			 boom.update();
			 if(booms.remove(boom_removal_service));
			 boom_removal_service.add(boom);
		 }
		 booms.removeAll(boom_removal_service);
		 
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
		if(isLeft()){
			x-=SPEED*Gdx.graphics.getDeltaTime();
			if(x < 0){
				x=0;
			}
			//Roll update if button is clicked for a short period of time/ just one press
			if(justLeft() && !justRight() && roll > 0){
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
			
		if(isRight()){
			x+=SPEED*Gdx.graphics.getDeltaTime();
			
			if(justRight() && !justLeft() && roll > 0){
				ROLL_TIMER = 0;
				roll++;
				if(roll>4){
					roll = 4;
				}
			}
			
			if(x > SpaceGame.screen_width-Ship_WIDTH){
				x = SpaceGame.screen_width-Ship_WIDTH;
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
				}     //cheeky boy is hree too
			}
		}
		//Updates collision after the player moves
		playerDetect.move(x, y);
		
		
		
		
		//Collision detection happens after all the objects have updated
		for(bullets bullet: bullet){
			for(Asteroid asteroid : asteroids){
				if(bullet.getCollisionDetection().collisionWITH(asteroid.getCollisionDetection())){//Checks for collision
					asteroid_removal_service.add(asteroid);
					bullet_removal_service.add(bullet);
					booms.add(new Boom(asteroid.getX(), asteroid.getY())) ;
					score+= 50;
				}
			}
		}
		
		
		
		
		for(Asteroid asteroid : asteroids){
			if(asteroid.getCollisionDetection().collisionWITH(playerDetect)){
				asteroid_removal_service.add(asteroid);
				health -= 0.1;
				//If the player has no health, go to game over screen
				if(health<=0){
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.dispose();
					spaceG.setScreen(new GameOverScreen(spaceG, score));
					return;
				}
			}
		}
		
		
		bullet.removeAll(bullet_removal_service);
		
		asteroids.removeAll(asteroid_removal_service);
		
		stateTime += delta;
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spaceG.batch.begin();  
		//get hacked kiddo
		if(SpaceGame.mobile_){
			//Left side 
			if(!removeControls){
			spaceG.batch.setColor(Color.CYAN);
			spaceG.batch.draw(controls, 0 , 0, SpaceGame.screen_width/2, SpaceGame.screen_height, 0, 0, SpaceGame.screen_width/2, SpaceGame.screen_height, false, false );
			//Right side
			spaceG.batch.setColor(Color.CHARTREUSE);
			spaceG.batch.draw(controls, SpaceGame.screen_width/2 , 0, SpaceGame.screen_width/2, SpaceGame.screen_height, 0, 0, SpaceGame.screen_width/2, SpaceGame.screen_height, true, false );
			
			spaceG.batch.setColor(Color.WHITE);
			}
		}
		spaceG.scroolBG.updateANDrender(Gdx.graphics.getDeltaTime(), spaceG.batch);
		GlyphLayout  scoreLayout = new GlyphLayout(fontSCORE, "" + score); 
		fontSCORE.draw(spaceG.batch, scoreLayout, (SpaceGame.screen_width/2 - scoreLayout.width)+ 30,SpaceGame.screen_height - scoreLayout.height );
		
		for(bullets bullet: bullet ){
			bullet.render(spaceG.batch);
		}
		for(Asteroid asteroid: asteroids ){
			asteroid.render(spaceG.batch);
		}
		for(Boom boom: booms){
			boom.render(spaceG.batch);
			boom.render(spaceG.batch);
			
		}
		//Render health bar
		if(health> 0.7f){
			spaceG.batch.setColor(Color.GREEN);
		}else if(health >0.3f){
			spaceG.batch.setColor(Color.ORANGE);
		}else if(health > 0.0f){
			spaceG.batch.setColor(Color.RED);
		}		
		spaceG.batch.draw(blank, 0, 0,SpaceGame.screen_width* health, 5);
		spaceG.batch.setColor(Color.WHITE);
		//spaceG.batch.draw(img, x, y);
		spaceG.batch.draw((TextureRegion)rolls[roll].getKeyFrame(stateTime, true), x, y, Ship_WIDTH, Ship_HEIGHT);
		
		
		spaceG.batch.end();
		
	}  //i am everywhere

	private boolean isRight(){
		return Gdx.input.isKeyPressed(Keys.RIGHT) || (Gdx.input.isTouched() && spaceG.cam.getGameInput().x >= SpaceGame.screen_width/2);//Checks if the screen is touched and it is on the right side of the screen 
	}
	private boolean isLeft(){
		return Gdx.input.isKeyPressed(Keys.LEFT)|| (Gdx.input.isTouched() && spaceG.cam.getGameInput().x < SpaceGame.screen_width/2);
	}
	private  boolean justRight(){
		return Gdx.input.isKeyJustPressed(Keys.RIGHT)|| (Gdx.input.isTouched() && spaceG.cam.getGameInput().x >= SpaceGame.screen_width/2);
	}
	private boolean justLeft(){
		return Gdx.input.isKeyJustPressed(Keys.LEFT)|| (Gdx.input.isTouched() && spaceG.cam.getGameInput().x < SpaceGame.screen_width/2);
	}
	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {   //your system is hacked
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		//last one
		
	}

}
