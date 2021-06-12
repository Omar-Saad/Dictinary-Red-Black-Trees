package com.omar.RBtrees;

public class Node {
   private Node parent , rightChild , leftChild;
   private String wordData;
   private int color;
   final int BLACK_COLOR = 0;
   final int RED_COLOR = 1;

    public Node(String wordData) {
        this.wordData = wordData;
        this.color = RED_COLOR;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public String getWordData() {
        return wordData;
    }

    public void setWordData(String wordData) {
        this.wordData = wordData;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
