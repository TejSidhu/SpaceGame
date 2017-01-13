package me.kaptaan.monte_enterprise;


import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.MainMenuScreen;
import toolss.GameCamera;
import toolss.ScrollingBG;

public class SpaceGame extends Game {
	public GameCamera cam;
	public static boolean mobile_ = false; //To see if the game is on an android device

	public static final int screen_width = 480;
	public static final int screen_height = 720;

	public SpriteBatch batch;
	public ScrollingBG scroolBG;//Needs to be accessible by all the screen classes 
	
	
	@Override
	public void create () {
		cam = new GameCamera(screen_width, screen_height);
		batch = new SpriteBatch();
		
		if(Gdx.app.getType() == ApplicationType.Android || Gdx.app.getType() == ApplicationType.iOS ){
			mobile_ = true;
		}
		//mobile_ = true;//Only for testing purposes, MAKE SURE TO DELETE THIS LATER
		this.scroolBG = new ScrollingBG();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined());
		super.render();		
	}
	public void resize(){
		cam.update(screen_width, screen_height);
		super.resize(screen_width, screen_height);
	}
	


}
