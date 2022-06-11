package summary.data.linked;

/**
 * 底层使用链表，实现的集合
 */
public class MyLinkedListSet<E> implements MySet<E> {

    private MyLinkedListR<E> list;

    public MyLinkedListSet() {
        this.list = new MyLinkedListR<>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
