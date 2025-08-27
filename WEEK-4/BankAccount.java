import java.util.*;

class BankAccount 
{
    private int accNo;
    private String name;
    private double balance;

    BankAccount(int accNo, String name, double balance) 
	{
        this.accNo = accNo; this.name = name; this.balance = balance;
    }

    void deposit(double amt) { balance += amt; }
    void withdraw(double amt) { if (balance >= amt) balance -= amt; }
    void transfer(BankAccount to, double amt) {
        if (balance >= amt) { withdraw(amt); to.deposit(amt); }
    }

    public String toString() { return accNo + " " + name + " " + balance; }
}

public class BankSystem 
{
    public static void main(String[] args) 
	{
        ArrayList<BankAccount> accounts = new ArrayList<>();
        BankAccount a1 = new BankAccount(101, "Alice", 1000);
        BankAccount a2 = new BankAccount(102, "Bob", 500);
        accounts.add(a1); accounts.add(a2);

        a1.deposit(200);
        a1.withdraw(100);
        a1.transfer(a2, 300);

        for (BankAccount acc : accounts) System.out.println(acc);
    }
}
