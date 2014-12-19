import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Apple extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DisplayMode dm = new DisplayMode(800,600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		Apple a = new Apple();
		a.run(dm);
	}
	private Screen s;
	private Image bg;
	private Animation a;
	
	//loads pics from computer into java
	public void loadPics(){
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
	public void run(DisplayMode dm){
		s = new Screen();
		try{
			s.setFullScreen(dm, new JFrame());
			loadPics();
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
			 
			 Graphics g = s.getFullScreenWindow().getGraphics();
			 draw(g);
			 g.dispose();
			 
			 try{
				 Thread.sleep(20);
			 }catch(Exception ex){}
		}
	}
	
	public void draw(Graphics g){
		g.drawImage(bg,0,0,null);
		g.drawImage(a.getImage(),600,600,null);
	}
}
