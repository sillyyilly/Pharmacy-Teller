package ui;

import model.Inventory;
import model.Item;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.text.*;

/**
 * FormattedTextFieldDemo.java requires no other files.
 *
 * It implements a mortgage calculator that uses four
 * JFormattedTextFields.
 */
public class AddItemWindow extends JPanel
        implements PropertyChangeListener {

    private JFrame frame = new JFrame("Add Item");

    private JButton addButton;
    //Values for the fields
    private String name = new String();

    private int numPrice = 0;

    //Labels to identify the fields
    private JLabel itemNameLabel;
    private JLabel itemPriceLabel;

    //Strings for the labels
    private static String nameString = "Item Name: ";
    private static String priceString = "Item Price: ";

    //Fields for data entry
    private JFormattedTextField itemNameField;
    private JFormattedTextField itemPriceField;

    //Formats to format and parse numbers
    private NumberFormat priceFormat;
    private String itemName;
    private int itemPrice;

    private Inventory inventory;

    //EFFECTS: creates window where users can input item name and price and add the item to inventory
    public AddItemWindow(Inventory inventory) {
        this.inventory = inventory;
        setUpFormats();

        //Create the labels.
        itemNameLabel = new JLabel(nameString);
        itemPriceLabel = new JLabel(priceString);

        //Create the text fields and set them up.
        itemNameField = new JFormattedTextField();
        itemNameField.setValue(name);
        itemNameField.setColumns(10);
        itemNameField.addPropertyChangeListener("value", this);


        itemPriceField = new JFormattedTextField(priceFormat);
        itemPriceField.setValue(new Integer(numPrice));
        itemPriceField.setColumns(10);
        itemPriceField.addPropertyChangeListener("value", this);

        makeTextBox();
    }

    //EFFECTS: Creates a formatted text box with fields for item name and item price
    public void makeTextBox() {

        //Lay out the labels in a panel.
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(itemNameLabel);
        labelPane.add(itemPriceLabel);

        //Layout the text fields in a panel.
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(itemNameField);
        fieldPane.add(itemPriceField);

        //Make add button
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        addButton = new JButton();
        addButton.setText("Add Item");
        buttonPanel.add(addButton);
        addButton.addActionListener(e -> addAction(e));


        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(buttonPanel, BorderLayout.PAGE_END);



    }

    //MODIFIES: this
    //EFFECTS: creates item with given item parameters and adds item to inventory
    public void addAction(ActionEvent e) {
        itemName = ((String) itemNameField.getValue());
        itemPrice = (int) (((Number) itemPriceField.getValue()).doubleValue() * 100);
        Item addedItem = new Item(itemName, itemPrice);
        this.inventory.addToInventory(addedItem);
        itemNameField.setValue(null);
        itemPriceField.setValue(0);
    }

    // EFFECTS: Called when a field's "value" property changes
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == itemNameField) {
            itemName = ((String) itemNameField.getValue());
        } else if (source == itemPriceField) {
            itemPrice = ((Number) itemPriceField.getValue()).intValue();
        }

    }

    // EFFECTS: Create the GUI and show it
    private void createAndShowGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Add contents to the window.
        frame.add(new AddItemWindow(this.inventory));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: runs the window
    public void run() {
        //Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        createAndShowGUI();
    }

    //EFFECTS: Create and set up number formats. These objects also parse numbers input by user.
    private void setUpFormats() {
        priceFormat = NumberFormat.getNumberInstance();
    }
}