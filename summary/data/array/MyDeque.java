package summary.data.array;

/**
 * 双端队列
 * 能从首端和尾端添加元素或者删除元素
 *
 * @param <E>
 */
public class MyDeque<E> {

    // 存放数据的数组
    private E[] data;

    // 队首位置索引
    private int front;

    // 队尾位置索引
    private int tail;

    // 队列中元素的个数
    private int size;

    public MyDeque(int capacity) {
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public MyDeque() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
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

    // 队尾添加元素
    public void addLast(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    // 队首添加元素
    public void addFront(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        // 在 front 前的位置添加元素
        front = front == 0 ? data.length - 1 : front - 1;
        data[front] = e;
        size++;
    }

    // 队尾删除元素
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue Empty Queue");
        }
        // 删除队尾元素
        tail = tail == 0 ? data.length - 1 : tail - 1;
        E result = data[tail];
        data[tail] = null;
        size--;
        if ((size == getCapacity() / 4) && (getCapacity() / 2 != 0)) {
            resize(getCapacity() / 2);
        }
        return result;
    }

    // 队首删除元素
    public E removeFront() {
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
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("MyDeque:");
        result.append("front [");
        // 循环队列的for循环判断条件
        for (int i = 0; i < size; i++) {
            result.append(data[(i + front) % data.length]);
            if (i != size - 1) {
                result.append(",");
            }
        }
        result.append("] tail");
        return result.toString();
    }
}
