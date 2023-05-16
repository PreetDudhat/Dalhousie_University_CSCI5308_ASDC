import java.util.Scanner;

class Withdraw implements Transaction{
    public static Scanner sc=new Scanner(System.in);
    public static double withdraw(double wid, double bal) {
        if (wid != 0 && bal >= wid)
        {
            System.out.println("Amount withdrawn");
            bal -= wid;
        }
        else if (bal < wid)
        {
            System.out.println("Bank balance insufficient");
            System.out.println("Enter sufficient balance ");
            double temp = sc.nextDouble();
            withdraw(temp,bal);
        }
        return bal;
    }

    @Override
    public void Withdraw() {

    }

    @Override
    public void Deposit() {

    }
}
