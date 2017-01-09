package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import me.kaptaan.monte_enterprise.SpaceGame;

public class MainGameScreen implements Screen{
	
	Texture img;
	float x;
	float y;
	public static final float SPEED = 130;
	SpaceGame spaceG;
	
	public MainGameScreen(SpaceGame spaceG){
		this.spaceG = spaceG;
	}
	
	@Override
	public void show() {
		img = new Texture("badlogic.jpg");
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.println(Gdx.graphics.getDeltaTime());
		
		if(Gdx.input.isKeyPressed(Keys.UP)){
			y+=SPEED*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			y-=SPEED*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			x-=SPEED*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			x+=SPEED*Gdx.graphics.getDeltaTime();
		}
		spaceG.batch.begin();
		spaceG.batch.draw(img, x, y);
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
