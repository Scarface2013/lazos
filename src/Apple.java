import java.awt.*;
import javax.swing.JFrame;


public class Apple extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DisplayMode dm = new DisplayMode(800,600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		Apple a = new Apple();
		a.run(dm);
	}
	public void run(DisplayMode dm){
		setBackground(Color.PINK);
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.PLAIN, 24));
		
		Screen s = new Screen();
		try{
			s.setFullScreen(dm, this);
			try{
				Thread.sleep(5000);
				}catch(Exception ex){}
		}finally{
			s.restoreScreen();
		}
	}
	public void paint(Graphics g){
		if(g instanceof Graphics2D){
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		g.drawString("This is Gonna Be awesome", 200, 200);
	}
}
