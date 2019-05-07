package com.ameet.cspractice.model;

import java.util.LinkedList;

public class DepthList {
    private LinkedList<Node>[] nodesAtDepth;

    public DepthList(int s) {
        nodesAtDepth = new LinkedList[s + 1];
    }

    public void addAtDepth(int d, Node n) {
        LinkedList<Node> ln = nodesAtDepth[d];
        if (ln == null) {
            ln = new LinkedList<>();
            nodesAtDepth[d] = ln;
        }
        ln.add(n);
    }

    public void print() {
        for (int i = 0; i < nodesAtDepth.length; i++) {
            LinkedList<Node> x = nodesAtDepth[i];
            if (x == null) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            x.forEach(node -> sb.append(node.id).append(","));
            sb.setLength(sb.length() - 1);
            System.out.println("depth[" + i + "] -> " + sb);
        }
    }
}
