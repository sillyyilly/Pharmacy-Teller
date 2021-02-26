package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order implements Writable {

    // represents a list of items to be ordered

    private String orderName;
    private ArrayList<model.Item> itemsOrdered;


    // EFFECTS: creates empty arraylist
    public Order(String orderName) {
        this.orderName = orderName;
        itemsOrdered = new ArrayList<>();
    }

    public String getOrderName() {
        return orderName;
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

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Item> getOrderItems() {
        return Collections.unmodifiableList(itemsOrdered);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("orderName", orderName);
        json.put("itemsOrdered", itemsOrderedToJson());
        return json;
    }

    // EFFECTS: returns the order as a JSON array
    private JSONArray itemsOrderedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : itemsOrdered) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }



}
