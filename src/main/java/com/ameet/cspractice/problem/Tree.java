package com.ameet.cspractice.problem;

import com.ameet.cspractice.model.DepthList;
import com.ameet.cspractice.model.Node;

import java.util.HashMap;
import java.util.Map;

import static com.ameet.cspractice.util.Utils.info;
import static com.ameet.cspractice.util.Utils.log;

public class Tree {

    public int[] prev, post;
    //    Map<Integer, Node> nodeValueMap = new HashMap<>();
    private int depth = 0;
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private DepthList nodesAtDepth;
    private int clock = 1;

    public static void main(String[] args) {
        Tree mt = new Tree();
        mt.createTree();
        Node root = mt.getOrCreate(4);
        mt.treeToList(root);
        mt.nodesAtDepth.print();
    }


    public void printPrevPost() {
        printArray(prev, "prev");
        printArray(post, "post");
    }

    private void printArray(int[] a, String header) {
        log(header + ":\n");
        for (int i = 0; i < a.length; i++) {
            log("\t" + i + ":->" + a[i]);
        }
        log("------------------------");
    }

    public void dfs(Node r) {
        r.isVisited = true;
        info(clock + "-> " + r);
        prev[r.id] = clock;
        clock++;
//        System.out.println("\t\t>> Children of " + r.id + "-" + r.getValue() + " len:" + r.children().size());
        for (Node s : r.children()) {
            if (s == null) {
                continue;
            }
//            System.out.print("\t\t\t\t==> Children of:" + r.id + "-" + r.getValue() + " ==>");
//            System.out.println(s);
            if (!s.isVisited) {
                dfs(s);
            }
        }
        post[r.id] = clock;
        clock++;
    }

    public void treeToList(Node r) {
        log(">>Operating on node:" + r.id);
        depth++;
        r.isVisited = true;
        log(depth + "-> " + r);
        nodesAtDepth.addAtDepth(depth, r);
        for (Node s : r.children()) {
            if (!s.isVisited) {
                treeToList(s);
            }
        }
        depth--;
    }

    private void addNode(int id, int left, int right) {
        Node leftN = getOrCreate(left);
        Node rightN = getOrCreate(right);
        Node me = nodeMap.get(id);
        if (me == null) {
            me = new Node(id, leftN, rightN);
            nodeMap.put(id, me);
        } else {
            me.setLeft(leftN);
            me.setRight(rightN);
        }
    }

    public Node getOrCreate(int id) {
        Node one = nodeMap.get(id);
        if (one == null) {
            one = new Node(id);
            nodeMap.put(id, one);
        }
        return one;
    }

    public void createValueTree() {
        final int[] id = {1};

        Map<Integer, Integer> nodeValues = new HashMap<Integer, Integer>() {
            {
                put(id[0]++, 2);
                put(id[0]++, 6);
                put(id[0]++, 3);
                put(id[0]++, 9);
                put(id[0]++, 18);
                put(id[0]++, -4);
                put(id[0]++, 41);
                put(id[0]++, 9);
                put(id[0]++, 3);
                put(id[0]++, 49);
                put(id[0]++, 1);
                put(id[0]++, 2);
            }
        };
        for (Map.Entry<Integer, Integer> e : nodeValues.entrySet()) {
            nodeMap.put(e.getKey(), new Node(e.getKey(), e.getValue()));
        }
        addChildren(1, 2, 3);
        addChildren(2, 4, 5);
        addChildren(3, 6, 7);
        addChildren(6, 8, 9);
        addChildren(9, 10, 11);
        addChildren(11, 12, -100);
        prev = new int[nodeMap.size() + 1];
        post = new int[nodeMap.size() + 1];
    }

    private void addChildren(int me, int l, int r) {
        Node meN = nodeMap.get(me);
        Node ln = nodeMap.getOrDefault(l, null);
        Node rn = nodeMap.getOrDefault(r, null);
        if (rn != null) {
            meN.setRight(rn);
        }
        if (ln != null) {
            meN.setLeft(ln);
        }
    }

    public void createTree() {
        addNode(4, 2, 6);
        addNode(2, 1, 3);
        addNode(6, 5, 7);
        nodesAtDepth = new DepthList(nodeMap.size());
        prev = new int[nodeMap.size() + 1];
        post = new int[nodeMap.size() + 1];
    }

    public void createTree2() {
        addNode(2, 1, 3);
        addNode(1, 4, 5);
        nodesAtDepth = new DepthList(nodeMap.size());
        prev = new int[nodeMap.size() + 1];
        post = new int[nodeMap.size() + 1];
    }
}
