package summary.data.array;

/**
 * 底层使用数组，实现的队列
 * 普通队列
 *
 * @param <E> 泛型
 */
public class MyArrayQueue<E> implements MyQueue<E> {

    private MyArray<E> array;

    public MyArrayQueue(int capacity) {
        array = new MyArray<>(capacity);
    }

    public MyArrayQueue() {
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
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Queue:");
        result.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            result.append(array.get(i));
            if (i != array.getSize() - 1) {
                result.append(",");
            }
        }
        result.append("] tail");
        return result.toString();
    }
}
