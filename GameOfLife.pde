GameObject cells[][];		 
float cellSize = 10;		
int numberOfColums;
int numberOfRows;
int fillPercentage = 15;



void setup(){
	size(512, 512);		
	ellipseMode(LEFT);	
	frameRate(30);		
	gameSetup();
}


void draw() {
	drawBackGround();
	calculateNextGen();
	drawGame();
}


void drawBackGround(){
	background(255);
}


void calculateNextGen(){
	boolean currentState = false;
	for (int y = 0; y < numberOfRows; y++){
		for (int x = 0; x < numberOfColums; x++){
				int neighbours = calculateNeighbours(x, y);
				cells[x][y].nextGenAlive = checkAgainstRules(neighbours, cells[x][y].alive);						
		}
	}
}


void drawGame(){
	for (int y = 0; y < numberOfRows; ++y) {
		for (int x = 0; x < numberOfColums; ++x) {
			cells[x][y].alive = cells[x][y].nextGenAlive;
			cells[x][y].draw();
		}
	}
}


boolean checkAgainstRules(int neighbours, boolean currentState){
	if(neighbours == 3) return true;
	else if(neighbours < 2 || neighbours > 3) {
		return false;		
	}
	return currentState;
}


int calculateNeighbours(int x, int y){
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




