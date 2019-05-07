package com.ameet.cspractice;

import com.ameet.cspractice.problem.GridPath;
import com.ameet.cspractice.problem.Subset;
import org.junit.Test;

public class DPTest {

    @Test
    public void testGridPaths() {
        GridPath gp = new GridPath(3);
        gp.compute();
    }

    @Test
    public void testSubsets() {
        int[] A = {1, 2, 4};
        Subset ss = new Subset(A);
        ss.compute();
    }
}
