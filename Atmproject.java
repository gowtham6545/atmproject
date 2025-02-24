import java.util.ArrayList;
import java.util.Scanner;

public class Atmproject {

    public static void main(String args[]) {
        int balance = 50000;
        int withdraw, deposit;
        String mobileNumber = "";
        int pin = 1234;
        int maxWithdraw = 20000;
        int maxDeposit = 50000;
        ArrayList<String> transactionHistory = new ArrayList<>();

        Scanner sc = new Scanner(System.in);


        System.out.print("Enter your PIN to access the ATM: ");
        int enteredPin = sc.nextInt();
        sc.nextLine();  // Consume newline after PIN input
        if (enteredPin != pin) {
            System.out.println("Invalid PIN. Exiting...");
            System.exit(0);
        }

        while (true) {
            System.out.println("Welcome to ATM ... ");
            System.out.println("Select 1 for Withdraw");
            System.out.println("Select 2 for Deposit");
            System.out.println("Select 3 for Check Balance");
            System.out.println("Select 4 to Set or Update Mobile Number");
            System.out.println("Select 5 to View Mobile Number");
            System.out.println("Select 6 to View Transaction History");
            System.out.println("Select 7 for EXIT");
            System.out.print("Select the appropriate option you want to perform: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Enter the amount to be withdrawn: ");
                    withdraw = sc.nextInt();
                    sc.nextLine(); // Consume newline left-over
                    balance = withdraw(balance, withdraw, maxWithdraw, transactionHistory);
                    break;
                case 2:
                    System.out.print("Enter the amount to be deposited: ");
                    deposit = sc.nextInt();
                    sc.nextLine(); // Consume newline left-over
                    balance = deposit(balance, deposit, maxDeposit, transactionHistory);
                    break;
                case 3:
                    printBalance(balance);
                    break;
                case 4:
                    System.out.print("Enter your mobile number: ");
                    mobileNumber = sc.nextLine();
                    if (mobileNumber.matches("\\d{10}")) {
                        System.out.println("Mobile number has been set/updated successfully.");
                    } else {
                        System.out.println("Invalid mobile number format. Please enter a 10-digit number.");
                    }
                    break;
                case 5:
                    if (mobileNumber.isEmpty()) {
                        System.out.println("No mobile number set. Please set your mobile number first.");
                    } else {
                        System.out.println("Your registered mobile number is: " + mobileNumber);
                    }
                    break;
                case 6:
                    printTransactionHistory(transactionHistory);
                    break;
                case 7:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
    }

    public static void printBalance(int balance) {
        System.out.println("The Current Balance: " + balance);
    }

    public static int withdraw(int balance, int withdrawAmount, int maxWithdraw, ArrayList<String> history) {
        System.out.println("Withdraw Operation:");
        if (withdrawAmount > maxWithdraw) {
            System.out.println("Exceeds maximum withdraw limit of " + maxWithdraw);
            return balance;
        }
        if (balance >= withdrawAmount) {
            balance -= withdrawAmount;
            System.out.println("Please collect your money and remove the card.");
            printBalance(balance);
            history.add("Withdrawn: " + withdrawAmount);
        } else {
            System.out.println("Sorry! The balance is insufficient.");
        }
        return balance;
    }

    public static int deposit(int balance, int depositAmount, int maxDeposit, ArrayList<String> history) {
        System.out.println("Deposit Operation:");
        if (depositAmount > maxDeposit) {
            System.out.println("Exceeds maximum deposit limit of " + maxDeposit);
            return balance;
        }
        balance += depositAmount;
        System.out.println("Your money has been successfully deposited.");
        printBalance(balance);
        history.add("Deposited: " + depositAmount);
        return balance;
    }

    public static void printTransactionHistory(ArrayList<String> history) {
        System.out.println("Transaction History:");
        if (history.isEmpty()) {
            System.out.println("No transactions have been made.");
        } else {
            for (String transaction : history) {
                System.out.println(transaction);
            }
        }
    }
}
