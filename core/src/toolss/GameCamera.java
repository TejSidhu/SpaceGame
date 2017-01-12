package toolss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameCamera {
	private OrthographicCamera cam;//Controls what part of the game screen the user sees
	private StretchViewport viewport; //No matter what resolution the users diplay is, the game will strech according to its needs
	
	public GameCamera(int width, int height){
		cam = new OrthographicCamera();
		viewport = new StretchViewport(width, height, cam);
		viewport.apply();
		cam.position.set(width/2, height/2, 0);
		cam.update();
	}
	public Matrix4 combined(){
	return cam.combined;
	}
	public void update(int width, int height){
		viewport.update(width, height);
	}
	//batch.setProjectionMatrix(cam.combined());
	public Vector2 getGameInput(){//Converts screen coord into game world coords
		Vector3 inputONscreen = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
		Vector3 unprojected = cam.unproject(inputONscreen);//This converts the coords
		//When it is unprojected it like taking screen coords and turing it back into the game world coords; the cam.project does the opposite
		return new Vector2(unprojected.x, unprojected.y);
	}
}

	
