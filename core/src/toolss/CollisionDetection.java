package toolss;

public class CollisionDetection {

	float x, y;
	int width, height;
	
	public CollisionDetection(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width; 
		this.height = height;
		
	}
	public void move(float x, float y){
		this.x = x;
		this.y = y;
	}
	public boolean collisionWITH(CollisionDetection detect){
		return x < detect.x + detect.width && y < detect.y + detect.height && x + width > detect.x && y + height > detect.y;
	}
}
