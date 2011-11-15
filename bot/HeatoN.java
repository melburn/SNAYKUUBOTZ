package bot;

import gameLogic.*;

public class HeatoN implements Brain
{
	Snake self;
	GameState gamestate;
	public HeatoN()
	{
		System.out.print("LOsdadsadL");
	}
	
	public Direction getNextMove(Snake yourSnake, GameState gamestate)
	{
		self = yourSnake;
		this.gamestate = gamestate;
		
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
		if (gamestate.willCollide(self, direction))
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
	
	
}
