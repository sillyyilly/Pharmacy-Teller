package ui;

import model.Checkout;
import model.Inventory;
import model.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Pharmacy extends JFrame {


    private Scanner input;
    private Inventory newInventory;
    private Checkout newCheckout;
    private static final String JSON_STORE = "./data/inventory.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    JButton addItemToInventoryButton;
    JButton printInventoryButton;
    JButton deleteItemsInInventoryButton;
    JButton checkoutButton;
    JButton saveInventoryButton;

    public Pharmacy() {
        super("Pharmacy");
        input = new Scanner(System.in);
        newInventory = new Inventory("inventoryName");
        newCheckout = new Checkout();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        loadInventory();

        setVisible(true);
        setSize(800, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        makeButtons();
        displayButtons();

    }

    public void makeButtons() {
        addItemToInventoryButton = new JButton();
        addItemToInventoryButton.setText("Add Item to Inventory");
        addItemToInventoryButton.addActionListener(event -> addItem(event));

        printInventoryButton = new JButton();
        printInventoryButton.setText("Print Items in Inventory");
        printInventoryButton.addActionListener(e -> printItems(e));

        saveInventoryButton = new JButton();
        saveInventoryButton.setText("Save Items in Inventory");
        saveInventoryButton.addActionListener(e -> saveInventory(e));

        deleteItemsInInventoryButton = new JButton();
        deleteItemsInInventoryButton.setText("Delete Items in Inventory");
        deleteItemsInInventoryButton.addActionListener(e -> deleteInventory(e));

        checkoutButton = new JButton();
        checkoutButton.setText("Checkout");

    }

    public void displayButtons() {

        JPanel pane = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        pane.add(addItemToInventoryButton, constraints);

        constraints.gridx = 1;
        pane.add(printInventoryButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(saveInventoryButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        pane.add(deleteItemsInInventoryButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        pane.add(checkoutButton, constraints);

        add(pane);

    }

    private void addItem(ActionEvent addEvent) {

        //item is being created when additemtoinventory button hit, not the button in the window

        AddItemWindow addItemWindow = new AddItemWindow();
        addItemWindow.run();
        Item newItem = new Item(addItemWindow.getItemName(), addItemWindow.getItemPrice());
        newInventory.addToInventory(newItem);
        System.out.println(newItem.getItemName());

    }

    private void printItems(ActionEvent printEvent) {
        JFrame printWindow = new JFrame("Inventory");
        printWindow.setSize(300, 600);
        printWindow.setVisible(true);
        printWindow.setDefaultCloseOperation(HIDE_ON_CLOSE);

        List<Item> itemsInInventory = newInventory.getInventoryItems();
        DefaultListModel inventoryList = new DefaultListModel();

        for (Item t : itemsInInventory) {
            inventoryList.addElement(t);
        }

        JList inventoryJList = new JList(inventoryList);
        JScrollPane listScrollPane = new JScrollPane(inventoryJList);

        printWindow.add(listScrollPane);

    }

    private void saveInventory(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(newInventory);
            jsonWriter.close();
            System.out.println("Saved " + newInventory.getInventoryName() + " to " + JSON_STORE);
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    private void deleteInventory(ActionEvent e) {
        newInventory = new Inventory("Store Inventory");
    }


//    public List<Item> getInventoryItems() {
//        java.util.List<Item> itemsInInventory = newInventory.getInventoryItems();
//        return itemsInInventory;
//    }



//    private ActionListener onItemAdded() {
//        return (event) -> {
//            addItemToInventoryButton.setText("Items " + ++pressed);
//        };
//    }
//}
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
    //EFFECTS: loads inventory
    private void loadInventory() {
        File data = new File(JSON_STORE);
        boolean exists = data.exists();

        try {
            if (exists) {
                newInventory = jsonReader.read();
            } else {
                newInventory = new Inventory("Inventory");
            }
            System.out.println("Loaded " + newInventory.getInventoryName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
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