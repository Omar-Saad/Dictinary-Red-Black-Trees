package com.omar.RBtrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Dictionary Red Black Tree Project ");
        //Load to file to RB Tree (initialization)
        int size = 0;
       RedBlackTree tree = new RedBlackTree();
        size = LoadFromFileToRB("EN-US-Dictionary.txt" , tree);
        System.out.println("Dictionary Loaded successfully");
        System.out.println("Size of the tree after Load : "+size);
        System.out.println("Height of the tree after Load : "+tree.getTreeHeight()); //TODO



        int choice=0;
        boolean isEnd = false;

        while (!isEnd) {
            System.out.println("\nChoose one of these functions : ");
            choice = 0;
            System.out.println("1 - print Dictionary Size");
            System.out.println("2 - print Dictionary Height");
            System.out.println("3 - Insert a word into the Dictionary");
            System.out.println("4 - Look-up a word from the Dictionary");
            System.out.println("5 - End the program");
            System.out.print("Enter your choice : ");
            try {
                choice = new Scanner(System.in).nextInt();


            }
            catch (Exception e){
                System.out.println("Wrong Input please try again !");
                continue;

            }

                switch (choice){
                    case 1:
                        System.out.println("Size of the tree is : "+size);
                        break;
                    case 2:
                        System.out.println("Height of the tree is : "+tree.getTreeHeight());
                        break;
                    case 3:
                        System.out.print("Enter Word you want to insert : ");
                        String word = new Scanner(System.in).nextLine();
                        if(insertWordToRB(word , tree))
                            System.out.println("Word Inserted Successfully ");
                        else {
                            System.out.println("ERROR: Word already in the dictionary!");

                        }
                        break;
                    case 4:
                        System.out.print("Enter Word you want to Look-up : ");
                        String searchWord = new Scanner(System.in).nextLine();
                        if(tree.search(tree.getRoot(),searchWord))
                            System.out.println("YES Word is found ");
                        else {
                            System.out.println("NO Word is not found");

                        }
                        break;
                    case 5 :
                        System.out.println("End !");
                        isEnd = true;
                        break;
                    default:
                    System.out.println("Wrong choice please try again !");
                     break;

            }

        }





    }


    private static int LoadFromFileToRB(String fileNmae, RedBlackTree tree) {
        int count = 0;
        File file = new File(fileNmae);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tree.insert(scanner.nextLine());
                count ++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    private static boolean insertWordToRB(String data , RedBlackTree tree){
        if(!tree.search(tree.getRoot(),data)) {
            tree.insert(data);
            return true;
        }
        else
            return false;

    }
}
