package ui;

import java.awt.*;

import javax.swing.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.text.*;

/**
 * FormattedTextFieldDemo.java requires no other files.
 *
 * It implements a mortgage calculator that uses four
 * JFormattedTextFields.
 */
public class AddItem extends JPanel
        implements PropertyChangeListener {
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

    public AddItem() {
        super(new BorderLayout());
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

        //Tell accessibility tools about label/textfield pairs.
        itemNameLabel.setLabelFor(itemNameField);
        itemPriceLabel.setLabelFor(itemPriceField);

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
        addButton.setActionCommand("make item");
        buttonPanel.add(addButton);


        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(buttonPanel, BorderLayout.PAGE_END);



    }


    /** Called when a field's "value" property changes. */
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == itemNameField) {
            name = ((String) itemNameField.getValue());
        } else if (source == itemPriceField) {
            numPrice = ((Number) itemPriceField.getValue()).intValue();
        } else if (source == addButton) {
            name = ((String) itemNameField.getValue());
            numPrice = ((Number) itemPriceField.getValue()).intValue();

        }

    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Add Item");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new AddItem());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }

    //Create and set up number formats. These objects also
    //parse numbers input by user.
    private void setUpFormats() {
        priceFormat = NumberFormat.getNumberInstance();
    }
}
