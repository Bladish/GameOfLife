void gameSetup(){
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

