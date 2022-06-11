package summary.data.linked;

/**
 * 链表
 */
public class MyLinkedList<E> {

    // 链表内部的节点
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 链表的虚拟头节点
    private Node dummyHead;

    // 链表元素个数
    private int size;

    public MyLinkedList() {
        // 虚拟头节点不保存任何数据；初始时虚拟头节点的 next 节点指向空
        dummyHead = new Node(null, null);
        size = 0;
    }

    // 获取链表元素个数
    public int getSize() {
        return size;
    }

    // 链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 链表的 index 位置添加元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed.Illegal index");
        }
        // 使用 prev 标记上一个节点，以关联所插入元素的位置
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            // prev 指向下一个元素，直到找到要插入元素的位置
            prev = prev.next;
        }
        // 下述三个步骤，等价于：
        // prev.next = new Node(e, prev.next);
        // 创建新元素
        Node node = new Node(e);
        // 新元素的 next 节点指向 prev 标记节点的下一个节点
        node.next = prev.next;
        // prev 索引指向当前node元素
        prev.next = node;
        // 元素个数+1
        size++;
    }

    // 链表头添加元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 链表尾添加元素
    public void addLast(E e) {
        add(size, e);
    }

    // 获取链表中 index 位置元素
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed.Illegal index");
        }
        // dummyHead 是虚拟节点，当前实际节点指向 dummyHead 的 next
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 获取链表中第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获取链表中最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    // 修改链表中 index 位置的元素
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set failed.Illegal index");
        }
        // dummyHead 是虚拟节点，当前实际节点指向 dummyHead 的 next
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        // 遍历链表，如果当前节点不是，则寻找下一个 next 节点
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 从链表中删除 index 位置的元素
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove failed.Illegal index");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 将 prev 的 next 节点指向要删除节点的 next 元素，达到删除效果
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    // 从链表中删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            result.append(cur + "->");
            cur = cur.next;
        }
        result.append("NULL");
        return result.toString();
    }
}
