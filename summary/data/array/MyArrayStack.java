package summary.data.array;

/**
 * 底层使用数组，实现的栈
 *
 * @param <E> 泛型
 */
public class MyArrayStack<E> implements MyStack<E> {

    // 使用自定义数组实现栈结构
    private MyArray<E> array;

    public MyArrayStack(int capacity) {
        array = new MyArray<>(capacity);
    }

    public MyArrayStack() {
        array = new MyArray<>();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Stack:");
        result.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            result.append(array.get(i));
            if (i != array.getSize() - 1) {
                result.append(",");
            }
        }
        result.append("] top");
        return result.toString();
    }
}
