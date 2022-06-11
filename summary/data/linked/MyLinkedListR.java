package summary.data.linked;

/**
 * 链表
 * 使用递归方式实现
 */
public class MyLinkedListR<E> {

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

    // 链表的头节点
    private Node head;

    // 链表元素个数
    private int size;

    public MyLinkedListR() {
        head = null;
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
        head = add(head, index, e);
        size++;
    }

    // 以 node 为头结点的链表 index 位置添加元素；递归算法
    private Node add(Node node, int index, E e) {
        if (index == 0) {
            return new Node(e, node);
        }
        node.next = add(node.next, index - 1, e);
        return node;
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
        return get(head, index);
    }

    // 找到 node 为头结点的链表中 index 位置元素；递归算法
    private E get(Node node, int index) {
        if (index == 0) {
            return node.e;
        }
        return get(node.next, index - 1);
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
        set(head, index, e);
    }

    // 修改 node 为头结点的链表中 index 位置元素；递归算法
    private void set(Node node, int index, E e) {
        if (index == 0) {
            node.e = e;
            return;
        }
        set(node.next, index - 1, e);
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        return contains(head, e);
    }

    // 查找 node 为头结点的链表中是否有元素e；递归算法
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.equals(e)) {
            return true;
        }
        return contains(node.next, e);
    }

    // 链表中删除元素
    public void removeElement(E e) {
        head = removeElement(head, e);
    }

    // 删除 node 为头结点的链表中元素e；递归算法
    private Node removeElement(Node node, E e) {
        if (node == null) {
            return null;
        }
        node.next = removeElement(node.next, e);
        if (node.e.equals(e)) {
            size--;
            return node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            result.append(cur + "->");
            cur = cur.next;
        }
        result.append("NULL");
        return result.toString();
    }
}
