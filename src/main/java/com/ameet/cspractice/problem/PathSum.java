package com.ameet.cspractice.problem;

import com.ameet.cspractice.model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * print all paths which add up to a given sum
 */
public class PathSum {
    private int sum;
    private Map<Integer, List<Integer>> dpTable;
    private List<Integer> path;

    public PathSum(int sum) {
        this.sum = sum;
        dpTable = new HashMap<>();
        path = new ArrayList<>();
    }

    public void findSumPaths(Node r, Node prev) {
        if (r == null) {
            return;
        }
        checkPaths(r, prev);
        findSumPaths(r.getLeft(), r);
        findSumPaths(r.getRight(), r);
        if (path.size() > 1) {
            path.remove(path.size() - 1);
        }
    }

    /**
     * get the list of sums from previous node and to each entry, add the current value.
     * check if any entry adds up to the desired sum.
     *
     * @param r    current node
     * @param prev prev node
     */
    private void checkPaths(Node r, Node prev) {
        path.add(r.getValue());
        List<Integer> mySums = new ArrayList<>();
        if (prev != null) {
            List<Integer> prevList = dpTable.get(prev.id);
            System.out.print("prevListSize=" + prevList.size() + " : [" + r.getValue() + "] <-[" + prev
                    .getValue() + "] => ");
            System.out.println(prevList);
            for (int i = 0; i < prevList.size(); i++) {
                int s = prevList.get(i);
                int itemSum = s + r.getValue();
                if (itemSum == sum) {
                    printPath(i);
                }
                mySums.add(itemSum);
            }
        }
        mySums.add(r.getValue());
        dpTable.put(r.id, mySums);
    }

    /**
     * nth entry in the path array added up to the sum. so starting from root, go to the nth entry and then from
     * there print all the items till the end.
     *
     * @param n this is the entry which starts the sequence
     */
    private void printPath(int n) {
        StringBuilder sb = new StringBuilder(">> Sum found! \t\t-> { ");
        for (int i = 0; i < path.size(); i++) {
            if (i < n) {
                continue;
            }
            sb.append(path.get(i)).append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(" }");
        System.out.println(sb.toString());
    }
}
