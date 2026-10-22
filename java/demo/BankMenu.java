import java.util.Scanner;

public class BankMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============================================");
        System.out.println("\n\n     Welcome to the Mer Bank           ");
        System.out.println("\n\n=============================================");

        double balance = 1000.00;
        int choice = 0;
        String accountName = "";
        int accountNumber = 0;

        do {
            System.out.println("\nPlease select an option:");
            System.out.println("\n[1] Create a new account");
            System.out.println("[2] View Account Balance");
            System.out.println("[3] Deposit funds");
            System.out.println("[4] Withdraw funds");
            System.out.println("[5] Exit");
            System.out.print("\nEnter your choice (1-5): ");
            choice = scanner.nextInt();
        


        switch (choice) {
            case 1:
                System.out.println("\n============================================");
                // Add logic for creating a new account

                System.out.println("\nCreating a new account");
                System.out.print("\nEnter your name: ");
                accountName = scanner.next();
                System.out.print("Enter your account number: ");
                accountNumber = scanner.nextInt();
                System.out.println("\nAccount created successfully for " + accountName + "\nwith account number " + accountNumber);
                System.out.println("\n[1] Back");
                System.out.println("\n============================================");

                
                break;

            case 2:
                System.out.println("\n============================================");
                    // Add logic for viewing account details    

                    System.out.println("\nViewing Account Balance");
                System.out.println("\n---------------------------------------------");
                System.out.println("\nAccount Name: " + accountName);
                System.out.println("Account Number: " + accountNumber);
                System.out.println("\n---------------------------------------------");
                System.out.println("\nCurrent Balance: $" + balance); 

                System.out.println("\n============================================");
                break;

            case 3:
                System.out.println("\n============================================");
                System.out.println("Depositing funds");

                // Add logic for depositing funds
                
                System.out.print("\nEnter the amount to deposit: ");
                double depositAmount = scanner.nextDouble();

                balance += depositAmount;
                
                System.out.println("\nNew Balance: $" + balance);
                System.out.println("\n============================================");
                break;

            case 4:
                System.out.println("\n============================================");
                System.out.println("Withdrawing funds");
                System.out.print("\nEnter the amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                if (withdrawAmount <= balance) {
                    balance -= withdrawAmount;
                    System.out.println("\nNew Balance: $" + balance);
                } else {
                    System.out.println("\nNigga you broke as fuck, you can't withdraw that much.");
                }
                System.out.println("\n============================================");
                break;
            
            case 5:

                System.out.println("\n============================================");
                System.out.println("\nExiting the program. Thank you!");
                System.out.println("\n--------------------------------------------");
                System.out.println("\n\nSimple Bank UI has been closed.");
                System.out.println("\n============================================");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
        
        } while (choice != 5);

        scanner.close();

       
    }
}
