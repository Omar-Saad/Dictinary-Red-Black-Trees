package com.omar.RBtrees;

public class RedBlackTree {

    private Node root;

    void insert(String data){
        Node newNode = new Node(data);
        root = insertToTree(root , newNode);
        fixInsertedNode(newNode);

    }


    private Node insertToTree(Node root, Node newNode) {
        if(root==null)
            return newNode;
        else if(root.getWordData().compareToIgnoreCase(newNode.getWordData()) > 0){
            root.setRightChild(insertToTree(root.getRightChild() , newNode));
            root.getRightChild().setParent(root);
        }
        else if(root.getWordData().compareToIgnoreCase(newNode.getWordData()) < 0){
            root.setLeftChild(insertToTree(root.getLeftChild() , newNode));
            root.getLeftChild().setParent(root);
        }

        return newNode;
    }

    private void fixInsertedNode(Node newNode) {
        Node parentNode , grandParentNode , uncle= null;

        while (newNode!=root && newNode.getColor() != Node.BLACK_COLOR
                && newNode.getParent().getColor() == Node.RED_COLOR)
        {
            parentNode = newNode.getParent();
            grandParentNode = newNode.getParent().getParent();

            if(parentNode == grandParentNode.getLeftChild()){
                uncle = grandParentNode.getRightChild();

                if(uncle!=null && uncle.getColor() == Node.RED_COLOR){
                    //case 1 =>> Recolor
                    recolor(grandParentNode , parentNode , uncle , newNode);

                }
                else { //Uncle is black
                    if(newNode == parentNode.getRightChild()){
                        //Case 2 Node is right child => Left rotate
                        rotateLeft(parentNode);
                        newNode = parentNode;
                        parentNode = newNode.getParent();
                    }

                    rotateRight(grandParentNode);
                    int tempColor = parentNode.getColor();
                    parentNode.setColor(grandParentNode.getColor());
                    grandParentNode.setColor(tempColor);
                    newNode = parentNode;

                }
            } else { //Parent is right child
                uncle = grandParentNode.getLeftChild();

                if(uncle!=null && uncle.getColor() == Node.RED_COLOR)
                    //case 1 =>> Recolor
                    recolor(grandParentNode , parentNode , uncle , newNode);
                else {
                    if(newNode == parentNode.getLeftChild()){
                        rotateRight(parentNode);
                        newNode = parentNode;
                        parentNode = newNode.getParent();
                    }
                    rotateLeft(grandParentNode);
                    int tempColor = parentNode.getColor();
                    parentNode.setColor(grandParentNode.getColor());
                    grandParentNode.setColor(tempColor);
                    newNode = parentNode;
                }

                }

        }

        if(root.getColor() != Node.BLACK_COLOR)
            root.setColor(Node.BLACK_COLOR);

    }

    private void recolor(Node grandParentNode , Node parentNode , Node uncle , Node newNode){
        //case 1 =>> Recolor
        uncle.setColor(Node.BLACK_COLOR);
        parentNode.setColor(Node.BLACK_COLOR);
        grandParentNode.setColor(Node.RED_COLOR);
        newNode = grandParentNode;
    }

    private void rotateRight(Node newNode){
        Node temp = newNode.getLeftChild();
        newNode.setLeftChild(temp.getRightChild());

        if (newNode.getLeftChild() != null)
        newNode.getLeftChild().setParent(newNode);

        temp.setParent(newNode.getParent());

        if (temp.getParent() ==null)
            root = temp;
        else if (newNode == newNode.getParent().getLeftChild())
            newNode.getParent().setLeftChild(temp);
        else
            newNode.getParent().setRightChild(temp);

        temp.setRightChild(newNode);
        newNode.setParent(temp);
    }

    private void rotateLeft(Node newNode){
        Node temp = newNode.getRightChild();
        newNode.setRightChild(temp.getLeftChild());

        if (newNode.getRightChild() != null)
            newNode.getRightChild().setParent(newNode);

        temp.setParent(newNode.getParent());

        if (temp.getParent() ==null)
            root = temp;
        else if (newNode == newNode.getParent().getLeftChild())
            newNode.getParent().setLeftChild(temp);
        else
            newNode.getParent().setRightChild(temp);

        temp.setLeftChild(newNode);
        newNode.setParent(temp);
    }

    boolean search(String data){
        /*TODO Search for string and return true if found */
        return true;
    }
    int getTreeHeight(){
        int height = 0;
        /*TODO return tree height*/
        return height;

    }

    int getTreeSize(){
        int size = 0;
        /*TODO return number of elements in the tree*/
        return size;

    }
}
