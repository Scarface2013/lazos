import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class KeyTest extends Core implements KeyListener {
	public static void main (String[] args) {
		new KeyTest().run();
	}

	private String mess = "";  //TO_DELETE once finished adaptation
	
	private Sprite sprite;
	private Image bg;
	private Animation a;

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
		Image CircleO = new ImageIcon(".\\Artwork\\CircleOrange.png").getImage();
		Image CircleY = new ImageIcon(".\\Artwork\\CircleYellow.png").getImage();
		Image CircleG = new ImageIcon(".\\Artwork\\CircleGreen.png").getImage();
		Image CircleC = new ImageIcon(".\\Artwork\\CircleCyan.png").getImage();
		Image CircleB = new ImageIcon(".\\Artwork\\CircleBlue.png").getImage();
		Image CircleV = new ImageIcon(".\\Artwork\\CircleViolet.png").getImage();
		Image CircleP = new ImageIcon(".\\Artwork\\CirclePink.png").getImage();
		//Try to figure out how to init the other shit plz
		
		a = new Animation();
		
		a.addScene(CircleR,250); //Sprite MUST use type Animation  (Find workaround plz)
		
		sprite = new Sprite(a);
		sprite.setVelX(0.3f);
		sprite.setVelY(0.6f);
	}
	
																//KEYLISTENERS
	
	//key press
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();
		
		//Checks when to escape
		if(keyCode == KeyEvent.VK_ESCAPE){
			stop();
		}else{
			mess = "Pressed : " + KeyEvent.getKeyText(keyCode);
			e.consume();
		}
		
		//Checks when to move right
		if(keyCode == KeyEvent.VK_RIGHT){
			
		}
		
	}
	//Key Released
	public void keyReleased(KeyEvent e){
		int keyCode = e.getKeyCode();
		mess = "Released : " + KeyEvent.getKeyText(keyCode);
		e.consume();
	}
	
	//useless but needed for implement
	public void keyTyped(KeyEvent e){e.consume();}
	
	
																//DRAW METHOD
	public synchronized void draw(Graphics2D g){
		//Window w = s.getFullScreenWindow();
		g.drawImage(bg, 0, 0, null);
		g.drawImage(sprite.getImage(), 0, 0, null);
		
	}
	
																//UPDATE METHOD
	public void update(long timePassed){
		//Checks left/right boundaries
		if(sprite.getX() <= 0){sprite.setVelX( Math.abs( sprite.getVelX() ) );
		}else if(sprite.getX() + sprite.getWidth() >= s.getWidth()){sprite.setVelX( -Math.abs( sprite.getVelX() ) );}
		//Checks top/bottom boundaries
		if(sprite.getY() <= 0){sprite.setVelY( Math.abs( sprite.getVelY() ) );
		}else if(sprite.getY() + sprite.getHeight() >= s.getHeight()){sprite.setVelY( -Math.abs( sprite.getVelY() ) );}
		
		//Updates sprite
		sprite.update(timePassed);
	}
}
