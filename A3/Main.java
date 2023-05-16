public class Main
{
	public static void main(String[] args)
	{
		try{
			GameBoard.Instance().ProcessCommands(args[0]);

		}
		catch (ArrayIndexOutOfBoundsException ae){
			System.out.println("Error");
		}
	}
}
