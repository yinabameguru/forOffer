package com.jza.forOffer;

import com.sun.imageio.plugins.common.I18N;
import com.sun.jmx.remote.internal.ArrayQueue;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

class ListNode<T> {
    T t;
    ListNode<T> next;

    public ListNode<T> add(T t) {
        ListNode<T> node = new ListNode<>(t);
        this.next = node;
        return node;
    }

    public ListNode(T t) {
        this.t = t;
    }

    public ListNode() {}

    @Override
    public String toString() {
        return "ListNode{" +
                "t=" + t +
                '}';
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

class TreeNode<T> {
    T t;
    TreeNode left;
    TreeNode right;

    public TreeNode(T t) {
        this.t = t;
    }

    public TreeNode() {}

    public TreeNode<T> addleft(T t) {
        TreeNode<T> left = new TreeNode<>(t);
        this.left = left;
        return left;
    }

    public TreeNode<T> addright(T t) {
        TreeNode<T> right = new TreeNode<>(t);
        this.right = right;
        return right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "t=" + t +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

class ParentTreeNode<T> {
    T t;
    ParentTreeNode left;
    ParentTreeNode right;
    ParentTreeNode parent;

    public ParentTreeNode(T t) {
        this.t = t;
    }

    public ParentTreeNode() {}

    public ParentTreeNode<T> addLeft(T t) {
        ParentTreeNode<T> left = new ParentTreeNode<>(t);
        this.left = left;
        left.parent = this;
        return left;
    }

    public ParentTreeNode<T> addRight(T t) {
        ParentTreeNode<T> right = new ParentTreeNode<>(t);
        this.right = right;
        right.parent = this;
        return right;
    }

    @Override
    public String toString() {
        return "ParentTreeNode{" +
                "t=" + t +
                '}';
    }
}

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
        if (Objects.isNull(a)) return false;
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

    //面试题6：从尾到头打印链表
    public void PrintListReversingly(ListNode head) {
        Optional.ofNullable(head).ifPresent(node -> {
            PrintListReversingly(node.next);
            System.out.println(node);
        });
    }

    @Test
    public void test6() {
        ListNode<Integer> head = new ListNode<>(1);
        head.add(2).add(3).add(4).add(5);
        PrintListReversingly(head);
    }

    //面试题7：重建二叉树
    public TreeNode rebuildTree(Integer[] a1, Integer start1, Integer end1, Integer[] a2, Integer start2, Integer end2) {
        TreeNode<Integer> cur = new TreeNode<>(a1[start1]);
        if (Objects.equals(start1, end1)) return cur;
        for (int i = start2; i <= end2; i++) {
            if (Objects.equals(a1[start1], a2[i])) {
            Integer leftLength = i - start2,
                    rightLength = end2 - i;
            cur.left = Objects.equals(leftLength, 0) ?
                    null : rebuildTree(a1, start1 + 1, start1 + leftLength, a2, start2, i - 1);
            cur.right = Objects.equals(rightLength, 0) ?
                    null : rebuildTree(a1, end1 - rightLength + 1, end1, a2, i + 1, end2);
            break;
            }
        }
        return cur;
    }

    @Test
    public void test7() {
        Integer[] a1 = {1, 2, 4, 7, 3, 5, 6, 8};
        Integer[] a2 = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode head = rebuildTree(a1, 0, a1.length - 1, a2, 0, a2.length - 1);
        System.out.println(head);
    }

    //面试题8：二叉树的下一个节点
    public ParentTreeNode nextTreeNode(ParentTreeNode cur) {
        if (cur == null) return null ;
        ParentTreeNode right = cur.right;
        if (Objects.nonNull(right)) {
            while (Objects.nonNull(right.left)) right = right.left;
            return right;
        }
        ParentTreeNode parent = cur.parent;
        if (Objects.isNull(parent)) return null;
        if (Objects.equals(parent.left, cur)) return parent;
        while (Objects.equals(parent.right, cur)) {
            cur = parent;
            parent = parent.parent;
            if (Objects.isNull(parent)) return null;
        }
        return parent;
    }

    @Test
    public void test8() {
        ParentTreeNode<Integer> head = new ParentTreeNode<>(1);
        head.addLeft(2).addLeft(4).addRight(7);
        ParentTreeNode<Integer> right = head.addRight(3);
        right.addLeft(5);
        right.addRight(6).addLeft(8);
        ParentTreeNode next1 = nextTreeNode(head);
        ParentTreeNode next2 = nextTreeNode(head.left);
        ParentTreeNode next4 = nextTreeNode(head.left.left);
        ParentTreeNode next7 = nextTreeNode(head.left.left.right);
        ParentTreeNode next3 = nextTreeNode(right);
        ParentTreeNode next5 = nextTreeNode(right.left);
        ParentTreeNode next6 = nextTreeNode(right.right);
        ParentTreeNode next8 = nextTreeNode(right.right.left);
        System.out.format("%s, %s, %s, %s, %s, %s, %s, %s", next1, next2, next4, next7, next3, next5, next6, next8);
    }

    //面试题9：T1 用两个栈实现队列
    public class QueueByStack <T> {
        Stack<T> stack1 = new Stack<>();
        Stack<T> stack2 = new Stack<>();

        public QueueByStack <T> offer(T t) {
            stack1.push(t);
            return this;
        }

        public T poll() {
            if (!stack2.isEmpty()) return stack2.pop();
            if (stack1.isEmpty()) return null;
            while (!stack1.isEmpty()) stack2.push(stack1.pop());
            return stack2.pop();
        }
    }

    @Test
    public void test9_1() {
        QueueByStack<Integer> queue = new QueueByStack<>();
        queue.offer(1).offer(2).offer(3);
        System.out.println(queue.poll());
        queue.offer(4);
        queue.poll();
        System.out.println(queue.stack1);
        System.out.println(queue.stack2);
    }

    //面试题9：T2 用两个队列实现一个栈
    public class StackByQueue <T> {
        Queue<T> queue1 = new ArrayDeque<>();
        Queue<T> queue2 = new ArrayDeque<>();

        public StackByQueue<T> push(T t) {
            if (queue1.isEmpty() && queue2.isEmpty()) queue1.offer(t);
            else if (queue1.isEmpty()) queue2.offer(t);
            else queue1.offer(t);
            return this;
        }

        public T pop() {
            if (queue1.isEmpty() && queue2.isEmpty()) return null;
                T cur;
            if (queue1.isEmpty()) {
                cur = queue2.poll();
                while (!queue2.isEmpty()) {
                    queue2.offer(cur);
                    cur = queue2.poll();
                }
                return cur;
            } else {
                cur = queue1.poll();
                while (!queue1.isEmpty()) {
                    queue2.offer(cur);
                    cur = queue1.poll();
                }
                return cur;
            }
        }
    }

    @Test
    public void test9_2() {
        StackByQueue<Integer> stack = new StackByQueue<>();
        stack.push(1).push(2).push(3);
        System.out.println(stack.queue1);
        stack.pop();
        System.out.println(stack.queue2);
        stack.push(4);
        System.out.println(stack.queue2);
    }

}
