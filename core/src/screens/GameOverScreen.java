package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;

import me.kaptaan.monte_enterprise.SpaceGame;
import toolss.ScrollingBG;

public class GameOverScreen implements Screen {
	
	SpaceGame game; 
	int score, highscore;
	private static final int WIDTHbanner = 350;
	private static final int HEIGHTbanner = 100;
	
	Texture BannerGameOver;
	BitmapFont scoreFont;
	
	
	public GameOverScreen(SpaceGame game, int score){
		this.game = game;
		this.score = score;
		
		//Gets high score from a save file
		Preferences prefs = Gdx.app.getPreferences("SpaceGame"); //This is used to save app settings across runs, essentially like a save game 
		//Plus it kinda opens up the file ^
		this.highscore = prefs.getInteger("Highscore", 0);
		
		//This is pretty self explanatory 
		if(score> highscore){
			prefs.putInteger("Highscore", score);//Changes the highscore
			prefs.flush();//Saves to the file
		}
		//Loads textures + font
		BannerGameOver = new Texture("game_over.png");
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
		game.scroolBG.set_SpeedFixed(false);
		game.scroolBG.set_Speeed(ScrollingBG.DEFAULT_SPEED);
	}
	
	
	public void show() {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		
		
		//Determine if buttons are being pressed
		
		
		game.batch.begin();
		game.scroolBG.updateANDrender(delta, game.batch);
		game.batch.draw(BannerGameOver, SpaceGame.screen_width/2 - WIDTHbanner/2,SpaceGame.screen_height- HEIGHTbanner - 20, WIDTHbanner, HEIGHTbanner );
		GlyphLayout layout_SCORE = new GlyphLayout(scoreFont, "Score:\n " + score, Color.CHARTREUSE, 0, Align.center, false);
		GlyphLayout layout_highSCORE = new GlyphLayout(scoreFont, "High Score: \n " + score, Color.CHARTREUSE, 0, Align.left, false);
		
		GlyphLayout tryAgain = new GlyphLayout(scoreFont, "Try Again");
		GlyphLayout MainMenu = new GlyphLayout(scoreFont, "Main Menu");
		
		float tryAgainX = SpaceGame.screen_width/2 - tryAgain.width/2;
		float tryAgainY = SpaceGame.screen_height/2 - tryAgain.height/2;
		float MainMenuX = SpaceGame.screen_width/2 - tryAgain.width/2;
		float MainMenuY= SpaceGame.screen_height/2 - tryAgain.height/2 - tryAgain.height - 15;
		
		float touchX = game.cam.getGameInput().x,touchY =  SpaceGame.screen_height - game.cam.getGameInput().y;
		//Checks for hover over the text 
		if(touchX >= tryAgainX && touchX <= tryAgainX + tryAgain.width && touchY >= tryAgainY - tryAgain.height&& touchY <= tryAgainY ){
				tryAgain.setText(scoreFont, "Try Again", Color.FIREBRICK, 0, Align.left,false);		
		}
		if(touchX >= MainMenuX && touchX <= MainMenuX + MainMenu.width && touchY >= MainMenuY - MainMenu.height&& touchY <= MainMenuY ){
			MainMenu.setText(scoreFont, "Main Menu", Color.CHARTREUSE, 0, Align.left,false);		
	}
	
		
		if(Gdx.input.justTouched()){
			
			//Try again
			if(touchX > tryAgainX && touchX < tryAgainX + tryAgain.width && touchY > tryAgainY - tryAgain.height&& touchY < tryAgainY){
				this.dispose();
				game.batch.end();
				game.setScreen(new MainGameScreen(game));
				return;
			}
			//MainMenu
			if(touchX > MainMenuX && touchX < MainMenuX + MainMenu.width && touchY > MainMenuY - MainMenu.height&& touchY < MainMenuY){
				this.dispose();
				game.batch.end();
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
		scoreFont.draw(game.batch, tryAgain, tryAgainX, tryAgainY);
		scoreFont.draw(game.batch, MainMenu, MainMenuX, MainMenuY);
		scoreFont.draw(game.batch, layout_SCORE,  SpaceGame.screen_width/2 - layout_SCORE.width/2,  SpaceGame.screen_height - HEIGHTbanner - 15 * 2 );
		scoreFont.draw(game.batch, layout_highSCORE,  SpaceGame.screen_width/2 - layout_highSCORE.width/2,  SpaceGame.screen_height - HEIGHTbanner - layout_SCORE.height -  15 * 5 );
		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
