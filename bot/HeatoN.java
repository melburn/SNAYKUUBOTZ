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
		
	/*	Position fruitPosition = getClosestFruit();
		if(fruitPosition != null && gamestate.distanceBetween(fruitPosition, self.getHeadPosition()) < range)
		{
			//TODO pursuit froot
		} */
		
		Direction previousDirection = self.getCurrentDirection();
		
		if (!okToGo(previousDirection))// inte okej att gå rakt
		{
			// behöver alternativ 
			if(okToGo(previousDirection.turnLeft()))
			{
				return previousDirection.turnLeft();
			}
			
			if(okToGo(previousDirection.turnRight()))
			{
				return previousDirection.turnRight();
			}
		}
		return previousDirection; // gå rakt eller inget alt
	}
	
	private boolean okToGo(Direction direction)
	{
		Position toCheck = direction.calculateNextPosition(self.getHeadPosition());
		Board board = gamestate.getBoard();
		
		// kommer collida dir		
		if (board.isLethal(toCheck))
		{
			return false;	
		}
		
		// om det kan vara en trap		
		Position left =  direction.turnLeft().calculateNextPosition(self.getHeadPosition());
		Position right =  direction.turnRight().calculateNextPosition(self.getHeadPosition());
		
		if(board.isLethal(left) && board.isLethal(right))
		{
			// might be trap
			return doTheWalk(direction);
		}
		
		return true;
		
	}
	

	public boolean doTheWalk(Direction direction)
	{
		return false;
	}
	
	

	private Position getClosestFruit()
	{
		ArrayList<Position> fruitList = gamestate.getFruits();
		if (fruitList == null)
			return null;
		Position closestFruit = fruitList.get(0);
		int shortestDistance = 10000000;
		for (Position p : fruitList)
		{
			int fruitDistance = gamestate.distanceBetween(self.getHeadPosition(), closestFruit);
			if (closestFruit == null)
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

