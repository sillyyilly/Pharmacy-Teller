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
    private Inventory storeInventory;
    private Checkout newCheckout;
    private static final String JSON_STORE = "./data/inventory.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    JButton addItemToInventoryButton;
    JButton printInventoryButton;
    JButton deleteItemsInInventoryButton;
    JButton checkoutButton;
    JButton saveInventoryButton;


    //EFFECTS: creates a pharmacy window where users can add, save, delete, and print items from an inventory
    //and select items to checkout
    public Pharmacy() {
        super("Pharmacy");
        input = new Scanner(System.in);
        storeInventory = new Inventory("inventoryName");
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


    //EFFECTS: creates buttons for pharmacy window
    public void makeButtons() {
        addItemToInventoryButton = new JButton();
        addItemToInventoryButton.setText("Add Item to Inventory");
        addItemToInventoryButton.addActionListener(event -> addItem(event));
        addItemToInventoryButton.setPreferredSize(new Dimension(200, 30));

        printInventoryButton = new JButton();
        printInventoryButton.setText("Print Items in Inventory");
        printInventoryButton.addActionListener(e -> printItems(e));
        printInventoryButton.setPreferredSize(new Dimension(200, 30));

        saveInventoryButton = new JButton();
        saveInventoryButton.setText("Save Items in Inventory");
        saveInventoryButton.addActionListener(e -> saveInventory(e));
        saveInventoryButton.setPreferredSize(new Dimension(200, 30));

        deleteItemsInInventoryButton = new JButton();
        deleteItemsInInventoryButton.setText("Delete Items in Inventory");
        deleteItemsInInventoryButton.addActionListener(e -> deleteInventory(e));
        deleteItemsInInventoryButton.setPreferredSize(new Dimension(200, 30));

        checkoutButton = new JButton();
        checkoutButton.setText("Checkout");
        checkoutButton.addActionListener(e -> checkoutWindow(e));
        checkoutButton.setPreferredSize(new Dimension(200, 30));

    }

    //EFFECTS: displays buttons in the window
    public void displayButtons() {

        JPanel buttonPane = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        buttonPane.add(addItemToInventoryButton, constraints);

        constraints.gridx = 1;
        buttonPane.add(printInventoryButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        buttonPane.add(saveInventoryButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        buttonPane.add(deleteItemsInInventoryButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        buttonPane.add(checkoutButton, constraints);

        add(buttonPane);

    }

    // MODIFIES: this
    // EFFECTS: makes new add item window
    private void addItem(ActionEvent addEvent) {

        AddItemWindow addItemWindow = new AddItemWindow(storeInventory);
        addItemWindow.run();

    }

    // EFFECTS: prints out all items in inventory in new window
    private void printItems(ActionEvent printEvent) {
        JFrame printWindow = new JFrame("Inventory");
        printWindow.setSize(300, 600);
        printWindow.setVisible(true);
        printWindow.setDefaultCloseOperation(HIDE_ON_CLOSE);

        List<Item> itemsInInventory = storeInventory.getInventoryItems();
        DefaultListModel inventoryList = new DefaultListModel();

        for (Item t : itemsInInventory) {

            String indexNumber = String.valueOf(itemsInInventory.indexOf(t));
            String priceInDollars = String.format("$%.2f", t.getItemPrice() / 100.0);
            String displayInfo = ("[" + indexNumber + "]" + " " + t.getItemName() + " " + priceInDollars);

            inventoryList.addElement(displayInfo);
        }


        JList inventoryJList = new JList(inventoryList);
        JScrollPane listScrollPane = new JScrollPane(inventoryJList);

        printWindow.add(listScrollPane);

    }

    //EFFECTS: saves items added to inventory
    private void saveInventory(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(storeInventory);
            jsonWriter.close();
            System.out.println("Saved " + storeInventory.getInventoryName() + " to " + JSON_STORE);
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    //MODIFIES: this
    //EFFECTS: deletes all items in inventory
    private void deleteInventory(ActionEvent e) {
        storeInventory = new Inventory("Store Inventory");
    }

    //EFFECTS: Makes new checkout window
    private void checkoutWindow(ActionEvent e) {
        CheckoutWindow mainCheckoutWindow = new CheckoutWindow(storeInventory, newCheckout);
        mainCheckoutWindow.run();

    }

    //EFFECTS: loads inventory
    private void loadInventory() {
        File data = new File(JSON_STORE);
        boolean exists = data.exists();

        try {
            if (exists) {
                storeInventory = jsonReader.read();
            } else {
                storeInventory = new Inventory("Inventory");
            }
            System.out.println("Loaded " + storeInventory.getInventoryName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
