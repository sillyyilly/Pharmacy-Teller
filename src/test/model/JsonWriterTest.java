package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Order order = new Order("test order");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyOrder() {
        try {
            Order order = new Order("test order");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOrder.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOrder.json");
            order = reader.read();
            assertEquals("test order", order.getOrderName());


        } catch (IOException e) {
            fail("Exception should not have been thrown");

        }
    }

    @Test
    void testWriterNormalOrder() {
        try {
            Order order = new Order("test order");
            order.addToOrder(new Item("Advil", 1000));
            order.addToOrder(new Item("Tylenol", 1500));
            JsonWriter writer = new JsonWriter("./data/testWriterNormalOrder.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalOrder.json");
            order = reader.read();
            assertEquals("test order", order.getOrderName());
            List<Item> listOfItems = order.getOrderItems();
            assertEquals(2, listOfItems.size());

            assertEquals(listOfItems.get(0).getItemName(), "Advil");
            assertEquals(listOfItems.get(0).getItemPrice(), 1000);

            assertEquals(listOfItems.get(1).getItemName(), "Tylenol");
            assertEquals(listOfItems.get(1).getItemPrice(), 1500);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
