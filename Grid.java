//https://youtu.be/qmyYOe74OHc
//Carlos Medina CMP 168 Fall 2022
// Prof Gitlin, Prof Sofianos

import java.util.Arrays;
import java.util.Random;

public class Grid {

	private boolean [][] bombGrid;
	private int [][] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	public Grid() {
		
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		bombGrid = new boolean[numRows][numColumns];
		countGrid = new int[numRows][numColumns];
		createBombGrid();
		createCountGrid();
		
	}
	
	public Grid(int rows, int columns) {
		
		this.numRows = rows;
		this.numColumns= columns;
		numBombs = 25;
		bombGrid = new boolean[rows][columns];
		countGrid = new int[rows][columns];
		createBombGrid();
		createCountGrid();
		
	}
	
	public Grid(int rows, int columns, int numBombs) {
		
		this.numRows = rows;
		this.numColumns= columns;
		this.numBombs = numBombs;
		bombGrid = new boolean[rows][columns];
		countGrid = new int[rows][columns];
		createBombGrid();
		createCountGrid();
		
	}
	
	public int getNumRows() {
		
		return numRows;
	}
	
	public int getNumColumns() {
		
		return numColumns;
	}
	
	public int getNumBombs() {
		
		return numBombs;
	}
	
	public boolean [][] getBombGrid(){
		
		boolean[][] newBombGrid = new boolean[numRows][numColumns];
		
		for(int i = 0;i < bombGrid.length;i++){
			for(int j = 0;j< bombGrid[i].length;j++) {
				newBombGrid[i][j] = bombGrid[i][j];
			}
		}		
		
		return newBombGrid;
	}
	
	public int [][] getCountGrid(){
		
		int [][] newCountGrid = new int[numRows][numColumns];
		
		for(int i = 0;i < countGrid.length;i++){
			for(int j = 0;j< countGrid[i].length;j++) {
				newCountGrid[i][j] = countGrid[i][j];
			}
		}		
		return newCountGrid;
	}
	
	public boolean isBombAtLocation(int row, int column) {
		
		return bombGrid[row][column];
	}
	
	public int getCountAtLocation(int row, int column) {
		
		return countGrid[row][column];
	}
	//helper method to add a bomb to grid
	 private void addBomb (boolean [][] grid) {    

		 Random rand = new Random ();
		 int rowI;
		 int columnI;

         while (true) {
        	 rowI = rand.nextInt(grid.length);
        	 columnI = rand.nextInt(grid[0].length);
        	 
        	 while (grid[rowI][columnI]) {
        		 rowI = rand.nextInt(grid.length);
        		 columnI = rand.nextInt(grid[0].length);
        		 }
        	 grid[rowI][columnI] = true;
        	 break;
         } 
         
	 }
	 //counts bombs within rows and columns and adds count to cell
	 private int cellCount (boolean[][] bombGrid, int r, int c) {

         int count = 0;
         
         for (int i = r-1; i <= r+1; i++) {
        	 for (int j = c-1; j <= c+1; j++) {
        		 try {
        			 if (bombGrid[i][j]){
        				 ++count;
        			 }
        		 } catch (Exception e) {}
        	 }        	 
         }
         return count;         
	 }

	 private void createBombGrid() {
	
		for(int i = 0;i < numRows; i++) {
			for( int j= 0;j<numColumns;j++) {
				bombGrid[i][j] = false;
			}
		}
		for (int i = 0; i < this.numBombs; ++i) {
			addBomb(bombGrid);
			}
	}
	 
	
	private void createCountGrid() {
		
	    for (int i = 0; i < numRows; i++) {
	    	for (int j = 0; j < numColumns; j++) {
	    		countGrid[i][j] = cellCount(bombGrid, i, j);
            }	    	
	    }
	}
	

	public void printGrid(boolean[][] bombsG, int[][] numsG) {
		
		for (int i = 0; i < bombsG.length; ++i) {
        	System.out.println(Arrays.toString(bombsG[i]));
        }
		
		System.out.println("");
		
		for (int i = 0; i < numsG.length; ++i) {
        	System.out.println(Arrays.toString(numsG[i]));
        }
		
		
	}
	
}
