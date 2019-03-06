package com.jza.forOffer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

public class All {

    private static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //面试题3：数组中重复的数字
    public Integer repetitiveNumber(Integer[] a) {
        if (Objects.isNull(a) || a.length == 1) return null;
        int length = a.length, cur = 0, curDate;
        while (cur < length) {
            if ((curDate = a[cur]) < 0) return null;
            if (curDate != cur) {
                if (curDate != a[curDate]) {
                    swap(a, a[cur], a[curDate]);
                } else {
                    return curDate;
                }
            } else {
                cur++;
            }
        }
        return null;
    }

    @Test
    public void test3() {
        Integer[] a = new Integer[]{2, 3, 5, 4, 3, 2, 6, 7};
        Integer integer = repetitiveNumber(a);
        System.out.println(integer);
    }
}
