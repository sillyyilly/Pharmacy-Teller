package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Inventory testOrder;
    private Item item1 = new Item("Advil", 1000);
    private Item item2= new Item("Tylenol", 1500);

    @BeforeEach
    void runBefore() {
        testOrder = new Inventory("test order");
    }

    @Test
        // if constructor assigns array list, size must be 0
    void testConstructorForOrder() {
        assertEquals(0, testOrder.getInventorySize());
    }

//    @Test
//    void testOrderPrice() {
//        testOrder.addToInventory(item1);
//        testOrder.addToInventory(item2);
//
//        assertEquals(2500, testOrder.orderPrice());
//    }

    @Test
    void testAddToOrder() {
        testOrder.addToInventory(item1);
        testOrder.addToInventory(item2);

        assertEquals(2, testOrder.getInventorySize());

    }


}