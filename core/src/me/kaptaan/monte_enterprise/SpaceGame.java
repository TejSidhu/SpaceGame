package me.kaptaan.monte_enterprise;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import screens.MainMenuScreen;
import toolss.ScrollingBG;

public class SpaceGame extends Game {

	
	public static final int screen_width = 480;
	public final static int screen_height = 720;

	public SpriteBatch batch;
	public ScrollingBG scroolBG;//Needs to be accessible by all the screen classes 
	private OrthographicCamera cam;//Controls what part of the game screen the user sees
	private FitViewport viewport; //No matter what resolution the users diplay is, the game will strech according to its needs
	
	@Override
	public void create () {
		cam = new OrthographicCamera();
		viewport = new FitViewport(screen_width, screen_height, cam);
		batch = new SpriteBatch();
		viewport.apply();
		cam.position.scl(screen_width/2, screen_height/2, 0);
		cam.update();
		this.scroolBG = new ScrollingBG();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(cam.invProjectionView);
		super.render();
		
	}
	public void resize(){
		viewport.update(screen_width, screen_height);
		this.scroolBG.backGround_RESIZE(screen_width,screen_height);
		super.resize(screen_width, screen_height);
	}
	


}
