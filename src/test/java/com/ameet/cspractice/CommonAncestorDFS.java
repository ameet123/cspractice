package com.ameet.cspractice;

import com.ameet.cspractice.problem.CommonAncestor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CommonAncestorDFS {
    private int a;
    private int b;

    public CommonAncestorDFS(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Parameterized.Parameters(name = "{index}: exec({0}, {1})  ")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2, 3},
                {1, 5},
                {2, 6},
                {2, 5}
        });
    }

    @Test
    public void testCommonAnc() {
        int r = 4;
        CommonAncestor anc = new CommonAncestor(a, b, r);
        int ca = anc.execDFS();
        System.out.println(String.format(">> (%d, %d) => %d ", a, b, ca));
    }
}
