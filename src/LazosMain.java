import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class LazosMain extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LazosMain lazos = new LazosMain();
		lazos.run();
	}
	
	private ScreenManager s;
	private Image bg;
	private Animation a;
	private static final DisplayMode modes1[] = { 
		new DisplayMode(1920,1080,32,0),
		new DisplayMode(1920,1080,24,0),
		new DisplayMode(1920,1080,16,0),
		new DisplayMode(1600,900,32,0),
		new DisplayMode(1600,900,24,0),
		new DisplayMode(1600,900,16,0),
		new DisplayMode(1366,768,32,0),
		new DisplayMode(1366,768,24,0),
		new DisplayMode(1366,768,16,0),
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		};
	
	//loads pics from computer into java
	public void loadImages(){
		bg = new ImageIcon("C:\\Users\\Travis\\Desktop\\Programming\\eclipse\\Workstation\\GameOne\\Artwork\\Background1920.jpg").getImage();
		Image CircleR = new ImageIcon("C:\\Users\\Travis\\Desktop\\Programming\\eclipse\\Workstation\\GameOne\\Artwork\\CircleRed.png").getImage();
		Image CircleO = new ImageIcon("C:\\Users\\Travis\\Desktop\\Programming\\eclipse\\Workstation\\GameOne\\Artwork\\CircleOrange.png").getImage();
		Image CircleY = new ImageIcon("C:\\Users\\Travis\\Desktop\\Programming\\eclipse\\Workstation\\GameOne\\Artwork\\CircleYellow.png").getImage();
		Image CircleG = new ImageIcon("C:\\Users\\Travis\\Desktop\\Programming\\eclipse\\Workstation\\GameOne\\Artwork\\CircleGreen.png").getImage();
		Image CircleC = new ImageIcon("C:\\Users\\Travis\\Desktop\\Programming\\eclipse\\Workstation\\GameOne\\Artwork\\CircleCyan.png").getImage();
		Image CircleB = new ImageIcon("C:\\Users\\Travis\\Desktop\\Programming\\eclipse\\Workstation\\GameOne\\Artwork\\CircleBlue.png").getImage();
		Image CircleV = new ImageIcon("C:\\Users\\Travis\\Desktop\\Programming\\eclipse\\Workstation\\GameOne\\Artwork\\CircleViolet.png").getImage();
		Image CircleP = new ImageIcon("C:\\Users\\Travis\\Desktop\\Programming\\eclipse\\Workstation\\GameOne\\Artwork\\CirclePink.png").getImage();
		a = new Animation();
		a.addScene(CircleR,250);
		a.addScene(CircleO,250);
		a.addScene(CircleY,250);
		a.addScene(CircleG,250);
		a.addScene(CircleC,250);
		a.addScene(CircleB,250);
		a.addScene(CircleV,250);
		a.addScene(CircleP,250);
	}
	
	//main engine
	public void run(){
		s = new ScreenManager();
		try{
			DisplayMode dm = s.findFirstCompatibleMode(modes1);
			s.setFullscreen(dm);
			loadImages();
			movieLoop();
		}finally{
			s.restoreScreen();
		}
	}
	
	//main movie loop
	public void movieLoop(){
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		
		while(cumTime - startingTime < 5000){
			 long timePassed = System.currentTimeMillis() - cumTime;
			 cumTime += timePassed;
			 a.update(timePassed);
			 
			 //draws screen
			 Graphics2D g = s.getGraphics();
			 draw(g);
			 g.dispose();
			 s.update();
			 
			 try{
				 Thread.sleep(20);
			 }catch(Exception ex){}
		}
	}
	//draws graphics
	public void draw(Graphics g){
		g.drawImage(bg,0,0,null);
		g.drawImage(a.getImage(),600,600,null);
	}
}
