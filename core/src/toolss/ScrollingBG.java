package toolss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.kaptaan.monte_enterprise.SpaceGame;

public class ScrollingBG {
Texture backGround;
float y1, y2; //This is because in the game there will be 2 images stacked on top of each other and the y co-ordinate is needed for both of them
int speed; //This is the speed for the scrolling, it is in pixels/second
int star_SPEED; //A certain speed that the stars should achieve
public static final int DEFAULT_SPEED = 80;
public static final int Acceleration = 50;
public static final int star_SPEED_ACCELERATION = 200;
float imageScalability;
boolean isSpeedFixed;


	public ScrollingBG(){
		backGround = new Texture(Gdx.files.internal("assets/stars_background.png"));
		y1 = 0;
		y2 = backGround.getHeight();
		speed = 0;
		star_SPEED = DEFAULT_SPEED;
		imageScalability =SpaceGame.screen_width/ backGround.getWidth();;
		isSpeedFixed = true;
	}
	
	public void updateANDrender(float delta, SpriteBatch batch){
		//Adjustment of speed to reach star_SPEED
		if(speed < star_SPEED){
			speed+= delta * star_SPEED_ACCELERATION;
			if(speed > star_SPEED){
				speed = star_SPEED;
			}else if(speed > star_SPEED){
				speed-= delta * star_SPEED_ACCELERATION;
				if(speed < star_SPEED){
					speed = star_SPEED;
					}
			}
		}
		
		if(!isSpeedFixed){
			speed += Acceleration * delta;
		}
		
		y1 -= delta * speed;
		y2 -= delta *speed;
		//if the background reaches the bottom of the game screen (& nt visible) then it puts it back on top of the image that it is under
		if(y1 + backGround.getHeight() * imageScalability<= 0){
			y1 = y2 + backGround.getHeight() * imageScalability;
		}
		if(y2 + backGround.getHeight()*imageScalability <= 0){
			y2 = y1 + backGround.getHeight() * imageScalability;
		}
		batch.draw(backGround, 0, y1, SpaceGame.screen_width, backGround.getHeight() * imageScalability);
		batch.draw(backGround, 0, y2, SpaceGame.screen_width, backGround.getHeight() * imageScalability);
	}

	public void set_Speeed(int star_SPEED){
		this.star_SPEED = star_SPEED;
	}
	public void set_SpeedFixed(boolean isSpeedFixed){
		this.isSpeedFixed = isSpeedFixed;
	}
	
	
}
