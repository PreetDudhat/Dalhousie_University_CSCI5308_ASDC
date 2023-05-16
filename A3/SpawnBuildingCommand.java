public class SpawnBuildingCommand extends Command
{
    public SpawnBuildingCommand(Object receiver, String[] args)
    {
        super(receiver, args);
    }

    @Override
    public void Execute()
    {
        Square square = (Square) receiver;
        IAsteroidGameFactory factory = GameBoard.Instance().GetFactory();
        System.out.println("Spawning building at (" + args[0] + "," + args[1] + ")");
        BoardComponent building = factory.MakeBuilding(square.GetX(), square.GetY());
        square.Add(building);
        building.Attach();

        GameBoard.Instance().IncrementBuildingCount();
    }
}
