package com.revature;

import java.util.ArrayList;
import java.util.Arrays;

import com.revature.models.Board;

public class Main {

	public static void main(String[] args) 
	{
		Board myBoard = Board.getABoard();
		
//		checkAscii();
		System.out.println("Default board:");
		myBoard.display();
		pathfind(myBoard);
		
		System.out.println("\n\n A bit harder of a board");
		myBoard.harderBoard();
		myBoard.display();
		pathfind(myBoard);
		
		System.out.println("\n\n An impossible board");
		myBoard.impossibleBoard();
		myBoard.display();
		pathfind(myBoard);

	}

	private static void pathfind(Board myBoard) 
	{
		//a 2d array of x,y coordinates of impassable tiles
		ArrayList<ArrayList<Integer>> impassable = new ArrayList<ArrayList<Integer>>();
		//An array with the final path we can take
		ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();
		//character that represents impassable tiles
		final char obstacle = myBoard.getObstacle();
		//character that represents the finish tile
		final char finish = myBoard.getFinish();
		//if a path cannot be found to the exit, this will be flagged to true
		boolean trapped = false;
		boolean finished = false;
		//holds the current position, is initialized to the top left, 0,0
		final int[] startPos = {0,0};
		int[] curPos = {0,0};
		
		while(!trapped && !finished)
		{
			int[] nextRight = {curPos[0]+1, curPos[1]};
			int[] nextDown = {curPos[0], curPos[1]+1};
			ArrayList<Integer> nRight = new ArrayList<>();
			nRight.add(nextRight[0]);
			nRight.add(nextRight[1]);
			ArrayList<Integer> nDown = new ArrayList<>();
			nDown.add(nextDown[0]);
			nDown.add(nextDown[1]);
			//check if we're at the end
			if(myBoard.getTile(curPos[0], curPos[1]) == finish)
			{
				//we found a path
				finished = true;
			}
			//check we're going out of bounds, then if we can go right
			else if(curPos[0] < myBoard.getColumns()-1)
			{
				if(myBoard.getTile(nextRight[0], nextRight[1]) != obstacle &&
						!impassable.contains(nRight))
				{
					//set curPos to 1 tile to the right
					curPos[0] += 1;
					//add the current tile to the answer list
					ArrayList<Integer> newTile = new ArrayList<>(Arrays.asList(curPos[0], curPos[1]));
					answer.add(newTile);
				}
				//check if we're going out of bounds, then if we can go down
				else if(curPos[1] < myBoard.getRows()-1)
				{
					if(myBoard.getTile(curPos[0], curPos[1]+1) != obstacle &&
							!impassable.contains(nDown))
					{
						//set curPos to 1 tile down
						curPos[1] +=1;
						ArrayList<Integer> newTile = new ArrayList<>(Arrays.asList(curPos[0], curPos[1]));
						answer.add(newTile);
					}
					else//both the right and bottom are deadends, therefore this tile is also a deadend
					{
						//check if we are at the starting position
						if((curPos[0] == startPos[0]) && (curPos[1] == startPos[1]))
						{
							trapped = true;
						}
						ArrayList<Integer> newTile = new ArrayList<>(Arrays.asList(curPos[0], curPos[1]));
						impassable.add(newTile);
						answer = new ArrayList<ArrayList<Integer>>();
						curPos[0] = 0;
						curPos[1] = 0;
					}
				}
			}//check if we're going out of bounds, then if we can go down
			else if(curPos[1] < myBoard.getRows()-1)
			{
				if(myBoard.getTile(curPos[0], curPos[1]+1) != obstacle && 
						!impassable.contains(nDown))
				{
					//set curPos to 1 tile down
					curPos[1] +=1;
					ArrayList<Integer> newTile = new ArrayList<>(Arrays.asList(curPos[0], curPos[1]));
					answer.add(newTile);
				}
				else//both the right and bottom are deadends, therefore this tile is also a deadend
				{
					//check if we are at the starting position
					if(curPos == startPos)
					{
						trapped = true;
					}
					ArrayList<Integer> newTile = new ArrayList<>(Arrays.asList(curPos[0], curPos[1]));
					impassable.add(newTile);
					answer = new ArrayList<ArrayList<Integer>>();
					curPos[0] = 0;
					curPos[1] = 0;
				}
			}
			
			
		}
		
		System.out.println("Path to the finish:");
		answer.forEach(tile -> {
			System.out.println(tile);
		});
	}

	public static void checkAscii()
	{
		char var1;
		for(int i=0; i<256; i++)
		{
			var1 = (char) i;
			System.out.println(i + " = " + var1);
		}
	}
}
