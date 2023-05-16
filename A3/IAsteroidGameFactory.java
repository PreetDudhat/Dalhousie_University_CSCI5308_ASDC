import java.util.ArrayList;

// This is the abstract factory requirement.
// It needs to create their squares, buildings, shields and asteroids.
public interface IAsteroidGameFactory
{
	public BoardComponent MakeSquare(int x, int y);
	public BoardComponent MakeBuilding(int x, int y);
	public BoardComponent MakeShield(BoardComponent squareToDecorate);
	public Asteroid MakeAsteroid(int x, int y, int height);

	public ArrayList<ArrayList<BoardComponent>> MakeBoard(int height, int width);
	public Command MakeCommand(String commandLine);
}