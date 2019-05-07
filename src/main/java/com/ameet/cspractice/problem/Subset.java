package com.ameet.cspractice.problem;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    private int[] set;

    private List<List<Integer>>[] dpTable;

    public Subset(int[] set) {
        this.set = set;

        dpTable = new ArrayList[set.length + 1];
        List<Integer> firstItem = new ArrayList<>();
        firstItem.add(set[0]);
        List<List<Integer>> firstList = new ArrayList<>();
        firstList.add(firstItem);
        dpTable[0] = firstList;
    }

    public void compute() {
        for (int i = 1; i < set.length; i++) {
            List<List<Integer>> prevLists = dpTable[i - 1];
            List<List<Integer>> currLists = new ArrayList<>();
            // add current item as a list
            List<Integer> ithList = new ArrayList<>();
            ithList.add(set[i]);
            currLists.add(ithList);
            System.out.println(String.format(">>[%d]->%d size of previous subsets:%d", i, set[i], prevLists.size()));
            for (List<Integer> s : prevLists) {
                System.out.println(String.format("\t\t>>[%d] operating on set:%s", i, s));
                List<Integer> copy = new ArrayList<>(s);
                List<Integer> copyPlus = new ArrayList<>(s);
                copyPlus.add(set[i]);
                currLists.add(copy);
                currLists.add(copyPlus);
            }
            dpTable[i] = currLists;
        }
        print();
    }

    private void print() {
        System.out.println("***************");
        List<List<Integer>> finalEntry = dpTable[set.length - 1];
        for (List<Integer> s : finalEntry) {
            System.out.println(s);
        }
    }
}
