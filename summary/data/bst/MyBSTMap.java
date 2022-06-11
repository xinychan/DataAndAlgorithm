package summary.data.bst;

/**
 * 底层使用二分搜索树，实现的 Map
 */
public class MyBSTMap<K extends Comparable<K>, V> implements MyMap<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Node{key=" + key.toString() + " : value=" + value.toString() + "}";
        }
    }

    private Node root;

    private int size;

    public MyBSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 以node为根节点插入元素K/V，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
        // node 根节点为null，则直接返回新Node
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            // key.compareTo(node.key) == 0
            node.value = value;
        }
        return node;
    }

    // 返回Node为根节点的树中，Key所在的节点
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            // key.compareTo(node.key) > 0
            return getNode(node.right, key);
        }
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    // 删除以node为根节点的树中键为K的节点
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        }
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        Node successor = minimum(node.right);
        successor.right = removeMin(node.right);
        successor.left = node.left;
        node.left = null;
        node.right = null;
        return successor;
    }

    // 返回node为根节点的树的最小值所在节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 删除以node为根节点的树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        // 左子树没有节点，则当前节点是最小值节点
        // 该节点的右子树补充当前删除的节点，以保持二分搜索树结构
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        // 递归继续，找到最小值节点后，新的根节点赋值给 node.left，完成删除操作
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " do not exist.");
        }
        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
