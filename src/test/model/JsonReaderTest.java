package model;

import org.junit.jupiter.api.Test;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            Order order = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyOrder() {
        JsonReader testReader = new JsonReader("./data/testReaderEmptyOrder.json");
        try {
           Order order = testReader.read();
            assertEquals("test empty order", order.getOrderName());
            assertEquals(0, order.getOrderSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNormalOrder() {
        JsonReader reader = new JsonReader("./data/testReaderNormalOrder.json");
        try {
            Order order = reader.read();
            assertEquals("test normal order", order.getOrderName());
            List<Item> listOfItems = order.getOrderItems();
            assertEquals(2, listOfItems.size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
