import java.util.Scanner;

public class BadBankAccountMain {
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        double bal;
        String customerName;
        System.out.println("Enter Customer Name");
        customerName = sc.nextLine();
        System.out.println("Enter Customer Balance");
        bal = sc.nextDouble();
        int choice;
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome "+customerName);
        System.out.println("\n");
        System.out.println("1) Check Balance");
        System.out.println("2) Deposit Amount");
        System.out.println("3) Withdraw Amount");
        System.out.println("4) Exit");
        do{
            System.out.println("Choose an option");
            choice=sc.nextInt();
            System.out.println("\n");

            switch (choice){
                case 1:
                    System.out.println("Balance ="+bal);
                    System.out.println("\n");
                    break;
                case 2:
                    System.out.println("Enter a amount to deposit :");
                    double dep=sc.nextDouble();
                    bal = deposit(dep,bal);
                    System.out.println("\n");
                    break;
                case 3:
                    System.out.println("Enter a amount to Withdraw :");
                    double wid=sc.nextDouble();
                    bal = withdraw(wid,bal);
                    System.out.println("\n");
                    break;
                case 4:
                    System.out.println("");
                    break;
                default:
                    System.out.println("Choose a correct option to proceed");
                    break;
            }

        }while(choice!=5);
        System.out.println("Thank you for using our banking services");
    }
    public static double deposit(double amount, double bal) {
        if (amount != 0) {
            bal += amount;
        }
        return bal;
    }
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

}