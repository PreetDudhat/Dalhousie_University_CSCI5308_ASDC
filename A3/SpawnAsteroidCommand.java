public class 	SpawnAsteroidCommand extends Command
{
	public SpawnAsteroidCommand(Object receiver, String[] args)
	{
		super(receiver, args);
	}

	@Override
	public void Execute()
	{
		Square square = (Square) receiver;
		int height = Integer.parseInt(args[2]);
		IAsteroidGameFactory factory = GameBoard.Instance().GetFactory();
		System.out.println("Spawning asteroid at (" + args[0] + "," + args[1] + ") with height = " + height);
		BoardComponent asteroid = factory.MakeAsteroid(square.GetX(), square.GetY(), height);
		square.Add(asteroid);
		asteroid.Attach();
	}
}