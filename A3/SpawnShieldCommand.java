import java.util.ArrayList;
public class SpawnShieldCommand extends Command
{
    public SpawnShieldCommand(Object receiver, String[] args)
    {
        super(receiver, args);
    }

    @Override
    public void Execute()
    {
        //receiver is the singleton GameBoard
        GameBoard board = (GameBoard)receiver;
        System.out.println("Spawning shield at (" + args[0] + "," + args[1] + ")");
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        BoardComponent square = board.GetBoard().get(y).get(x);

        //singleton factory create shieldedSquare
        IAsteroidGameFactory factory = GameBoard.Instance().GetFactory();
        BoardComponent shieldedSquare = factory.MakeShield(square);
        ArrayList<BoardComponent> row = board.GetBoard().get(y);
        row.set(x,shieldedSquare);
        shieldedSquare.Attach();
    }
}