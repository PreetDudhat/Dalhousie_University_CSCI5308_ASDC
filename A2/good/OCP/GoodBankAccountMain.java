import java.util.Scanner;

public class GoodBankAccountMain {
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {

        Withdraw wd = new Withdraw();
        Deposit dp = new Deposit();

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
                    bal = Deposit.deposit(dep,bal);
                    System.out.println("\n");
                    break;
                case 3:
                    System.out.println("Enter a amount to Withdraw :");
                    double wid=sc.nextDouble();
                    bal = Withdraw.withdraw(wid,bal);
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
}