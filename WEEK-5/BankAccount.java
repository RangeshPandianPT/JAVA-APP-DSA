// Abstract base class
abstract class BankAccount {
    String accountNumber;
    double balance;

    // Constructor
    BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Abstract method - must be implemented by all child classes
    abstract double calculateInterest();
}

// Savings Account
class SavingsAccount extends BankAccount {
    SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    double calculateInterest() {
        return balance * 0.04; // 4% interest
    }
}

// Current Account
class CurrentAccount extends BankAccount {
    CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    double calculateInterest() {
        return balance * 0.01; // 1% interest
    }
}

// Loan Account
class LoanAccount extends BankAccount {
    LoanAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    double calculateInterest() {
        return balance * 0.08; // 8% interest (amount owed)
    }
}

// Test Program
public class Main {
    public static void main(String[] args) {
        BankAccount savings = new SavingsAccount("S123", 10000);
        BankAccount current = new CurrentAccount("C456", 20000);
        BankAccount loan = new LoanAccount("L789", 5000);

        System.out.println("Savings Interest: " + savings.calculateInterest());
        System.out.println("Current Interest: " + current.calculateInterest());
        System.out.println("Loan Interest: " + loan.calculateInterest());
    }
}
