class Deposit implements Transaction {
    public static double deposit(double amount, double bal) {
        if (amount != 0) {
            bal += amount;
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
