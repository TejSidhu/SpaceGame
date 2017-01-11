package me.kaptaan.monte_enterprise;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.MainGameScreen;
import screens.MainMenuScreen;

public class SpaceGame extends Game {

	
	public static final int screen_width = 480;
	public final static int screen_height = 720;

	public SpriteBatch batch;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {	
		super.render();
	}
	


}
