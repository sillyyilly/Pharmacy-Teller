package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Checkout {
    private ArrayList<Item> itemsInCheckout;

    public Checkout() {
        itemsInCheckout = new ArrayList<>();
    }

    public void addToCheckout(Item item) {
        itemsInCheckout.add(item);
    }

    public int checkoutPrice() {
        int price = 0;

        for (Item item : itemsInCheckout) {
            price += item.getItemPrice();
        }
        return price;
    }

    public List<Item> getCheckoutItems() {
        return Collections.unmodifiableList(itemsInCheckout);
    }
}
