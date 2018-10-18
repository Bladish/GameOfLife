import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class GameOfLife extends PApplet {

GameObject cells[][];		 
float cellSize = 10;		
int numberOfColums;
int numberOfRows;
int fillPercentage = 15;



public void setup(){
			
	ellipseMode(LEFT);	
	frameRate(30);		
	gameSetup();
}


public void draw() {
	drawBackGround();
	calculateNextGen();
	drawGame();
}


public void drawBackGround(){
	background(255);
}


public void calculateNextGen(){
	boolean currentState = false;
	for (int y = 0; y < numberOfRows; y++){
		for (int x = 0; x < numberOfColums; x++){
				int neighbours = calculateNeighbours(x, y);
				cells[x][y].nextGenAlive = checkAgainstRules(neighbours, cells[x][y].alive);						
		}
	}
}


public void drawGame(){
	for (int y = 0; y < numberOfRows; ++y) {
		for (int x = 0; x < numberOfColums; ++x) {
			cells[x][y].alive = cells[x][y].nextGenAlive;
			cells[x][y].draw();
		}
	}
}


public boolean checkAgainstRules(int neighbours, boolean currentState){
	if(neighbours == 3) return true;
	else if(neighbours < 2 || neighbours > 3) {
		return false;		
	}
	return currentState;
}


public int calculateNeighbours(int x, int y){
	int neighbours = 0;
	for (int deltaX = -1; deltaX <= 1; deltaX++){
		for (int deltaY = -1; deltaY <= 1; deltaY++){
			if( deltaX == 0 && deltaY == 0){
				continue;
			}

			try{
				if (cells[x + deltaX][y + deltaY].alive)
				{
					neighbours++;
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{

			}
		}
	}
	return neighbours;
}




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

	
	public void draw(){
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
public void gameSetup(){
	numberOfColums = (int)Math.floor(width/cellSize);
	numberOfRows = (int)Math.floor(height/cellSize);
 
	cells = new GameObject[numberOfColums][numberOfRows];

	for (int y = 0; y < numberOfRows; y++) {
		
		for (int x = 0; x < numberOfColums; x++) {
			cells[x][y] = new GameObject(x * cellSize, y * cellSize, cellSize);
			
			if (random(0, 100) < fillPercentage) {
				cells[x][y].alive = true;
			}
			else cells[x][y].startDead = true;
		}
	}
}

  public void settings() { 	size(512, 512); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "GameOfLife" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
