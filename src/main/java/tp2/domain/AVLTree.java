package tp2.domain;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    private class Node {
        String key;
        StaticVehicleData staticData;
        List<TripData> tripDataList;
        Node left;
        Node right;
        int height;

        public Node(String key, StaticVehicleData staticData, List<TripData> tripDataList) {
            this.key = key;
            this.staticData = staticData;
            this.tripDataList = tripDataList;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

    }

    private Node root;

    public void insert(String key, StaticVehicleData staticData, List<TripData> tripDataList) {
        root = insert(root, key, staticData, tripDataList);
    }

    private Node insert(Node node, String key, StaticVehicleData staticData, List<TripData> tripDataList) {
        if (node == null) {
            return new Node(key, staticData, tripDataList);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, staticData, tripDataList);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, staticData, tripDataList);
        } else {
            // Key already exists, add trip data to the list
            node.tripDataList.addAll(tripDataList);
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1) {
            if (key.compareTo(node.left.key) < 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        } else if (balance < -1) {
            if (key.compareTo(node.right.key) > 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }


    public List<TripData> getTripsForVehId(String key) {
        Node node = search(root, key);
        if (node != null) {
            return node.tripDataList;
        }
        return null;
    }

    private Node search(Node node, String key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return search(node.left, key);
        } else if (cmp > 0) {
            return search(node.right, key);
        } else {
            return node;
        }
    }

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }


    // Outros métodos para AVL tree (excluindo, percorrendo, etc.) podem ser adicionados aqui.




}
