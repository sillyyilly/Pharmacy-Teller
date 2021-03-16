package ui;

import model.Checkout;
import model.Inventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Pharmacy extends JFrame {

    private Scanner input;
    private Inventory newInventory;
    private Checkout newCheckout;
    private static final String JSON_STORE = "./data/inventory.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    int pressed = 0;
    JButton addItemToInventoryButton;
    JButton printInventoryButton;

    public Pharmacy() {
        super("Pharmacy");

        setVisible(true);
        setSize(800, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(100, 30);

        addItemToInventoryButton = new JButton();
        addItemToInventoryButton.setText("Add Item to Inventory");
        addItemToInventoryButton.addActionListener(event -> addItem(event));
        printInventoryButton = new JButton();
        printInventoryButton.setText("Print Items in Inventory");


        buttonPanel.add(addItemToInventoryButton);
        buttonPanel.add(printInventoryButton);
        add(buttonPanel);
    }

    private void addItem(ActionEvent event) {

        AddItem newItem = new AddItem();
    }

    private ActionListener onItemAdded() {
        return (event) -> {
            addItemToInventoryButton.setText("Items " + ++pressed);
        };
    }
}
//
//    input = new Scanner(System.in);
//    newInventory = new Inventory("inventoryName");
//    newCheckout = new Checkout();
//    jsonWriter = new JsonWriter(JSON_STORE);
//    jsonReader = new JsonReader(JSON_STORE);
//    input = new Scanner(System.in);
//    loadInventory();
//    runPharmacy();
//        System.out.println("\nHave a good day!");
//
//
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runPharmacy() {
//        displayMenu(); //display menu of options to the user
//        switch (input.next().toLowerCase()) {
//            case "q": return;
//            case "a":
//                addItemToInventory();
//                break;
//            case "p":
//                printInventory();
//                break;
//            case "s":
//                saveInventory();
//                break;
//            case "c":
//                checkout();
//                break;
//            case "d":
//                deleteItemsFromInventory();
//                break;
//            default:
//                System.out.println("Selection not valid...");
//                break;
//        }
//        runPharmacy();
//    }
//
//    //EFFECTS: loads inventory
//    private void loadInventory() {
//        File data = new File(JSON_STORE);
//        boolean exists = data.exists();
//
//        try {
//            if (exists) {
//                newInventory = jsonReader.read();
//            } else {
//                newInventory = new Inventory("Inventory");
//            }
//            System.out.println("Loaded " + newInventory.getInventoryName() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//    private void deleteItemsFromInventory() {
//        newInventory = new Inventory("Inventory");
//        System.out.println("All items deleted from inventory");
//    }
//
//
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nWelcome to the pharmacy! Please select from:");
//        System.out.println("\ta -> add item to inventory");
//        System.out.println("\tp -> print items in inventory");
//        System.out.println("\ts -> save items to inventory");
//        System.out.println("\tc -> checkout");
//        System.out.println("\td -> delete items from inventory");
//        System.out.println("\tq -> quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds Item to order list
//    private void addItemToInventory() {
//
//        System.out.println("Enter Item Name:");
//        String newItemName = input.next();
//
//        System.out.println("Enter Item Price:");
//        int newItemPrice = Integer.parseInt(input.next());
//
//        System.out.println("Added item successfully");
//        newInventory.addToInventory(new Item(newItemName, newItemPrice));
//
//    }
//
//
//    // EFFECTS: prints all the items in order to the console
//    private void printInventory() {
//        List<Item> itemsInInventory = newInventory.getInventoryItems();
//
//        for (Item t : itemsInInventory) {
//            String indexNumber = String.valueOf(itemsInInventory.indexOf(t));
//            String priceInDollars = String.format("$%.2f", t.getItemPrice() / 100.0);
//            System.out.println("[" + indexNumber + "]" + " " + t.getItemName() + " " + priceInDollars);
//        }
//    }
//
//    // EFFECTS: saves the order to file
//    private void saveInventory() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(newInventory);
//            jsonWriter.close();
//            System.out.println("Saved " + newInventory.getInventoryName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    private void checkout() {
//        printInventory();
//        System.out.println("Select items for checkout:");
//        List<Item> itemsInInventory = newInventory.getInventoryItems();
//        int itemIndex = Integer.parseInt(input.next());
//
//        newCheckout.addToCheckout(itemsInInventory.get(itemIndex));
//        System.out.println("Successfully added item to checkout.");
//        displayContinueMenu();
//        String continueCommand = input.next();
//        continueCommand = continueCommand.toLowerCase();
//        processContinueCommand(continueCommand);
//
////        String priceInDollars = String.format("$%.2f", itemsInInventory.get(itemIndex).getItemPrice() / 100.0);
////        System.out.println(itemsInInventory.get(itemIndex).getItemName() + " " + priceInDollars);
//
//    }
//
//    private void displayContinueMenu() {
//        System.out.println("Would you like to add another item to checkout?");
//        System.out.println("\ty -> yes");
//        System.out.println("\tn -> no");
//    }
//
//    private void processContinueCommand(String continueCommand) {
//        if (continueCommand.equals("y")) {
//            checkout();
//        } else if (continueCommand.equals("n")) {
//            finalCheckoutPrice();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//    private void finalCheckoutPrice() {
//        List<Item> itemsInCheckout = newCheckout.getCheckoutItems();
//        for (Item c : itemsInCheckout) {
//            String priceInDollars = String.format("$%.2f", c.getItemPrice() / 100.0);
//            System.out.println(c.getItemName() + " " + priceInDollars);
//        }
//        String totalOrderPrice = String.format("$%.2f", newCheckout.checkoutPrice() / 100.0);
//        System.out.println("Total Price: " + totalOrderPrice);
//        System.out.println("Thank you for visiting. Have a great day!");
//    }
//}