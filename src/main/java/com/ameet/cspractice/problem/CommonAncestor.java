package com.ameet.cspractice.problem;

import com.ameet.cspractice.model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ameet.cspractice.util.Utils.log;

public class CommonAncestor {
    public Map<Integer, Integer> candidates;
    private int commonAncestor;
    private boolean isFirstReached = false;
    private int firstNodeDepth = 0;
    private int depth = 0;
    private boolean isBothReached = false;
    private List<Integer> nodes;
    private Node rootNode;
    private Tree mt;
    private int a, b;

    public CommonAncestor(int a, int b, int root) {
        this.a = a;
        this.b = b;
        mt = new Tree();
        mt.createTree();
        rootNode = mt.getOrCreate(root);
        nodes = new ArrayList<Integer>() {
            {
                add(a);
                add(b);
            }
        };
    }

    public int execDFS() {
        mt.dfs(rootNode);
        mt.printPrevPost();
        // get a list of vertices w s.t.with prev(u) < prev(w) < prev(v)
        int prev_u = mt.prev[a];
        int prev_v = mt.prev[b];
        if (prev_u > prev_v) {
            int temp;
            prev_u = prev_v;
            temp = a;
            a = b;
            b = temp;
        }
        log(String.format("a: %d b: %d prev_u:%d", a,b,prev_u));
        int post_u = mt.post[a];
        int post_v = mt.post[b];
        candidates = new HashMap<>();
        for (int i = 0; i < mt.prev.length; i++) {
            if (mt.prev[i] <= prev_u && mt.post[i] >= post_u && mt.post[i] > post_v) {
                candidates.put(i, mt.prev[i]);
            }
        }
        int max = -1;
        for (Map.Entry<Integer, Integer> e : candidates.entrySet()) {
            log(e.getKey()+"=>"+e.getValue());
            if (e.getValue() > max) {
                commonAncestor = e.getKey();
                max = e.getValue();
            }
        }
        return commonAncestor;
    }

    public int exec() {
        // execute
        get(rootNode);
        return commonAncestor;
    }

    private void get(Node r) {
        r.isVisited = true;
        depth++;
        log(String.format(">> visiting:%d depth:%d", r.id, depth));
        if (isBothReached) {
            return;
        }
        // pre
        if (nodes.contains(r.id)) {
            if (!isFirstReached) {
                commonAncestor = r.id;
                isFirstReached = true;
                firstNodeDepth = depth;
            } else {
                isBothReached = true;
            }
        }
        // recursion
        for (Node s : r.children()) {
            log(String.format("\t\tchildren of %d:-> %d", r.id, s.id));
            if (isFirstReached && depth < firstNodeDepth && !isBothReached) {
                commonAncestor = r.id;
            }
            if (!s.isVisited) {
                get(s);
            }
        }
        // post
        log(String.format("[DEBUG] %d-> depth=%d, firstNodeDepth=%d", r.id, depth, firstNodeDepth));
        if (isFirstReached && depth < firstNodeDepth) {
            commonAncestor = r.id;
        }
        depth--;
    }
}
