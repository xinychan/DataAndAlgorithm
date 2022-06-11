package summary.algorithm;

import java.util.Arrays;

/**
 * 归并排序的优化
 * 思想：将整个数组分成两个子数组，对子数组排序，然后合并子数组；
 * 将此逻辑不断递归，直到最小的子数组完成排序，然后不断导向上合并成一个大数组，最终完成整个数组的合并；
 * 算法的复杂度：O(NlogN)
 */
public class MergeSort {

    private MergeSort() {
    }

    /**
     * 归并排序
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        // E[] temp 需要保存原数组信息；供合并时使用
        E[] temp = Arrays.copyOf(arr, arr.length);
        sort(arr, 0, arr.length - 1, temp);
    }

    /**
     * 递归调用排序过程
     */
    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, E[] temp) {
        // 终止条件：当子数组仅有一个元素时，无需再向下递归拆分两个子数组
        if (l >= r) {
            return;
        }
        // 防止整型溢出
        int mid = l + (r - l) / 2;
        // 拆分成两个子数组
        sort(arr, l, mid, temp);
        sort(arr, mid + 1, r, temp);
        // 当 arr[mid] 小于 arr[mid + 1] 时，即两个子数组已经是从小到大排序的，则不需要排序合并
        // 只有当 arr[mid] 大于 arr[mid + 1] 时，才需要排序合并
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            // 将两个子数组合并成有序的大数组
            merge(arr, l, mid, r, temp);
        }
    }

    /**
     * 合并阶段
     * 合并两个有序区间 arr[l,mid] 和 arr[mid+1,r]
     * E[] temp 需要保存原数组信息
     */
    public static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        // 将 arr 数组中元素拷贝到 temp 中数组
        System.arraycopy(arr, l, temp, l, r - l + 1);
        // 子数组1从i位置开始遍历，子数组2从j位置开始遍历
        int i = l;
        int j = mid + 1;
        // 每轮循环为 arr[k] 赋值
        for (int k = l; k <= r; k++) {
            if (i > mid) { // i 越界，即 arr[l,mid] 子数组中元素已经排序完成，都已经赋值给了 arr[k]；只需要赋值 arr[mid+1,r] 中元素
                arr[k] = temp[j];
                j++;
            } else if (j > r) { // j 越界，即 arr[mid+1,r] 子数组中元素已经排序完成，都已经赋值给了 arr[k]；只需要赋值 arr[l,mid] 中元素
                arr[k] = temp[i];
                i++;
            } else if (temp[i].compareTo(temp[j]) <= 0) {
                // temp[i] <= temp[j];即 arr[l,mid] 中元素 <= arr[mid+1,r] 中元素；赋值更小的元素给 arr[k]
                arr[k] = temp[i];
                i++;
            } else {
                // temp[i].compareTo(temp[j]) > 0
                // temp[i] > temp[j];即 arr[l,mid] 中元素 > arr[mid+1,r] 中元素；赋值更小的元素给 arr[k]
                arr[k] = temp[j];
                j++;
            }
        }
    }
}
