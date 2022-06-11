package summary.data.bst;

/**
 * 集合接口
 * 集合中没有重复元素
 */
public interface MySet<E> {

    // 添加元素
    void add(E e);

    // 删除元素
    void remove(E e);

    // 是否包含元素
    boolean contains(E e);

    // 集合大小
    int getSize();

    // 集合是否为空
    boolean isEmpty();
}
