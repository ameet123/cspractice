package com.ameet.cspractice.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int id;
    public boolean isVisited = false;
    private Node left;
    private Node right;
    private int value;
    private List<Node> myChildren = new ArrayList<>();

    public Node(int id, Node left, Node right) {
        this.id = id;
        this.left = left;
        this.right = right;
        myChildren = new ArrayList<Node>() {
            {
                add(left);
                add(right);
            }
        };
    }

    public Node(int id, Node left, Node right, int value) {
        this.id = id;
        this.value = value;
        this.left = left;
        this.right = right;
        myChildren = new ArrayList<Node>() {
            {
                add(left);
                add(right);
            }
        };
    }

    public Node(int id) {
        this.id = id;
    }

    public Node(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
        myChildren = new ArrayList<Node>() {
            {
                add(left);
                add(right);
            }
        };
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
        myChildren = new ArrayList<Node>() {
            {
                add(left);
                add(right);
            }
        };
    }

    public List<Node> children() {
        return myChildren;
    }

    @Override
    public String toString() {
        return id +"["+ value+"]"+
                ":(" + (left == null ? "_" : left.id) + ", " + (right == null ? "_" : right.id) + ")";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
