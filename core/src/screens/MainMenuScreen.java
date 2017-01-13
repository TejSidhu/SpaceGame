package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import me.kaptaan.monte_enterprise.SpaceGame;
import toolss.ScrollingBG;

public class MainMenuScreen implements Screen {

	Texture ActiveExitButton;
	Texture InactiveExitButton;
	Texture ActivePlayButton;
	Texture InactivePlayButton;
	Texture spaceBG;
	Texture logo;
	SpaceGame spaceG;
	
	private static final int Exit_Button_Width = 250;
	private static final int Exit_Button_Height = 120;
	private static final int Play_Button_Width = 300;
	private static final int Play_Button_Height = 120;
	private static final int Play_Y = 230;
	private static final int Exit_Y = 100;
	private static final int LOGO_width = 400;
	private static final int LOGO_HEIGHT = 250;
	private static final int LOGOy = 450;
	
	public MainMenuScreen(SpaceGame spaceG){
		this.spaceG = spaceG;
		spaceBG = new Texture("spaceBG.jpg");
		ActivePlayButton = new Texture("play_button_active.png");
		InactivePlayButton = new Texture("play_button_inactive.png");
		ActiveExitButton = new Texture("exit_button_active.png");
		InactiveExitButton = new Texture("exit_button_inactive.png");
		logo = new Texture("logo.png");
		spaceG.scroolBG.set_SpeedFixed(true);
		spaceG.scroolBG.set_Speeed(ScrollingBG.DEFAULT_SPEED);
	}
	
	@Override
	public void show() {
		
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//System.out.println(Gdx.graphics.getDeltaTime());
		spaceG.batch.begin();
		
		spaceG.scroolBG.updateANDrender(Gdx.graphics.getDeltaTime(), spaceG.batch);
		spaceG.batch.draw(logo, SpaceGame.screen_width/2 - LOGO_width/2, LOGOy, LOGO_width, LOGO_HEIGHT);
		float x = SpaceGame.screen_width/2 - Play_Y/2;
		//Play Button 
		if(spaceG.cam.getGameInput().x < x + Play_Button_Width && spaceG.cam.getGameInput().x > x && SpaceGame.screen_height - spaceG.cam.getGameInput().y < Play_Y + Play_Button_Height && SpaceGame.screen_height - spaceG.cam.getGameInput().y> Play_Y ){
			spaceG.batch.draw(ActivePlayButton, x,Play_Y, Play_Button_Width, Play_Button_Height);
			if(Gdx.input.justTouched()){
				spaceG.setScreen(new MainGameScreen(spaceG));
				this.dispose();
			}
		}else{
			spaceG.batch.draw(InactivePlayButton, x,Play_Y, Play_Button_Width, Play_Button_Height);
		}
		//Exit Button
		if(spaceG.cam.getGameInput().x < x + Exit_Button_Width && spaceG.cam.getGameInput().x > x && SpaceGame.screen_height - spaceG.cam.getGameInput().y < Exit_Y + Exit_Button_Height && SpaceGame.screen_height - spaceG.cam.getGameInput().y> Exit_Y ){
			spaceG.batch.draw(ActiveExitButton, x,Exit_Y, Exit_Button_Width, Exit_Button_Height);
			if(Gdx.input.isTouched()){
				Gdx.app.exit();
			}
		}else{
			spaceG.batch.draw(InactiveExitButton, x,Exit_Y, Exit_Button_Width, Exit_Button_Height);
		}
		
		
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
