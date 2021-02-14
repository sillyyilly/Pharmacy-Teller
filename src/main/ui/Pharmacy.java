package ui;

import model.Item;
import model.Order;

import java.util.Scanner;

public class Pharmacy {

    private final Item advil = new Item("Advil",1000);
    private final Item tylenol = new Item("Tylenol",1500);
    private final Item midol = new Item("Midol",700);
    private final Item gravol = new Item("Gravol",1200);
    private Scanner input;
    private Order newOrder;

    // EFFECTS: runs the pharmacy application
    public Pharmacy() {
        runPharmacy();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPharmacy() {
        boolean keepGoing = true;
        String command = null;

        order(); //initializes order

        while (keepGoing) {
            displayMenu(); //display menu of options to the user
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nHave a good day!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddItem();
        } else if (command.equals("c")) {
            doCheckout();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //    // EFFECTS: creates empty order
    private void order() {
        newOrder = new Order();
        input = new Scanner(System.in);

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to the pharmacy! Please select from:");
        System.out.println("\ta -> add item to order");
        System.out.println("\tc -> checkout");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds Item to order list
    private void doAddItem() {

        boolean keepGoing = true;
        String addItemCommand = null;
        String continueCommand = null;


        displayAddItemMenu();
        addItemCommand = input.next();
        addItemCommand = addItemCommand.toLowerCase();

        processAddItemCommand(addItemCommand);

        System.out.println("Would you like to add another item?");
        displayContinueMenu();
        continueCommand = input.next();
        continueCommand = continueCommand.toLowerCase();

        processContinueCommand(continueCommand);



    }

    private void processContinueCommand(String continueCommand) {
        if (continueCommand.equals("y")) {
            doAddItem();
        } else if (continueCommand.equals("n")) {
            //ignored
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayContinueMenu() {
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");
    }

    // EFFECTS: displays menu of options to user
    private void displayAddItemMenu() {
        System.out.println("Select item to add to order");
        System.out.println("\t1 -> Advil (Price: $10)");
        System.out.println("\t2 -> Tylenol (Price: $15)");
        System.out.println("\t3 -> Midol (Price: $7)");
        System.out.println("\t4 -> Gravol (Price: $12)");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processAddItemCommand(String addItemCommand) {
        if (addItemCommand.equals("1")) {
            newOrder.addToOrder(advil);
        } else if (addItemCommand.equals("2")) {
            newOrder.addToOrder(tylenol);
        } else if (addItemCommand.equals("3")) {
            newOrder.addToOrder(midol);
        } else if (addItemCommand.equals("4")) {
            newOrder.addToOrder(gravol);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes order and returns order total
    private void doCheckout() {

        printPrice(newOrder);
        newOrder = new Order();

    }

    // EFFECTS: prints price of order to the screen
    private void printPrice(Order newOrder) {
        System.out.printf("Price: $%.2f\n", (newOrder.orderPrice() / 100.0));
        // price stored as int in cents, divide by 100.0 to convert to float
    }
}