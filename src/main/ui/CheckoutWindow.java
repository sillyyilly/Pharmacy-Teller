package ui;

import model.Checkout;
import model.Inventory;
import model.Item;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class CheckoutWindow extends JPanel {

//    private final JFrame checkoutWindow;
    private JFrame frame = new JFrame("Checkout");

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

            String priceInDollars = String.format("$%.2f", t.getItemPrice() / 100.0);
            String displayInfo = (t.getItemName() + " " + priceInDollars);

            checkoutInventoryModel.addElement(displayInfo);

        }



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


    private void finalCheckout(ActionEvent e) {

        String totalOrderPrice = String.format("$%.2f", checkout.checkoutPrice() / 100.0);

        Image image = new ImageIcon("data/pills.jpg").getImage();
        ImageIcon icon2 = new ImageIcon(getScaledImage(image, 150, 150));


        JOptionPane.showMessageDialog(this, "Total Price: " + totalOrderPrice,
                "Checkout", JOptionPane.INFORMATION_MESSAGE, icon2);
        checkout = new Checkout();


    }

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
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

