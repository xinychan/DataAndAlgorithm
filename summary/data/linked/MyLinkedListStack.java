package summary.data.linked;

/**
 * 使用链表实现栈
 */
public class MyLinkedListStack<E> implements MyStack<E> {

    private MyLinkedList<E> list;

    public MyLinkedListStack() {
        list = new MyLinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Stack:top ");
        result.append(list);
        return result.toString();
    }
}
