package me.kaptaan.monte_enterprise.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.kaptaan.monte_enterprise.SpaceGame;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = SpaceGame.screen_width;
		config.height = SpaceGame.screen_height;
		config.resizable = false;
		config.title = ("Space Destruction 0.0.1 alpha");
		new LwjglApplication(new SpaceGame(), config);
	}
}
