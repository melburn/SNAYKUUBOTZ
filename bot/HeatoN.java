package bot;

import gameLogic.*;

public class HeatoN implements Brain
{
	public HeatoN()
	{
		System.out.print("LOsdadsadL");
	}
	
	public Direction getNextMove(Snake yourSnake, GameState gamestate)
	{
		self = yourSnake;
		this.gamestate = gamestate;
		
		Direction previousDirection = self.getCurrentDirection();
		if (gamestate.willCollide(self, previousDirection))
		{
			return previousDirection.turnLeft();
		}
		return previousDirection;
	}
}
