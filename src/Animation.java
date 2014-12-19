import java.awt.Image;
import java.util.ArrayList;

public class Animation {

	private ArrayList<oneScene> scenes;
	private int sceneIndex;
	private long movieTime;
	private long totalTime;
	
	//CONSTRUCTOR
	public Animation() {
		scenes = new ArrayList<oneScene>();
		totalTime = 0;
		start();
	}
	//add scene to arraylist  and sets time
	public synchronized void addScene(Image i, long t){
		totalTime+=t;
		scenes.add(new oneScene(i,totalTime));
	}
	
	//start anim from beginning
	public synchronized void start(){
		movieTime = 0;
		sceneIndex = 0;
	}
	
	//changes scene
	public synchronized void update(long timePassed){
		if(scenes.size()>1){
			movieTime+= timePassed;
			if(movieTime>=totalTime){
			movieTime = 0;
			sceneIndex = 0;
			}
			while(movieTime > getScene(sceneIndex).endTime){sceneIndex++;}
		}
	}
	//get animations current scene(aka image)
	public synchronized Image getImage(){
		if(scenes.size()==0){
			return null;
		}else{
			return getScene(sceneIndex).pic;
		}
		
	}
	private oneScene getScene(int x){
		return (oneScene)scenes.get(x);
	}
	//PRIVATE INNER CLASS
	private class oneScene{
		Image pic;
		long endTime;
		public oneScene(Image pic, long endTime){
			this.pic = pic;
			this.endTime = endTime;
		}
	}
}
