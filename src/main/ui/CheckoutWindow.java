package ui;

import model.Inventory;

import javax.swing.*;

public class CheckoutWindow {

    private JList checkoutInventory;
    private DefaultListModel checkoutInventoryModel;
    private Inventory inventory;

    public void checkoutWindow(Inventory inventory) {
        this.inventory = inventory;

    }

}
