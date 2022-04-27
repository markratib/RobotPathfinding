package com.revature.models;


public class Board 
{
	private static final int columns = 5;
	private static final int rows = 5;
	private static final char obstacle = 127;
	private static final char finish = 216;
	private static char[][] grid;
	private static Board board = null;
	
	private Board()
	{
		
	}
	
	private static void initializeBoard()
	{
		//set all vertices to spaces
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<columns; j++)
			{
				grid[i][j] = ' ';
			}
		}
		grid[0][3] = obstacle;
		grid[1][3] = obstacle;
		grid[3][0] = obstacle;
		grid[3][1] = obstacle;
		grid[3][2] = obstacle;
		grid[rows - 1][columns - 1] = finish;
	}
	
	//sets 2,1 to be an obstacle as well
	public void harderBoard()
	{
		//set all vertices to spaces
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<columns; j++)
			{
				grid[i][j] = ' ';
			}
		}
		grid[0][3] = obstacle;
		grid[1][2] = obstacle;
		grid[1][3] = obstacle;
		grid[3][0] = obstacle;
		grid[3][1] = obstacle;
		grid[3][2] = obstacle;
		grid[rows - 1][columns - 1] = finish;
	}
	
	//sets an impossible board
		public void impossibleBoard()
		{
			//set all vertices to spaces
			for(int i=0; i<rows; i++)
			{
				for(int j=0; j<columns; j++)
				{
					grid[i][j] = ' ';
				}
			}
			grid[0][3] = obstacle;
			grid[1][2] = obstacle;
			grid[2][2] = obstacle;
			grid[1][3] = obstacle;
			grid[3][0] = obstacle;
			grid[3][1] = obstacle;
			grid[3][2] = obstacle;
			grid[rows - 1][columns - 1] = finish;
		}
	
	public static Board getABoard()
	{
		//board not initialized
		if(board == null)
		{
			board = new Board();
			grid = new char[columns][rows];
			initializeBoard();
		}
		else//board initialized, do nothing
		{
			//do nothing :)
		}
		
		return board;
	}
	
	public char getTile(int c, int r)
	{
		return grid[r][c];
	}
	
//	public
	
	public void display()
	{
		for(int i=0; i<rows; i++)
		{
			System.out.print("|");
			for(int j=0; j<columns; j++)
			{
				System.out.print(grid[i][j] + "|");
			}
			System.out.println();
		}
	}
	
	public char getObstacle()
	{
		return obstacle;
	}
	
	public char getFinish()
	{
		return finish;
	}
	
	public int getColumns()
	{
		return columns;
	}
	
	public int getRows()
	{
		return rows;
	}
}
