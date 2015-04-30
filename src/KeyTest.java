import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class KeyTest extends Core implements KeyListener {
	public static void main (String[] args) {
		new KeyTest().run();
	}
	
	private Sprite sprite;
	private Sprite spriteTwo;
	private Image bg;
	private Animation a;
	private Animation a2;
	private Animation laserAnim;
	private int laserCount;
	private ArrayList<Sprite> lasers = new ArrayList<Sprite>();
	private ArrayList<Long> lasers2 = new ArrayList<Long>(); 		//Contains the startTime of newly created lasers for delayed collisons
	private float speedMod;
	
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
		//Image CircleY = new ImageIcon(".\\Artwork\\CircleYellow.png").getImage();
		//Image CircleG = new ImageIcon(".\\Artwork\\CircleGreen.png").getImage();
		//Image CircleC = new ImageIcon(".\\Artwork\\CircleCyan.png").getImage();
		Image CircleB = new ImageIcon(".\\Artwork\\CircleBlue.png").getImage();
		//Image CircleV = new ImageIcon(".\\Artwork\\CircleViolet.png").getImage();
		//Image CircleP = new ImageIcon(".\\Artwork\\CirclePink.png").getImage();
		//Try to figure out how to init the other shit plz
		
		a = new Animation();
		a.addScene(CircleR,250); //Sprite MUST use type Animation  (Find workaround plz)
		
		a2 = new Animation();
		a2.addScene(CircleO, 250);
		
		
		laserAnim = new Animation();
		laserAnim.addScene(CircleB, 250);
		laserCount = 0;
		speedMod = 1f;
		
		sprite = new Sprite(a);
		sprite.setVelX(0.1f);
		sprite.setVelY(0.1f);
		
		spriteTwo = new Sprite(a2);
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
		
											//SPRITE ONE
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
		
											//SPRITE TWO
		//Checks when to move left/right 
		if(keyCode == KeyEvent.VK_SEMICOLON){
			spriteTwo.setVelX(spriteTwo.getVelX() + .1f);
			e.consume();
				}
		if(keyCode == KeyEvent.VK_K){
			spriteTwo.setVelX(spriteTwo.getVelX() - .1f);
			e.consume();
		}
				
		//Checks when to move down/up
		if(keyCode == KeyEvent.VK_L){
			spriteTwo.setVelY(spriteTwo.getVelY() + .1f);
			e.consume();
		}
		if(keyCode == KeyEvent.VK_O){
			spriteTwo.setVelY(spriteTwo.getVelY() - .1f);
			e.consume();
		}	
		
		
		//SpeedMod set
		
		
		//WARNING ------ Low speedmodes break the delay on collision detection so you may shoot yourself  (((need to fix w/ inverse math but shit went awry)))
		if(keyCode == KeyEvent.VK_1){
			speedMod = .01f;
			e.consume();
		}
		if(keyCode == KeyEvent.VK_2){
			speedMod = .05f;
			e.consume();
		}
		if(keyCode == KeyEvent.VK_3){
			speedMod = .1f;
			e.consume();
		}
		if(keyCode == KeyEvent.VK_4){
			speedMod = .5f;
			e.consume();
		}
		if(keyCode == KeyEvent.VK_5){
			speedMod = 1f;
			e.consume();
		}
		if(keyCode == KeyEvent.VK_6){
			speedMod = 2f;
			e.consume();
		}
		if(keyCode == KeyEvent.VK_7){
			speedMod = 5f;
			e.consume();
		}
		if(keyCode == KeyEvent.VK_8){
			speedMod = 10f;
			e.consume();
		}
		if(keyCode == KeyEvent.VK_9){
			speedMod = 20f;
			e.consume();
		}
		
		//RESTART
		if(keyCode == KeyEvent.VK_R){
			lasers2.clear();
			lasers.clear();
			laserCount = 0;
			e.consume();
		}
		
														//Lasers
		if(keyCode == KeyEvent.VK_SPACE){
			lasers.add(new Sprite(laserAnim));
			lasers2.add(cumTime);
			lasers.get(laserCount).setVelX(   ((float)MouseInfo.getPointerInfo().getLocation().getX()-sprite.getX())/1000   );
			lasers.get(laserCount).setVelY(   ((float)MouseInfo.getPointerInfo().getLocation().getY()-sprite.getY())/1000   );
			
			lasers.get(laserCount).setX(sprite.getX()  );
			lasers.get(laserCount).setY(sprite.getY()  );
			laserCount++;
			e.consume();
		}
		if(keyCode == KeyEvent.VK_SHIFT){
			lasers.add(new Sprite(laserAnim));
			lasers2.add(cumTime);
			lasers.get(laserCount).setVelX(   ((float)MouseInfo.getPointerInfo().getLocation().getX()-spriteTwo.getX())/1000   );
			lasers.get(laserCount).setVelY(   ((float)MouseInfo.getPointerInfo().getLocation().getY()-spriteTwo.getY())/1000   );
			
			lasers.get(laserCount).setX(spriteTwo.getX()  );
			lasers.get(laserCount).setY(spriteTwo.getY()  );
			laserCount++;
			e.consume();
		}
		
		
		
		
		
	}
	//Key Released
	public void keyReleased(KeyEvent e){
		int keyCode = e.getKeyCode();
		
		
	}
	
	//useless but needed for implement
	public void keyTyped(KeyEvent e){e.consume();}
	
	
	public void checkCollisions(){
		super.checkCollisions();
		for(int x = 0; x < lasers.size(); x++){
			if(spriteTwo.getBounds().intersects(lasers.get(x).getBounds()) && lasers2.get(x)+1000<cumTime){
				
				lasers2.clear();
				lasers.clear();
				laserCount = 0;
			
			}
			
		}
		for(int x = 0; x < lasers.size(); x++){
		if(sprite.getBounds().intersects(lasers.get(x).getBounds()) && lasers2.get(x)+1000<cumTime){
			
			lasers2.clear();
			lasers.clear();
			laserCount = 0;
			}
		}		
	}
	
																//DRAW METHOD
	public synchronized void draw(Graphics2D g){
		//Window w = s.getFullScreenWindow();
		g.drawImage(bg, 0, 0, null);
		g.drawImage(sprite.getImage(),   Math.round(sprite.getX()),   Math.round(sprite.getY()),   null);
		g.drawImage(spriteTwo.getImage(), Math.round(spriteTwo.getX()), Math.round(spriteTwo.getY()), null);
		
		//Draws all lasers
		for(int x = 0; x < lasers.size(); x++){
			try{
				g.drawImage(lasers.get(x).getImage(),   Math.round(lasers.get(x).getX()),   Math.round(lasers.get(x).getY()),   null);
			}catch(Exception ex){}
		}
		
		
		
	}
	
																//UPDATE METHOD
	public void update(long timePassed){
		
		//SPRITE ONE
		
		//Checks left/right boundaries for you
		if(sprite.getX() <= 0){sprite.setVelX( Math.abs( sprite.getVelX() ) );
		}else if(sprite.getX() + sprite.getWidth() >= s.getWidth()){sprite.setVelX( -Math.abs( sprite.getVelX() ) );}
		//Checks top/bottom boundaries
		if(sprite.getY() <= 0){sprite.setVelY( Math.abs( sprite.getVelY() ) );
		}else if(sprite.getY() + sprite.getHeight() >= s.getHeight()){sprite.setVelY( -Math.abs( sprite.getVelY() ) );}
		
		//SPRITE TWO
		
		//Checks left/right boundaries for you
		if(spriteTwo.getX() <= 0){spriteTwo.setVelX( Math.abs( spriteTwo.getVelX() ) );
		}else if(spriteTwo.getX() + spriteTwo.getWidth() >= s.getWidth()){spriteTwo.setVelX( -Math.abs( spriteTwo.getVelX() ) );}
		//Checks top/bottom boundaries
		if(spriteTwo.getY() <= 0){spriteTwo.setVelY( Math.abs( spriteTwo.getVelY() ) );
		}else if(spriteTwo.getY() + spriteTwo.getHeight() >= s.getHeight()){spriteTwo.setVelY( -Math.abs( spriteTwo.getVelY() ) );}
		
		//Updates sprite
		sprite.update(timePassed, speedMod);
		spriteTwo.update(timePassed, speedMod);
		
		
		//updates ALL lasers
		for(int x = 0; x < lasers.size(); x++){
			try{
				//Checks left/right boundaries for laser
				if(lasers.get(x).getX() <= 0){lasers.get(x).setVelX( Math.abs( lasers.get(x).getVelX() ) );
				}else if(lasers.get(x).getX() + lasers.get(x).getWidth() >= s.getWidth()){lasers.get(x).setVelX( -Math.abs( lasers.get(x).getVelX() ) );}
				//Checks top/bottom boundaries
				if(lasers.get(x).getY() <= 0){lasers.get(x).setVelY( Math.abs( lasers.get(x).getVelY() ) );
				}else if(lasers.get(x).getY() + lasers.get(x).getHeight() >= s.getHeight()){lasers.get(x).setVelY( -Math.abs( lasers.get(x).getVelY() ) );}
				
				//Updates laser
				lasers.get(x).update(timePassed, speedMod);
				}catch(Exception e){}
		}
		
	}
}
