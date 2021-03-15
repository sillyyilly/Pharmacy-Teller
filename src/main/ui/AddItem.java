package ui;

import model.Item;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AddItem extends JFrame {

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


    public AddItem(ActionListener itemAdded) {
        setSize(800, 450);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(100, 30);

        JButton pressed = new JButton();
        pressed.addActionListener((event) -> {
            itemAdded.actionPerformed(event);
            setVisible(false);
        });
        add(pressed);



    }
}
