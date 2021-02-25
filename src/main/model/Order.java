package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Order implements Writable {

    // represents a list of items to be ordered

    private ArrayList<model.Item> itemsOrdered;


    // EFFECTS: creates empty arraylist
    public Order() {
        itemsOrdered = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds an item to the list
    public void addToOrder(model.Item item) {
        itemsOrdered.add(item);

    }

    // MODIFIES: this
    // EFFECTS: returns price of total order so far 
    public int orderPrice() {
        int price  = 0;

        for (model.Item item : itemsOrdered) {
            price += item.getItemPrice();
        }
        return price;

    }

    public int getOrderSize() {
        return itemsOrdered.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("itemsOrdered", itemsOrdered);
        return json;
    }

    // EFFECTS: returns the order as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : itemsOrdered) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }



}
