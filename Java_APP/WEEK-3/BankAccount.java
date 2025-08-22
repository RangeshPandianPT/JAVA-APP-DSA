public class BankAccount
 {
    String accountNumber;
    String accountHolder;
    double balance;

    BankAccount(String accountNumber, String accountHolder, double balance)
	{
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    void displayBalance() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: ₹" + balance);

    }

    public static void main(String[] args) 
	{
        BankAccount acc1 = new BankAccount("1234567890", "Aarav Sharma", 50000.75);
        BankAccount acc2 = new BankAccount("9876543210", "Diya Mehta", 8900.50);

        acc1.displayBalance();
        acc2.displayBalance();
    }
}
