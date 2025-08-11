import java.util.ArrayList;
import java.util.Date;

public class Account {
    private String accountNumber;
    private String holderName;
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor
    public Account(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: ₹" + initialBalance + " on " + new Date());
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount + " on " + new Date());
            System.out.println("✅ Deposit successful!");
        } else {
            System.out.println("❌ Invalid deposit amount!");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount + " on " + new Date());
            System.out.println("✅ Withdrawal successful!");
        } else {
            System.out.println("❌ Insufficient balance or invalid amount!");
        }
    }

    // Show balance
    public void showBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }

    // Show transaction history
    public void showTransactionHistory() {
        System.out.println("\n📜 Transaction History for " + holderName + ":");
        for (String record : transactionHistory) {
            System.out.println(record);
        }
    }
}

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create account
        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Balance: ₹");
        double balance = sc.nextDouble();

        Account account = new Account(accNum, name, balance);

        // Menu-driven program
        int choice;
        do {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Balance");
            System.out.println("4. Show Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmt = sc.nextDouble();
                    account.deposit(depositAmt);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmt = sc.nextDouble();
                    account.withdraw(withdrawAmt);
                    break;
                case 3:
                    account.showBalance();
                    break;
                case 4:
                    account.showTransactionHistory();
                    break;
                case 5:
                    System.out.println("👋 Thank you for banking with us!");
                    break;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}