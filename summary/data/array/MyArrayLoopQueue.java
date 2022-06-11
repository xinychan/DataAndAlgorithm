package summary.data.array;

/**
 * 底层使用数组，实现的队列
 * 循环队列
 *
 * @param <E> 泛型
 */
public class MyArrayLoopQueue<E> implements MyQueue<E> {

    // 存放数据的数组
    private E[] data;

    // 队首位置索引
    private int front;

    // 队尾位置索引
    private int tail;

    // 队列中元素的个数
    private int size;

    public MyArrayLoopQueue(int capacity) {
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public MyArrayLoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        // 当循环队列是满的状态，扩充队列
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue Empty Queue");
        }
        // 删除队首元素
        E result = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if ((size == getCapacity() / 4) && (getCapacity() / 2 != 0)) {
            resize(getCapacity() / 2);
        }
        return result;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot getFront Empty Queue");
        }
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        // 把原数组中的元素赋值给新数组
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("LoopQueue:");
        result.append("front [");
        // 循环队列的for循环判断条件
        for (int i = 0; i < size; i++) {
            result.append(data[(i + front) % data.length]);
            if ((i + front + 1) % data.length != tail) {
                result.append(",");
            }
        }
        result.append("] tail");
        return result.toString();
    }
}
