package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order testOrder;
    private Item item1 = new Item("Advil", 1000);
    private Item item2= new Item("Tylenol", 1500);

    @BeforeEach
    void runBefore() {
        testOrder = new Order("test order");
    }

    @Test
        // if constructor assigns array list, size must be 0
    void testConstructorForOrder() {
        assertEquals(0, testOrder.getOrderSize());
    }

    @Test
    void testOrderPrice() {
        testOrder.addToOrder(item1);
        testOrder.addToOrder(item2);

        assertEquals(2500, testOrder.orderPrice());
    }

    @Test
    void testAddToOrder() {
        testOrder.addToOrder(item1);
        testOrder.addToOrder(item2);

        assertEquals(2, testOrder.getOrderSize());

    }


}