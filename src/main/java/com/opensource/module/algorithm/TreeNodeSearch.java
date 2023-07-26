package com.opensource.module.algorithm;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/22 16:57
 * @Version V1.0
 */
public class TreeNodeSearch {


    void printTreePreOrder(TreeNode tree){
        if(tree != null){
            System.out.println(tree.value);
            printTreePreOrder(tree.left);
            printTreePreOrder(tree.right);
        }
    }

    void printTreeMidOrder(TreeNode tree){
        if(tree != null){
            printTreeMidOrder(tree.left);
            System.out.println(tree.value);
            printTreeMidOrder(tree.right);
        }
    }


    void printTreePostOrder(TreeNode tree){
        if(tree != null){
            printTreePreOrder(tree.left);
            printTreePreOrder(tree.right);
            System.out.println(tree.value);

        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        TreeNodeSearch search = new TreeNodeSearch();
        search.printTreePreOrder(treeNode);
        search.printTreeMidOrder(treeNode);
        search.printTreePostOrder(treeNode);

    }

}
