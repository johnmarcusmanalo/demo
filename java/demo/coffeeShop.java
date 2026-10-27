import java.util.Scanner;

public class coffeeShop {
    private static int nextReceiptNumber = 1;

    private static final String[] COFFEES = {
        "Matcha Latte", "Espresso", "Sea-salt Spanish Latte", "Mocha", "Caramel Macchiato"
    };
    private static final double[] COFFEE_PRICES = {89.99, 79.99, 120.00, 75.99, 100.00};

    private static final String[] DESSERTS = {
        "Chocolate Cake", "Blueberry Muffin", "Cheesecake", "Croissant", "Cookie"
    };
    private static final double[] DESSERT_PRICES = {4.50, 2.25, 4.75, 2.50, 1.75};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] coffeeCart = new int[COFFEES.length];
        int[] dessertCart = new int[DESSERTS.length];
        boolean running = true;


        System.out.println("=================================");
        System.out.println("\n--- Welcome to the MerCoffee! ---");
        while (running) {
            System.out.println("\n=========== Main Menu ===========\n");
            System.out.println("1. View coffee menu");
            System.out.println("2. View dessert menu");
            System.out.println("3. Order items");
            System.out.println("4. View cart and checkout");
            System.out.println("5. Exit");
            int choice = readChoice(scanner, "\n\nChoose an option: ");

            switch (choice) {
                case 1:
                    showMenu(scanner, COFFEES, COFFEE_PRICES, "Coffee");
                    break;
                case 2:
                    showMenu(scanner, DESSERTS, DESSERT_PRICES, "Dessert");
                    break;
                case 3:
                    orderItems(scanner, coffeeCart, dessertCart);
                    break;
                case 4:
                    checkout(scanner, coffeeCart, dessertCart);
                    break;
                case 5:
                    running = false;
                    System.out.println("\n======================================");
                    System.out.println("\nThank you for visiting MerCoffee!");
                    System.out.println("\n======================================");
                    break;
                default:
                    System.out.println("Please choose a number from 1 to 5.");
            }
        }
        scanner.close();
    }

    private static void showMenu(Scanner scanner, String[] items, double[] prices, String title) {
        boolean viewing = true;
        while (viewing) {
            System.out.println("\n=========== " + title + " Menu ==========\n");
            System.out.println(String.format("%-3s %-25s %s", "#", "Product", "Price"));
            System.out.println("------------------------------------------");
            for (int i = 0; i < items.length; i++) {
                System.out.println(String.format(
                        "%-3d %-25s PHP %8.2f", i + 1, items[i], prices[i]));
            }
            System.out.println("\n0. Back to main menu");
            int choice = readChoice(scanner, "\n\nChoose an option: ");
            if (choice == 0) {
                viewing = false;
            } else if (choice >= 1 && choice <= items.length) {
                System.out.println(String.format(
                        "%s - PHP %.2f", items[choice - 1], prices[choice - 1]));
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }

    private static void orderItems(Scanner scanner, int[] coffeeCart, int[] dessertCart) {
        boolean ordering = true;
        while (ordering) {
            System.out.println("\n========== Order Items ===========\n");
            System.out.println("1. Add coffee");
            System.out.println("2. Add dessert");
            System.out.println("\n0. Back to main menu\n");
            int category = readChoice(scanner, "Choose a category: ");

            switch (category) {
                case 1:
                    addItem(scanner, COFFEES, COFFEE_PRICES, coffeeCart, "coffee");
                    break;
                case 2:
                    addItem(scanner, DESSERTS, DESSERT_PRICES, dessertCart, "dessert");
                    break;
                case 0:
                    ordering = false;
                    break;
                default:
                    System.out.println("Choose 1, 2, or 0 to go back.");
            }
        }
    }

        private static void addItem(
            Scanner scanner,
            String[] items,
            double[] prices,
            int[] cart,
            String category) {
        boolean selecting = true;
        while (selecting) {
            System.out.print("==========================================\n");
            System.out.println("\nSelect a " + category + ":\n");
            System.out.println(String.format("%-3s %-25s %s", "#", "Product", "Price"));
            System.out.println("------------------------------------------");
            for (int i = 0; i < items.length; i++) {
                System.out.println(String.format(
                        "%-3d %-25s PHP %8.2f", i + 1, items[i], prices[i]));
            }
            System.out.println("\n0. Back");
            int choice = readChoice(scanner, "Item number: ");
            if (choice == 0) {
                selecting = false;
            } else if (choice >= 1 && choice <= items.length) {
                int index = choice - 1;
                cart[index]++;
                System.out.println(items[index] + " added to your cart.");
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }

    private static void checkout(Scanner scanner, int[] coffeeCart, int[] dessertCart) {
        double total = 0.0;
        System.out.println("\n=== Your Cart ===");
        for (int i = 0; i < coffeeCart.length; i++) {
            if (coffeeCart[i] > 0) {
                double subtotal = coffeeCart[i] * COFFEE_PRICES[i];
                System.out.println(String.format(
                        "%-25s x%-3d PHP %8.2f", COFFEES[i], coffeeCart[i], subtotal));
                total += subtotal;
            }
        }
        for (int i = 0; i < dessertCart.length; i++) {
            if (dessertCart[i] > 0) {
                double subtotal = dessertCart[i] * DESSERT_PRICES[i];
                System.out.println(String.format(
                        "%-25s x%-3d PHP %8.2f", DESSERTS[i], dessertCart[i], subtotal));
                total += subtotal;
            }
        }

        if (total == 0.0) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.printf("Total: PHP %.2f%n", total);
        System.out.println("1. Complete checkout");
        System.out.println("0. Back to main menu");
        int choice = readChoice(scanner, "Choose an option: ");
        if (choice == 1) {
            double payment;
            do {
                payment = readPayment(scanner, total);
                if (payment < total) {
                    System.out.printf("Insufficient payment. You still owe PHP %.2f.%n", total - payment);
                }
            } while (payment < total);

            printReceipt(coffeeCart, dessertCart, total, payment);
            for (int i = 0; i < coffeeCart.length; i++) {
                coffeeCart[i] = 0;
            }
            for (int i = 0; i < dessertCart.length; i++) {
                dessertCart[i] = 0;
            }
        } else if (choice == 0) {
            System.out.println("Returning to the main menu. Your cart is saved.");
        } else {
            System.out.println("Invalid selection. Your cart is saved.");
        }
    }

    private static double readPayment(Scanner scanner, double total) {
        while (true) {
            System.out.printf("Amount to pay (total PHP %.2f): PHP", total);
            String input = scanner.nextLine().trim();
            try {
                double payment = Double.parseDouble(input);
                if (Double.isFinite(payment) && payment >= 0.0) {
                    return payment;
                }
            } catch (NumberFormatException exception) {
                // Ask again for a valid amount.
            }
            System.out.println("Please enter a valid, non-negative amount.");
        }
    }

        private static void printReceipt(
            int[] coffeeCart,
            int[] dessertCart,
            double total,
            double payment) {
        String receiptCode = String.format("CS-%06d", nextReceiptNumber++);
        System.out.println("\n=== Coffee Shop Receipt ===");
        System.out.println(String.format("Receipt: %s", receiptCode));
        System.out.println(String.format("%-25s %-4s %12s", "Product", "Qty", "Cost (PHP)"));
        System.out.println("-------------------------------------------");
        for (int i = 0; i < coffeeCart.length; i++) {
            if (coffeeCart[i] > 0) {
                System.out.println(String.format(
                        "%-25s %-4d PHP %8.2f", COFFEES[i], coffeeCart[i],
                        coffeeCart[i] * COFFEE_PRICES[i]));
            }
        }
        for (int i = 0; i < dessertCart.length; i++) {
            if (dessertCart[i] > 0) {
                System.out.println(String.format(
                        "%-25s %-4d PHP %8.2f", DESSERTS[i], dessertCart[i],
                        dessertCart[i] * DESSERT_PRICES[i]));
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println(String.format("%-30s PHP %8.2f", "Total:", total));
        System.out.println(String.format("%-30s PHP %8.2f", "Paid:", payment));
        System.out.println(String.format("%-30s PHP %8.2f", "Change:", payment - total));
        System.out.println("-------------------------------------------");
        System.out.println("Order confirmed. Thank you for your purchase! @MerCoffee");
    }

    private static int readChoice(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
