package me.kaptaan.monte_enterprise;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BackgroundMusic {
	Music music;
	public BackgroundMusic(){
		music = Gdx.audio.newMusic(Gdx.files.internal("Space Music - Orion.mp3"));
		
		music.play();
		music.setLooping(true);
	}
}
