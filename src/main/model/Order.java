package model;

import java.util.ArrayList;

public class Order {

    // list of items to be ordered, returns price

    private ArrayList<model.Item> itemsOrdered;

    public Order() {
        itemsOrdered = new ArrayList<>();
    }

    public void addToOrder(model.Item item) {
        itemsOrdered.add(item);

    }

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



}
