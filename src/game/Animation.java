package game;
public class Animation{

	public enum AnimationMode {LOOP, PLAYONCE;}

	public int ss;
	//X coordinate on the spritesheet grid
	public int frame;
	//Y coordinate on the spritesheet grid
	public int set;
	public int[] framesInSet;
	public double timeBetweenFrames;
	public AnimationMode mode;
	
	public double timeLeft;
	public boolean finished;
	
	public Animation(int ss, int frame, int set, double timeBetweenFrames, AnimationMode mode){
		this.ss = ss;
		this.frame = frame;
		this.set = set;
		this.timeBetweenFrames = timeBetweenFrames;
		this.mode = mode;
		
		timeLeft = timeBetweenFrames;
		finished = false;
	}
	
	public void setSet(int set){
		if(this.set != set){
			this.set = set;
			frame = 0;
		}
	}
	
	public void restart(){
		frame = 0;
	}
	
	public void update(double delta){
		timeLeft -= delta;
		while(timeLeft<=0){
			timeLeft+=timeBetweenFrames;
			frame++;
			int[] colsInRow = SpritesheetEnum.getSprite(ss).colsInRow;
			if(frame >= colsInRow[set]){
				finished = true;
				switch(mode){
					case LOOP:
						frame = frame % colsInRow[set];
						break;
					case PLAYONCE:
						frame = colsInRow[set]-1;
						break;
				}
			}
		}
	}
}
