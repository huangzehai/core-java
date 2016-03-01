package com.hzh.corejava.datastructure;

/**
 * Created by Adam on 2016/3/1.
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree bTree=new BinaryTree();
        bTree.insert(30);
        bTree.insert(67);
        bTree.insert(98);
        bTree.insert(256);
        bTree.insert(5);

        bTree.lookup(2);

    }
}
