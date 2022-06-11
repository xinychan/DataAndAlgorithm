package summary.data.linked;

/**
 * 映射Map接口
 */
public interface MyMap<K, V> {
    // 添加元素
    void add(K key, V value);

    // 删除元素
    V remove(K key);

    // 是否包含元素
    boolean contains(K key);

    // 获取元素
    V get(K key);

    // 修改元素
    void set(K key, V value);

    // Map元素个数
    int getSize();

    // Map是否为空
    boolean isEmpty();
}
