package ui;

import model.Checkout;
import model.Inventory;
import model.Item;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CheckoutWindow extends JPanel {

//    private final JFrame checkoutWindow;
    private JFrame frame = new JFrame("Checkout");
    //TODO: making two windows , merge

    private JList checkoutInventory;
    private DefaultListModel checkoutInventoryModel;
    private Inventory inventory;
    private JButton addToCheckoutButton;
    private Checkout checkout;
    private int selectedItemIndex;
    private JButton checkoutButton;
    private JScrollPane checkoutScrollPane;
    private Item selectedItem;

    public CheckoutWindow(Inventory inventory, Checkout checkout) {
        this.inventory = inventory;
        this.checkout = checkout;

        checkoutInventoryModel = new DefaultListModel();

        List<Item> inventoryList = inventory.getInventoryItems();
        for (Item t : inventoryList) {
//            checkoutInventoryModel.addElement(t);
            // store data differently?
//            String indexNumber = String.valueOf(inventoryList.indexOf(t));
            String priceInDollars = String.format("$%.2f", t.getItemPrice() / 100.0);
            String displayInfo = (t.getItemName() + " " + priceInDollars);

            checkoutInventoryModel.addElement(displayInfo);

        }

//        private void printItems(ActionEvent printEvent) {
//            JFrame printWindow = new JFrame("Inventory");
//            printWindow.setSize(300, 600);
//            printWindow.setVisible(true);
//            printWindow.setDefaultCloseOperation(HIDE_ON_CLOSE);
//
//            List<Item> itemsInInventory = storeInventory.getInventoryItems();
//            DefaultListModel inventoryList = new DefaultListModel();
//
//            for (Item t : itemsInInventory) {
//
//                String indexNumber = String.valueOf(itemsInInventory.indexOf(t));
//                String priceInDollars = String.format("$%.2f", t.getItemPrice() / 100.0);
//                String displayInfo = ("[" + indexNumber + "]" + " " + t.getItemName() + " " + priceInDollars);
//
//                inventoryList.addElement(displayInfo);
//            }
//
//
//            JList inventoryJList = new JList(inventoryList);
//            JScrollPane listScrollPane = new JScrollPane(inventoryJList);
//
//            printWindow.add(listScrollPane);
//
//        }


//        checkoutInventoryModel.addElement("object 1");
//        checkoutInventoryModel.addElement("object 2");

        checkoutInventory = new JList(checkoutInventoryModel);
        checkoutInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        checkoutInventory.setSelectedIndex(0);
        checkoutInventory.addListSelectionListener(e -> selectItem(e));
        checkoutInventory.setVisibleRowCount(10);
        checkoutScrollPane = new JScrollPane(checkoutInventory);

        makeCheckoutComponents();
        displayCheckoutWindow();

    }

    private void makeCheckoutComponents() {

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        addToCheckoutButton = new JButton();
        addToCheckoutButton.setText("Add to Checkout");
        buttonPanel.add(addToCheckoutButton);
        addToCheckoutButton.addActionListener(e -> addToCheckout(e));

        JPanel checkoutButtonPanel = new JPanel(new GridLayout(0, 1));
        checkoutButton = new JButton();
        checkoutButton.setText("Checkout");
        checkoutButtonPanel.add(checkoutButton);
        checkoutButton.addActionListener(e -> finalCheckout(e));
    }

    private void displayCheckoutWindow() {
        JPanel checkoutPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        checkoutPanel.add(checkoutScrollPane);

        constraints.gridx = 0;
        constraints.gridy = 1;
        checkoutPanel.add(addToCheckoutButton);

        constraints.gridx = 1;
        constraints.gridy = 1;
        checkoutPanel.add(checkoutButton);

        add(checkoutPanel);


    }
// index of selection selects index of item list, adds item to checkout

    private void selectItem(ListSelectionEvent e) {
        selectedItemIndex = checkoutInventory.getSelectedIndex();
//        System.out.println("item selected");
    }

    private void addToCheckout(ActionEvent e) {
        selectedItem = inventory.getInventoryItems().get(selectedItemIndex);
//        System.out.println(selectedItem.getItemName());
        checkout.addToCheckout(selectedItem);
//        System.out.println("added Item");
    }

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

    private void finalCheckout(ActionEvent e) {
//        JFrame totalPriceFrame = new JFrame();
//        totalPriceFrame.setSize(500, 300);
//        totalPriceFrame.setVisible(true);
//        totalPriceFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);

        String totalOrderPrice = String.format("$%.2f", checkout.checkoutPrice() / 100.0);
//        List<Item> itemsInCheckout = checkout.getCheckoutItems();
//        for (Item c : itemsInCheckout) {
//            String priceInDollars = String.format("$%.2f", c.getItemPrice() / 100.0);
//        }

        JOptionPane.showMessageDialog(this, "Total Price: " + totalOrderPrice);
        checkout = new Checkout();


    }

    private void createAndShowGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Add contents to the window.
        frame.add(new CheckoutWindow(this.inventory, this.checkout));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void run() {
        //Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        createAndShowGUI();
    }

}

