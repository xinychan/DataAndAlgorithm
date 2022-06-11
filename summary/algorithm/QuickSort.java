package summary.algorithm;

import java.util.Random;

/**
 * 双路快速排序法
 * 快速排序+标定元素随机化+解决元素相等的性能退化问题
 */
public class QuickSort {

    private static final Random random = new Random();

    private QuickSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        // 当数组区间只有一个元素时，不需要排序
        if (l >= r) {
            return;
        }
        // 获得当前标定元素位置，对标定元素分割成左右两部分子数组区间
        // 然后对子数组区间继续做排序
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    /**
     * 双路快速排序，对标定元素左右两侧做排序
     * 最终使得被标定元素左边的元素，都小于等于标定元素
     * 标定元素右边的元素，都大于等于标定元素
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        // 生成 [l,r] 之间的随机索引
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        // arr[l+1...i-1] <= v;arr[j+1...r] >= v
        int i = l + 1;
        int j = r;
        while (true) {
            // 从左往右遍历；当前值小于标定值，索引+1
            while (i <= j && arr[i].compareTo(arr[l]) < 0) {
                i++;
            }
            // 从右往左遍历；当前值大于标定值，索引-1
            while (j >= i && arr[j].compareTo(arr[l]) > 0) {
                j--;
            }
            // i/j继续迭代，直到i==j，本轮循环结束
            if (i >= j) {
                break;
            }
            // 当左部分i值大于标定值，或右部分j值小于标定值，交换i/j值
            // 以满足左边部分小于等于标定值，右边部分大于等于标定值
            swap(arr, i, j);
            // 交换i/j值后，i/j继续迭代，直到i==j，本轮循环结束
            i++;
            j--;
        }
        // i==j后，交换l/j值，把l值放到中间，并返回j位置索引
        swap(arr, l, j);
        return j;
    }

    private static <E extends Comparable<E>> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
