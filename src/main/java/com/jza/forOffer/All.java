package com.jza.forOffer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

public class All {

    private static <T> void swap(T[] a, Integer i, Integer j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static Integer mid(Integer i, Integer j) {
        return (i + j) / 2;
    }

    private static boolean less(Integer i, Integer j) {
        return i < j;
    }

    private static boolean less(Integer[] a, Integer i, Integer j) {
        return a[i] < a[j];
    }

    private static boolean more(Integer i, Integer j) {
        return i > j;
    }

    private static boolean more(Integer[] a, Integer i, Integer j) {
        return a[i] > a[j];
    }

    private static boolean lessEqual(Integer i, Integer j) {
        return i <= j;
    }

    private static boolean moreEqual(Integer i, Integer j) {
        return i >= j;
    }


    //面试题3：T1 数组中重复的数字
    public Integer repetitiveNumber(Integer[] a) {
        if (Objects.isNull(a) || Objects.equals(a.length, 1)) return null;
        Integer length = a.length,
                cur = 0,
                curDate;
        while (less(cur, length)) {
            if ((curDate = a[cur]) < 0) return null;
            if (!Objects.equals(curDate, cur)) {
                if (!Objects.equals(curDate, a[curDate])) {
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
    public void test3_1() {
        Integer[] a = new Integer[]{2, 3, 5, 4, 3, 2, 6, 7};
        Integer integer = repetitiveNumber(a);
        System.out.println(integer);
    }

    //面试题3：T2 不修改数组，找出数组中重复的数字
    public Integer repetitiveNumberWithoutAlter(Integer[] a) {
        if (Objects.isNull(a) || Objects.equals(a.length, 1)) return null;
        Integer length = a.length,
                left = 1,
                right = length - 1;
        while (true) {
            if (Objects.equals(left, right)) return left;
            Integer i1 = 0,
                    i2 = 0,
                    mid = mid(left, right);

            for (int i = 0; i < length; i++) {
                if (moreEqual(a[i], left) &&lessEqual(a[i], mid)) {
                    i1++;
                } else if (more(a[i], mid) && lessEqual(a[i], right)) {
                    i2++;
                }
            }
            if (moreEqual(i1, i2)) {
                right = mid;
            } else {
                left = mid;
            }
        }
    }

    @Test
    public void test3_2() {
        Integer[] a = new Integer[]{2, 3, 5, 4, 3, 2, 6, 7};
        Integer integer = repetitiveNumberWithoutAlter(a);
        System.out.println(integer);
    }

    //面试题4：二维数组中的查找
    public boolean TwoDimensionSearch(Integer[][] a, Integer seed) {
        if (Objects.equals(a, null)) return false;
        Integer outLength = a.length,
                innerLength = a[0].length,
                y = outLength - 1,
                x = 0;
        while (more(y, 0) && less(x, innerLength)) {
            if (Objects.equals(a[x][y], seed)) {
                return true;
            } else if (less(a[x][y], seed)) {
                x++;
            } else {
                y--;
            }
        }
        return false;
    }

    @Test
    public void test4() {
        Integer[][] a = new Integer[][]{{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        boolean b = TwoDimensionSearch(a, -1);
        System.out.println(b);
    }
}
