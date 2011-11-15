package bot;

import gameLogic.*;
import java.util.*;


public class HeatoN implements Brain
{
	private GameState gamestate;
	private Board currBoard;
	private Snake self;
	public HeatoN()
	{
		System.out.print("LOsdadsadL");
	}
	public Direction getNextMove(Snake yourSnake, GameState gamestate)
	{
		self = yourSnake;
		this.gamestate = gamestate;
		currBoard = gamestate.getBoard();
		int range = 10;
		
		Position fruitPosition = getClosestFruit();
		if(fruitPosition != null && gamestate.distanceBetween(fruitPosition, self.getHeadPosition()) < range)
		{
			//TODO pursuit froot
		}
		
		Direction previousDirection = self.getCurrentDirection();
		if (gamestate.willCollide(self, previousDirection))
		{
			if(gamestate.willCollide(self, previousDirection.turnLeft()))
				return previousDirection.turnRight();
			return previousDirection.turnLeft();
		}
		return previousDirection;
	}
	
	private Position getClosestFruit()
	{
		ArrayList<Position> fruitList = gamestate.getFruits();
		if (fruitList == null)
			return null;
		Position closestFruit = null;
		int shortestDistance = 10000000;
		for (Position p : fruitList)
		{
			int fruitDistance = gamestate.distanceBetween(self.getHeadPosition(), closestFruit);
			if (closestFruit != null)
			{
				closestFruit = p;
				shortestDistance = fruitDistance;
			}
			if (fruitDistance < shortestDistance)
			{
				closestFruit = p;
				shortestDistance = fruitDistance;
			}
		}
		return closestFruit;
	}
	
}

