package summary.data.linked;

/**
 * 栈接口
 * 后进先出的数据结构，LIFO(last in first out)
 *
 * @param <E> 泛型
 */
public interface MyStack<E> {

    // 栈中元素的个数
    int getSize();

    // 栈是否是空
    boolean isEmpty();

    // 数据入栈
    void push(E e);

    // 数据出栈
    E pop();

    // 栈顶的元素（最先出栈元素）
    E peek();
}
