package com.ameet.cspractice.problem;

import com.ameet.cspractice.model.Node;

public class CheckBST {
    private static int lastValue = Integer.MIN_VALUE;

    public static boolean is(Node root) {
        if (root == null) {
            return true;
        }
        if (!is(root.getLeft())) {
            return false;
        }
        if (root.id < lastValue) {
            return false;
        } else {
            lastValue = root.id;
            System.out.print(root.id + "-");
        }
        return is(root.getRight());
    }
}
