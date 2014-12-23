import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
//import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;


public class ScreenManager {
	
	private GraphicsDevice vc;
	
	
	//gives vc access to screen
	public ScreenManager(){
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = e.getDefaultScreenDevice();
	}
	//get all compatible display modes
	public DisplayMode[] getCompatibleDisplayModes(){
		return vc.getDisplayModes();
	}
	
	//compares DM passed to VC to see if match
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]){
		DisplayMode goodModes[] = vc.getDisplayModes();
		for(int x = 0; x<modes.length;x++){
			for(int y = 0;y<goodModes.length;y++){
				if(displayModesMatch(modes[x],goodModes[y])){return modes[x];}
			}
		}
		return null;
	}
	
	
	//get current DM
	public DisplayMode getCurrentDisplayMode(){return vc.getDisplayMode();}
	
	
	//Checks if 2 modes match each other
	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2){
		if(m1.getWidth()!=m2.getWidth() || m1.getHeight() != m2.getHeight()){return false;} //Resolution testing
		if(m1.getBitDepth()!=DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth()!=DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth()!=m2.getBitDepth()){return false;} //Bit depth testing
		if(m1.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate()!=m2.getRefreshRate()){return false;} //Refresh rate testing
		return true;
	}
	//make frame full screen
	public void setFullscreen(DisplayMode dm){
		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setIgnoreRepaint(true);
		f.setResizable(false);
		vc.setFullScreenWindow(f);
		
		if(dm != null && vc.isDisplayChangeSupported()){
			try{
				vc.setDisplayMode(dm);
			}catch(Exception ex){}
		}
		f.createBufferStrategy(2);
	}
	
	//set Graphics object == this
	public Graphics2D getGraphics(){
		Window w = vc.getFullScreenWindow();
		if(w!= null){
			BufferStrategy s = w.getBufferStrategy();
			return(Graphics2D)s.getDrawGraphics();
		}else{return null;}
	}
	//Updates display
	public void update(){
		Window w = vc.getFullScreenWindow();
		if(w != null){
			BufferStrategy s = w.getBufferStrategy();
			if(!s.contentsLost()){s.show();}
		}
	}
	
	//Returns fullscreen window
	public Window getFullScreenWindow(){
		return vc.getFullScreenWindow();
	}
	
	//Returns int width OR 0 if null
	public int getWidth(){ 
		Window w = vc.getFullScreenWindow();
		if(w !=null){
			return w.getWidth();
		}else{return 0;}
	}
	//Returns int height OR 0 if null
	public int getHeight(){
		Window w = vc.getFullScreenWindow();
		if(w !=null){
			return w.getHeight();
		}else{return 0;}
	}
	
	//get out of fullscreen
	public void restoreScreen(){
		Window w = vc.getFullScreenWindow();
		if(w!=null){w.dispose();}
		vc.setFullScreenWindow(null);
	}
	
	
	public BufferedImage createCompatibleImage(int w, int h, int t){  //Width(w), height(h), and transparency(t)
		Window win = vc.getFullScreenWindow();
		if(win!=null){
			GraphicsConfiguration gc = win.getGraphicsConfiguration();
			return gc.createCompatibleImage(w,h,t);
		}
		return null;
	}
}
