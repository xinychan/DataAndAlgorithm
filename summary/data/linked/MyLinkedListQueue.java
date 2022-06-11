package summary.data.linked;

/**
 * 使用链表实现队列
 */
public class MyLinkedListQueue<E> implements MyQueue<E> {

    // 队列内部的节点
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

    // 头节点；添加/删除元素都容易
    private Node head;

    // 尾结点；添加元素容易，删除元素不容易
    private Node tail;

    private int size;

    public MyLinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
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
        // 队列先进先出
        // 头节点适合做出口（队首），删除元素
        // 尾节点适合做入口（队尾），添加元素
        if (tail == null) { // 列表为空时
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Dequeue failed.Illegal index");
        }
        // 队列先进先出
        // 头节点适合做出口（队首），删除元素
        // 尾节点适合做入口（队尾），添加元素
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("getFront failed.Illegal index");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Queue:front ");
        Node cur = head;
        while (cur != null) {
            result.append(cur + "->");
            cur = cur.next;
        }
        result.append("NULL tail");
        return result.toString();
    }
}
