package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.UUID;

public class Item implements Writable {

    // represents and item with an id, name, and price

    private static int nextItemId = 1;  // tracks id of next account created
    private final int itemId;        // item id
    private final String itemName;      //  item name
    private int itemPrice;        // price in cents


    //REQUIRES: itemName has non-zero length
    //EFFECTS: name on item is set to itemName; item id is a
    //         positive integer not assigned to any other item;
    //         item price is a positive integer in cents;


    public Item(String itemName, int itemPrice) {
        this.itemId = nextItemId++;
        this.itemName = itemName;
        this.itemPrice = itemPrice;

    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("itemId", itemId);
        json.put("itemName", itemName);
        json.put("itemPrice", itemPrice);
        return json;
    }


}

