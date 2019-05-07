package com.ameet.cspractice.problem;

import com.ameet.cspractice.util.Utils;

import java.util.*;

public class GridPath {
    private final String BORDER = "*******************";
    private int n;
    private Map<Integer, List<Integer>> legalMoves = new HashMap<>();
    private List<LinkedList<Integer>>[] dpTable;
    private int total;

    public GridPath(int n) {
        this.n = n;
        this.total = n * n + 1;
        fixLegalMoves();
        printLegalMoves();
        dpTable = new ArrayList[total];
        LinkedList<Integer> itemOnePath = new LinkedList<>();
        itemOnePath.add(1);
        List<LinkedList<Integer>> itemOneList = new ArrayList<>();
        itemOneList.add(itemOnePath);
        dpTable[1] = itemOneList;
    }

    public void compute() {
        List<LinkedList<Integer>> prevList, newList;
        for (int i = 2; i < total; i++) {
            prevList = dpTable[i - 1];
            newList = new ArrayList<>();
            System.out.println(">>Iteration: " + i + " Previous paths:" + prevList.size());
            for (LinkedList<Integer> p : prevList) {
                System.out.println("\t\t>> Operating on: " + Utils.linkedToStr(p));
                int lastSquare = p.getLast();
                List<Integer> nextMoves = legalMoves.get(lastSquare);
                if (nextMoves.isEmpty()) {
                    continue;
                }
                for (int m : nextMoves) {
                    LinkedList<Integer> cc = Utils.copy(p);
                    cc.addLast(m);
                    newList.add(cc);
                }
            }
            System.out.println("\t\t\t>>new paths in[" + i + "]:" + newList.size());
            dpTable[i] = newList;
        }
        System.out.println("\n\n" + BORDER + "Total Paths:" + getMaxPaths() + " " + BORDER);
    }

    private int getMaxPaths() {
        int maxPaths = 0;
        for (int i1 = n * n; i1 > 0; i1--) {
            List<LinkedList<Integer>> i = dpTable[i1];
            if (i != null && i.size() > 0) {
                maxPaths = i.size();
                break;
            }
        }
        return maxPaths;
    }

    private void fixLegalMoves() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int id = getId(i, j);
                List<Integer> myMoves = new ArrayList<>();
                addNeighbors(i, j, myMoves);
                legalMoves.put(id, myMoves);
            }
        }
    }

    private void addNeighbors(int i, int j, List<Integer> myMoves) {
        int neighbor;
        if ((i + 1) <= n) {
            neighbor = getId(i + 1, j);
            myMoves.add(neighbor);
        }
        if ((j + 1) <= n) {
            neighbor = getId(i, j + 1);
            myMoves.add(neighbor);
        }
    }

    private void printLegalMoves() {
        legalMoves.forEach((k, v) -> Utils.info(k + "=> " + v));
    }

    private int getId(int x, int y) {
        return (x - 1) * n + y;
    }
}