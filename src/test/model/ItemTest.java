package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private Item testItem;

    @BeforeEach
    void runBefore() {
        testItem = new Item("Advil", 1000);
    }

    @Test
    void testConstructor() {
        assertEquals("Advil", testItem.getItemName());
        assertEquals(1000, testItem.getItemPrice());
        assertTrue(testItem.getItemId() > 0);
    }

}