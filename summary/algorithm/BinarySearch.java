package summary.algorithm;

/**
 * 二分查找法
 * 在有序的数据中查找指定元素
 * 递归方式
 */
public class BinarySearch {

    private BinarySearch() {
    }

    public static <E extends Comparable<E>> int search(E[] data, E target) {
        return search(data, 0, data.length - 1, target);
    }

    // 递归方式实现二分查找法
    private static <E extends Comparable<E>> int search(E[] data, int l, int r, E target) {
        // 终止条件；没有查找到数据
        if (l > r) {
            return -1;
        }
        // 中间索引
        int mid = l + (r - l) / 2;
        // 等于时查找到元素
        if (data[mid].compareTo(target) == 0) {
            return mid;
        }
        // mid 小于 target 时，则再往右侧查找元素
        if (data[mid].compareTo(target) < 0) {
            return search(data, mid + 1, r, target);
        }
        // mid 大于 target 时，则再往左侧查找元素
        if (data[mid].compareTo(target) > 0) {
            return search(data, l, mid - 1, target);
        }
        return -1;
    }
}
