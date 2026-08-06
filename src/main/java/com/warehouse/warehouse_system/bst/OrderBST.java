package com.warehouse.warehouse_system.bst;

import com.warehouse.warehouse_system.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderBST {

    private OrderNode root;

    private static class OrderNode {
        Order data;
        OrderNode left;
        OrderNode right;

        OrderNode(Order data) {
            this.data = data;
        }
    }

    public void insert(Order order) {
        root = insertRecursive(root, order);
    }

    private OrderNode insertRecursive(OrderNode current, Order order) {
        if (current == null) {
            return new OrderNode(order);
        }

        if (order.getPriorityLevel() < current.data.getPriorityLevel()) {
            current.left = insertRecursive(current.left, order);
        } else {
            // equal or greater priority goes right (duplicates go right)
            current.right = insertRecursive(current.right, order);
        }

        return current;
    }

    public List<Order> inorder() {
        List<Order> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive(OrderNode node, List<Order> result) {
        if (node == null) return;
        inorderRecursive(node.left, result);
        result.add(node.data);
        inorderRecursive(node.right, result);
    }

    public Order findHighest() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        OrderNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    public Order findLowest() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        OrderNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }
}