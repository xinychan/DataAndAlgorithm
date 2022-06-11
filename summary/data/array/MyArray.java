package summary.data.array;

/**
 * 封装具有扩展功能的数组
 */
public class MyArray<E> {

    private E[] data;

    // 数组非空元素的个数
    private int size;

    // 创建数组容量为 capacity 的数组
    public MyArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 默认构造函数，数组容量默认为 10
    public MyArray() {
        this(10);
    }

    // 获取数组中元素个数
    public int getSize() {
        return size;
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 添加元素在末尾
    public void addLast(E e) {
        add(size, e);
    }

    // 添加元素在开头
    public void addFirst(E e) {
        add(0, e);
    }

    // 在 index 位置添加元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add error,index error.");
        }
        // 数组扩容
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    // 重新设置数组容量
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    // 获取指定位置的元素
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get error,index error.");
        }
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    // 修改指定位置的元素
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set error,index error.");
        }
        data[index] = e;
    }

    // 查找是否包含某个元素
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 查找某元素所在的索引；不存在该元素，则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 删除指定位置的元素，并返回该元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove error,index error.");
        }
        E result = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        // 数组缩容；当在 1/2 处频繁 add/remove，会发生复杂度震荡
//        if (size == data.length / 2) {
//            resize(data.length / 2);
//        }
        // 数组缩容；数组元素长度为1/4时，数组长度缩容为1/2；防止复杂度震荡
        if ((size == data.length / 4) && (data.length / 2 != 0)) {
            resize(data.length / 2);
        }
        return result;
    }

    // 删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    // 删除最后一个元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 删除首次出现的某元素
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Array:size = %d,capacity = %d\n", size, data.length));
        result.append("[");
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i != (size - 1)) {
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }
}
