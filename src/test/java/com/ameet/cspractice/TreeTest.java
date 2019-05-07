package com.ameet.cspractice;

import com.ameet.cspractice.model.Node;
import com.ameet.cspractice.problem.CheckBST;
import com.ameet.cspractice.problem.PathSum;
import com.ameet.cspractice.problem.Tree;
import org.junit.Test;


public class TreeTest {


    @Test
    public void exec() {
        Tree mt = new Tree();
        mt.createTree();
        Node rootNode = mt.getOrCreate(4);
        System.out.println(">> Is BST? = " + CheckBST.is(rootNode));
    }

    @Test
    public void execFail() {
        Tree mt = new Tree();
        mt.createTree2();
        Node rootNode = mt.getOrCreate(2);
        System.out.println(">> Is BST? = " + CheckBST.is(rootNode));
    }

    @Test
    public void testSumTree() {
        Tree mt = new Tree();
        mt.createValueTree();
        Node rootNode = mt.getOrCreate(1);
//        mt.dfs(rootNode);
        PathSum ps = new PathSum(5);
        ps.findSumPaths(rootNode,null);
    }
}