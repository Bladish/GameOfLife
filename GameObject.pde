public class GameObject {
	float x, y; 	
	float size;		
	boolean alive = false;
	boolean nextGenAlive = false;
	boolean startDead = false;

	public GameObject (float x, float y, float size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	
	void draw(){
		if(alive){
			fill(0, 255, 0);
			ellipse(x, y, size, size);
			startDead = false;		
		}
		if(!alive){
			fill(255, 0, 0);
			ellipse(x, y, size, size);	
		}
		if(startDead){
			fill(255);
			ellipse(x, y, size, size);
		}		
	} 
}