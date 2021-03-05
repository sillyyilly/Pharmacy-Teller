package ui;

import model.Item;
import model.Order;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Pharmacy {

    private final Item advil = new Item("Advil",1000);
    private final Item tylenol = new Item("Tylenol",1500);
    private final Item midol = new Item("Midol",700);
    private final Item gravol = new Item("Gravol",1200);
    private Scanner input;
    private Order newOrder;
    private static final String JSON_STORE = "./data/order.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the pharmacy application
    public Pharmacy() {
        input = new Scanner(System.in);
        newOrder = new Order("Current Order");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPharmacy();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPharmacy() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

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
        } else if (command.equals("p")) {
            printOrder();
        } else if (command.equals("s")) {
            saveOrder();
        } else if (command.equals("l")) {
            loadOrder();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to the pharmacy! Please select from:");
        System.out.println("\ta -> add item to order");
        System.out.println("\tp -> print items in order");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
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

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processContinueCommand(String continueCommand) {
        if (continueCommand.equals("y")) {
            doAddItem();
        } else if (continueCommand.equals("n")) {
            //ignored
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
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
        newOrder = new Order("Current Order");

    }

    // EFFECTS: prints price of order to the screen
    private void printPrice(Order newOrder) {
        System.out.printf("Price: $%.2f\n", (newOrder.orderPrice() / 100.0));
        // price stored as int in cents, divide by 100.0 to convert to float
    }

    // EFFECTS: prints all the items in order to the console
    private void printOrder() {
        List<Item> itemsOrdered = newOrder.getOrderItems();

        for (Item t : itemsOrdered) {
            String priceInDollars = String.format("$%.2f", t.getItemPrice() / 100.0);
            System.out.println(t.getItemName() + " " + priceInDollars);
        }
    }

    // EFFECTS: saves the order to file
    private void saveOrder() {
        try {
            jsonWriter.open();
            jsonWriter.write(newOrder);
            jsonWriter.close();
            System.out.println("Saved " + newOrder.getOrderName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: loads order
    private void loadOrder() {
        try {
            newOrder = jsonReader.read();
            System.out.println("Loaded " + newOrder.getOrderName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}