package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory implements Writable {

    // represents a list of items to be ordered

    private String inventoryName;
    private ArrayList<model.Item> itemsInInventory;


    // EFFECTS: creates empty arraylist
    public Inventory(String orderName) {
        this.inventoryName = orderName;
        itemsInInventory = new ArrayList<>();
    }

    public String getInventoryName() {
        return inventoryName;
    }

    //MODIFIES: this
    //EFFECTS: adds an item to the inventory
    public void addToInventory(model.Item item) {
        itemsInInventory.add(item);

    }

//    // MODIFIES: this
//    // EFFECTS: returns price of total order so far
//    public int orderPrice() {
//        int price  = 0;
//
//        for (model.Item item : itemsInInventory) {
//            price += item.getItemPrice();
//        }
//        return price;
//
//    }

    public int getInventorySize() {
        return itemsInInventory.size();
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Item> getInventoryItems() {
        return Collections.unmodifiableList(itemsInInventory);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("inventoryName", inventoryName);
        json.put("itemsInInventory", inventoryToJason());
        return json;
    }

    // EFFECTS: returns the inventory as a JSON array
    private JSONArray inventoryToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : itemsInInventory) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }



}
