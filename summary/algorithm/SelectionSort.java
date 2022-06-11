package summary.algorithm;

/**
 * 选择排序
 */
public class SelectionSort {
    private SelectionSort() {
    }

    // 循环不变量：数据 data[0...i) 是有序的；数据 data[i...n) 是无序的
    public static <E extends Comparable<E>> void sort(E[] data) {
        for (int i = 0; i < data.length; i++) {
            // 选择 data[i...n) 中最小值的索引
            int minIndex = i;
            for (int j = i; j < data.length; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // 找到本轮的最小值后，将最小值与data[i]对换
            swap(data, i, minIndex);
        }
    }

    // 从大往小倒序排序
    // 循环不变量：数据 data[0...i) 是无序的；数据 data[i...n) 是有序的
    public static <E extends Comparable<E>> void sortReverse(E[] data) {
        for (int i = data.length - 1; i >= 0; i--) {
            // 选择 data[0...i) 中最大值的索引
            int maxIndex = i;
            for (int j = i; j >= 0; j--) {
                if (data[j].compareTo(data[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            // 找到本轮的最大值后，将最大值与data[i]对换
            swap(data, i, maxIndex);
        }
    }

    private static <E> void swap(E[] data, int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
