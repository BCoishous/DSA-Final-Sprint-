package com.warehouse.warehouse_system;

import com.warehouse.warehouse_system.bst.OrderBST;
import com.warehouse.warehouse_system.model.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderBSTTest {

    private Order makeOrder(int priority) {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setPriorityLevel(priority);
        return order;
    }

    @Test
    void inorderTraversalReturnsSortedPriorities() {
        // Arrange
        OrderBST tree = new OrderBST();
        tree.insert(makeOrder(5));
        tree.insert(makeOrder(3));
        tree.insert(makeOrder(8));
        tree.insert(makeOrder(1));
        tree.insert(makeOrder(4));

        // Act
        List<Order> result = tree.inorder();

        // Assert
        List<Integer> priorities = result.stream()
                .map(Order::getPriorityLevel)
                .toList();
        assertEquals(List.of(1, 3, 4, 5, 8), priorities);
    }

    @Test
    void findHighestAndLowestReturnCorrectExtremes() {
        // Arrange
        OrderBST tree = new OrderBST();
        tree.insert(makeOrder(5));
        tree.insert(makeOrder(3));
        tree.insert(makeOrder(8));
        tree.insert(makeOrder(1));

        // Act
        Order highest = tree.findHighest();
        Order lowest = tree.findLowest();

        // Assert
        assertEquals(8, highest.getPriorityLevel());
        assertEquals(1, lowest.getPriorityLevel());
    }

    @Test
    void duplicatePrioritiesAreHandledWithoutError() {
        // Arrange
        OrderBST tree = new OrderBST();
        tree.insert(makeOrder(5));
        tree.insert(makeOrder(5));
        tree.insert(makeOrder(5));

        // Act
        List<Order> result = tree.inorder();

        // Assert
        assertEquals(3, result.size());
        assertEquals(5, tree.findHighest().getPriorityLevel());
        assertEquals(5, tree.findLowest().getPriorityLevel());
    }
}