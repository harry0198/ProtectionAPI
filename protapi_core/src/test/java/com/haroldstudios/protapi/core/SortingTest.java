package com.haroldstudios.protapi.core;

import com.haroldstudios.protapi.core.components.QuickSort;
import org.junit.Assert;
import org.junit.Test;

public class SortingTest {

    @Test
    public void testQuickSortPos() {
        int[] num = new int[]{1,5,6,7,4,3,8,2};
        QuickSort.sort(num, 0, num.length -1);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,6,7,8}, num);
    }

    @Test
    public void testQuickSortNeg() {
        int[] num = new int[]{-1,-5,-6,-7,-4,-3,-8,-2};
        QuickSort.sort(num, 0, num.length -1);
        Assert.assertArrayEquals(new int[]{-8,-7,-6,-5,-4,-3,-2,-1}, num);
    }

    @Test
    public void testQuickSortAll() {
        int[] num = new int[]{-1,5,-6,-7,4,3,8,-2};
        QuickSort.sort(num, 0, num.length -1);
        Assert.assertArrayEquals(new int[]{-7,-6,-2,-1,3,4,5,8}, num);
    }


}