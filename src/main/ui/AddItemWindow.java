package ui;

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


    //TODO: access inventory in pharmacy class, add item w/ item name/price value to inventory each time
    // Add button is clicked

    public AddItemWindow() {

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

    public void addAction(ActionEvent e) {
        itemName = ((String) itemNameField.getValue());
        itemPrice = ((Number) itemPriceField.getValue()).intValue();
        frame.setVisible(false);
        frame.dispose();
        itemNameField.setValue(null);
        itemPriceField.setValue(0);
    }

    /** Called when a field's "value" property changes. */
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == itemNameField) {
            itemName = ((String) itemNameField.getValue());
        } else if (source == itemPriceField) {
            itemPrice = ((Number) itemPriceField.getValue()).intValue();
        }

    }

//    public void actionPerformed(ActionEvent e) {
//        if ("add item".equals(e.getActionCommand())) {
//            itemName = ((String) itemNameField.getValue());
//            itemPrice = ((Number) itemPriceField.getValue()).intValue();
//            frame.setVisible(false);
//            frame.dispose();
//            name = null;
//            numPrice = 0;
//        }
//    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Add contents to the window.
        frame.add(new AddItemWindow());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void run() {
        //Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        createAndShowGUI();
    }
//
//    public static void main(String[] args) {
//        //Schedule a job for the event dispatch thread:
//        //creating and showing this application's GUI.
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                //Turn off metal's use of bold fonts
//                UIManager.put("swing.boldMetal", Boolean.FALSE);
//                createAndShowGUI();
//            }
//        });
//    }

    //Create and set up number formats. These objects also
    //parse numbers input by user.
    private void setUpFormats() {
        priceFormat = NumberFormat.getNumberInstance();
    }
}
//    public AddItem(ActionListener itemAdded) {
//        setSize(800, 450);
//        setVisible(true);
//        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setSize(100, 30);
//
//        JButton pressed = new JButton();
//        pressed.addActionListener((event) -> {
//            itemAdded.actionPerformed(event);
//            setVisible(false);
//        });
//        add(pressed);