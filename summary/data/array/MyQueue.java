package summary.data.array;

/**
 * 队列接口
 * 先出先出的数据结构，FIFO(first in first out)
 *
 * @param <E>
 */
public interface MyQueue<E> {

    // 队列中元素的个数
    int getSize();

    // 队列是否是空
    boolean isEmpty();

    // 数据入队
    void enqueue(E e);

    // 数据出队
    E dequeue();

    // 队首的元素（最先出队元素）
    E getFront();
}
