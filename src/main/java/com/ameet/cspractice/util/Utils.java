package com.ameet.cspractice.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Utils {
    private static final boolean DEBUG = false;
    private static final boolean INFO = true;

    public static void log(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }

    public static void info(String s) {
        if (INFO) {
            System.out.println(s);
        }
    }

    public static String linkedToStr(LinkedList<Integer> ll) {
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> lcopy = (LinkedList<Integer>) ll.clone();
        lcopy.forEach(i -> sb.append(i).append("-"));
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static LinkedList<Integer> copy(LinkedList<Integer> ll) {
        LinkedList<Integer> copy = new LinkedList<Integer>();
        copy.addAll(ll);
        return copy;
    }

    public static List<LinkedList<Integer>> copy(List<LinkedList<Integer>> prevList) {
        List<LinkedList<Integer>> copyList = new ArrayList<>(prevList.size());
        for (LinkedList<Integer> l : prevList) {
            copyList.add(copy(l));
        }
        return copyList;
    }
}
