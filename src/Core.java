import java.awt.*;

import javax.swing.*;

public abstract class Core {
	private static DisplayMode modes [] = {
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
		new DisplayMode(800,600,16,0)
	};
	
	private boolean running;
	protected ScreenManager s;
	
	//Stop method
	public void stop(){
		running = false;
	}
	
	//call init & run method
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			s.restoreScreen();
		}
	}
	public void init(){
		s = new ScreenManager();
		DisplayMode dm = s.findFirstCompatibleMode(modes);
		s.setFullscreen(dm);
		
		Window w = s.getFullScreenWindow();
		w.setFont(new Font ("Times New Roman", Font.PLAIN,20));
		w.setBackground(Color.decode("#c4e3e8"));
		w.setForeground(Color.decode("#9c9a64"));
		
	}
	
	
}
