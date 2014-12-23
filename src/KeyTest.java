import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class KeyTest extends Core implements KeyListener {
	public static void main (String[] args) {
		new KeyTest().run();
	}
	
	private Sprite sprite;
	private Image bg;
	private Animation a;
	private Animation laserAnim;
	private Sprite laser;
	
	//init from superclass							MUST RUN FIRST
	public void init(){								//	  |
		super.init();								//	  |
		loadImages();								//	  |
		Window w = s.getFullScreenWindow();			//	  |
		w.setFocusTraversalKeysEnabled(false);		//	  |
		w.addKeyListener(this);						//	  |  
	}												//	  V
	
	public void loadImages(){
		bg = new ImageIcon(".\\Artwork\\Background1920.png").getImage();
		
		Image CircleR = new ImageIcon(".\\Artwork\\CircleRed.png").getImage();
		//Image CircleO = new ImageIcon(".\\Artwork\\CircleOrange.png").getImage();
		//Image CircleY = new ImageIcon(".\\Artwork\\CircleYellow.png").getImage();
		//Image CircleG = new ImageIcon(".\\Artwork\\CircleGreen.png").getImage();
		//Image CircleC = new ImageIcon(".\\Artwork\\CircleCyan.png").getImage();
		Image CircleB = new ImageIcon(".\\Artwork\\CircleBlue.png").getImage();
		//Image CircleV = new ImageIcon(".\\Artwork\\CircleViolet.png").getImage();
		//Image CircleP = new ImageIcon(".\\Artwork\\CirclePink.png").getImage();
		//Try to figure out how to init the other shit plz
		
		a = new Animation();
		a.addScene(CircleR,250); //Sprite MUST use type Animation  (Find workaround plz)
		
		laserAnim = new Animation();
		laserAnim.addScene(CircleB, 250);
		
		sprite = new Sprite(a);
		sprite.setVelX(0.1f);
		sprite.setVelY(0.1f);
	}
	
																//KEYLISTENERS
	
	//key press
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();
		
		//Checks when to escape
		if(keyCode == KeyEvent.VK_ESCAPE){
			stop();
		}
		//Checks when to move left/right
		if(keyCode == KeyEvent.VK_D){
			sprite.setVelX(sprite.getVelX() + .1f);
			e.consume();
		}
		if(keyCode == KeyEvent.VK_A){
			sprite.setVelX(sprite.getVelX() - .1f);
			e.consume();
		}
		
		//Checks when to move down/up
		if(keyCode == KeyEvent.VK_S){
			sprite.setVelY(sprite.getVelY() + .1f);
			e.consume();
		}
		if(keyCode == KeyEvent.VK_W){
			sprite.setVelY(sprite.getVelY() - .1f);
			e.consume();
		}
		
		if(keyCode == KeyEvent.VK_SPACE){
			laser = new Sprite(laserAnim);
			laser.setX(sprite.getX());
			laser.setY(sprite.getY());
			System.out.println(   ((float)MouseInfo.getPointerInfo().getLocation().getX()-sprite.getX())/1000   );
			System.out.println(   ((float)MouseInfo.getPointerInfo().getLocation().getY()-sprite.getY())/1000   );
			laser.setVelX(   ((float)MouseInfo.getPointerInfo().getLocation().getX()-sprite.getX())/1000   );
			laser.setVelY(   ((float)MouseInfo.getPointerInfo().getLocation().getY()-sprite.getY())/1000   );
			e.consume();
			
		}
		
	}
	//Key Released
	public void keyReleased(KeyEvent e){
		e.consume();
	}
	
	//useless but needed for implement
	public void keyTyped(KeyEvent e){e.consume();}
	
	
	
	
																//DRAW METHOD
	public synchronized void draw(Graphics2D g){
		//Window w = s.getFullScreenWindow();
		g.drawImage(bg, 0, 0, null);
		g.drawImage(sprite.getImage(),   Math.round(sprite.getX()),   Math.round(sprite.getY()),   null);
		
		try{
			g.drawImage(laser.getImage(),   Math.round(laser.getX()),   Math.round(laser.getY()),   null);
		}catch(Exception ex){}
		
		
	}
	
																//UPDATE METHOD
	public void update(long timePassed){
		//Checks left/right boundaries for you
		if(sprite.getX() <= 0){sprite.setVelX( Math.abs( sprite.getVelX() ) );
		}else if(sprite.getX() + sprite.getWidth() >= s.getWidth()){sprite.setVelX( -Math.abs( sprite.getVelX() ) );}
		//Checks top/bottom boundaries
		if(sprite.getY() <= 0){sprite.setVelY( Math.abs( sprite.getVelY() ) );
		}else if(sprite.getY() + sprite.getHeight() >= s.getHeight()){sprite.setVelY( -Math.abs( sprite.getVelY() ) );}
		
		//Updates sprite
		sprite.update(timePassed);
		
		
		try{
		//Checks left/right boundaries for laser
		if(laser.getX() <= 0){laser.setVelX( Math.abs( laser.getVelX() ) );
		}else if(laser.getX() + laser.getWidth() >= s.getWidth()){laser.setVelX( -Math.abs( laser.getVelX() ) );}
		//Checks top/bottom boundaries
		if(laser.getY() <= 0){laser.setVelY( Math.abs( laser.getVelY() ) );
		}else if(laser.getY() + laser.getHeight() >= s.getHeight()){laser.setVelY( -Math.abs( laser.getVelY() ) );}
		
		//Updates laser
		laser.update(timePassed);
		}catch(Exception e){}
	}
}
