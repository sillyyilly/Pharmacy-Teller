package model;

import org.junit.jupiter.api.Test;

import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            Inventory order = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyOrder() {
        JsonReader testReader = new JsonReader("./data/testReaderEmptyOrder.json");
        try {
           Inventory order = testReader.read();
            assertEquals("test empty order", order.getInventoryName());
            assertEquals(0, order.getInventorySize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNormalOrder() {
        JsonReader reader = new JsonReader("./data/testReaderNormalOrder.json");
        try {
            Inventory order = reader.read();
            assertEquals("test normal order", order.getInventoryName());
            List<Item> listOfItems = order.getInventoryItems();
            assertEquals(2, listOfItems.size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
