import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
	
	private Animation a;
	private float posX;
	private float posY;
	private float velX;
	private float velY;
	private KeyTest key;
	
	//CONSTRUCTOR
	public Sprite(Animation a){
		this.a = a;
	}
	//Change position
	public void update(long timePassed, float speedMod){
		try{
			posX += velX * timePassed * speedMod;
			posY += velY * timePassed * speedMod;
			a.update(timePassed);
		}catch(Exception ex){}
	}
	
	//get x,y positions of sprite
	public float getX(){return posX;}
	public float getY(){return posY;}
	
	//set x,y positions of sprite
	public void setX(float posX){this.posX=posX;}
	public void setY(float posY){this.posY=posY;}
	
	//get Width,Height of image
	public int getWidth(){return a.getImage().getWidth(null);}
	public int getHeight(){return a.getImage().getHeight(null);}
	
	//get velocities of sprite
	public float getVelX(){return velX;}
	public float getVelY(){return velY;}
	
	//set velocities of sprite
	public void setVelX(float velX){this.velX=velX;}
	public void setVelY(float velY){this.velY=velY;}
	
	//Used in collision detection collisions 
	public Rectangle getBounds(){
		return new Rectangle((int)posX,(int)posY,getWidth(),getHeight());
	}
	
	//get sprite's image
	public Image getImage(){return a.getImage();}
}
