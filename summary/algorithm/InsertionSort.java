package summary.algorithm;

/**
 * 插入排序
 */
public class InsertionSort {
    private InsertionSort() {
    }

    // 循环不变量：数据 data[0...i) 是有序的；数据 data[i...n) 是无序的
    public static <E extends Comparable<E>> void sort(E[] data) {
        for (int i = 0; i < data.length; i++) {
            // 索引 j 关联 data[i]
            // 将 data[i] 与前面位置的数据比较，比前面位置的数据小，则往前移动，一直移到合适的位置
            for (int j = i; j - 1 >= 0; j--) {
                if (data[j].compareTo(data[j - 1]) < 0) {
                    swap(data, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] data) {
        for (int i = 0; i < data.length; i++) {
            // 将 data[i] 的值缓存
            E temp = data[i];
            int j;
            // data[i] 的值和前面数据比较，找到合适的位置后赋值，然后把赋值元素后面的元素都往后移动
            // 这里的赋值操作，比交换操作有优化；赋值只操作1步，交换要操作3步
            for (j = i; j - 1 >= 0; j--) {
                if (temp.compareTo(data[j - 1]) < 0) {
                    data[j] = data[j - 1];
                } else {
                    break;
                }
            }
            data[j] = temp;
        }
    }

    // 换个角度实现插入排序
    public static <E extends Comparable<E>> void sort3(E[] data) {
        for (int i = data.length - 1; i >= 0; i--) {
            // 将 data[i] 的值缓存
            E temp = data[i];
            int j;
            // data[i] 的值和前面数据比较，找到合适的位置后赋值，然后把赋值元素后面的元素都往后移动
            // 这里的赋值操作，比交换操作有优化；赋值只操作1步，交换要操作3步
            for (j = i; j + 1 < data.length; j++) {
                if (temp.compareTo(data[j + 1]) > 0) {
                    data[j] = data[j + 1];
                } else {
                    break;
                }
            }
            data[j] = temp;
        }
    }

    private static <E> void swap(E[] data, int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
