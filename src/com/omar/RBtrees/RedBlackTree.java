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
        else if(newNode.getWordData().compareToIgnoreCase(root.getWordData()) > 0){
            root.setRightChild(insertToTree(root.getRightChild() , newNode));
            root.getRightChild().setParent(root);
        }
        else if(newNode.getWordData().compareToIgnoreCase(root.getWordData()) < 0){
            root.setLeftChild(insertToTree(root.getLeftChild() , newNode));
            root.getLeftChild().setParent(root);
        }

        return root;
    }


    public void fixInsertedNode(Node newNode) {
        Node uncle = null;

        if (root == newNode) {
            root.setColor( Node.BLACK_COLOR);
        }
        else if (newNode.getParent().getColor() != Node.BLACK_COLOR) {

            if (newNode.getParent().getParent() != null) {

                if (newNode.getParent().getParent().getLeftChild() == newNode.getParent()) {
                    uncle = newNode.getParent().getParent().getRightChild();
                } else
                    uncle = newNode.getParent().getParent().getLeftChild();
            }
            if (newNode.getColor() == newNode.getParent().getColor()) { // parent and child red

                if (uncle != null) { // case 1 or 2 or 3

                    if (newNode.getColor() == uncle.getColor()) { //case 1 uncle is red
                        newNode.getParent().setColor( Node.BLACK_COLOR);
                        uncle.setColor(Node.BLACK_COLOR);
                        newNode.getParent().getParent().setColor(Node.RED_COLOR);
                        fixInsertedNode(newNode.getParent().getParent());
                    } else {
                        if(newNode.getParent() == newNode.getParent().getParent().getLeftChild()) {

                            if (newNode == newNode.getParent().getRightChild())// case 2 =>rotate left
                            {
                                newNode = newNode.getParent();
                                rotateLeft(newNode);
                            }
                            newNode.getParent().setColor(Node.BLACK_COLOR);
                            newNode.getParent().getParent().setColor(Node.RED_COLOR);
                            rotateRight(newNode.getParent().getParent());
                        }
                        else if (newNode.getParent() == newNode.getParent().getParent().getRightChild()){ //case 2 => rotate right

                            if ( newNode == newNode.getParent().getLeftChild() ) {
                                newNode = newNode.getParent();
                                rotateRight(newNode);
                            }

                            // recolor than rotate left
                            newNode.getParent().setColor(Node.BLACK_COLOR);
                            newNode.getParent().getParent().setColor(Node.RED_COLOR);
                            rotateLeft(newNode.getParent().getParent());
                        }
                    }

                } else { //Uncle is null => Uncle is black (NILL)
                    if ((newNode.getParent() == newNode.getParent().getParent().getLeftChild()))
                    {
                        if(newNode == newNode.getParent().getRightChild()){
                            newNode = newNode.getParent();
                            rotateLeft(newNode);
                        }

                        newNode.getParent().setColor(Node.BLACK_COLOR);
                        newNode.getParent().getParent().setColor(Node.RED_COLOR);
                        rotateRight(newNode.getParent().getParent());
                    } else if(newNode.getParent() == newNode.getParent().getParent().getRightChild()){

                        if ( newNode == newNode.getParent().getLeftChild() ) {
                            newNode = newNode.getParent();
                            rotateRight(newNode);
                        }

                        newNode.getParent().setColor(Node.BLACK_COLOR);
                        newNode.getParent().getParent().setColor(Node.RED_COLOR);
                        rotateLeft(newNode.getParent().getParent());
                    }
                }
            }
        }
        root.setColor(Node.BLACK_COLOR);
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

    boolean search(Node root , String data){
        if(root==null){
            return false;
        }
        if(data.compareToIgnoreCase(root.getWordData())==0 ){
            return true;
        }
        else if(data.compareToIgnoreCase(root.getWordData())>0 ) {
            return  search(root.getRightChild(),data);
        }
        else if (data.compareToIgnoreCase(root.getWordData())<0 ){
            return search(root.getLeftChild(),data);
        }
        return false;


    }
    int getTreeHeight(Node root){
        if (root == null) return -1;
        else
            return  1 + Math.max(getTreeHeight(root.getLeftChild()), getTreeHeight(root.getRightChild()));

    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }


}
