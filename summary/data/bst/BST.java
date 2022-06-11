package summary.data.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 * 左子树上所有节点的值都小于它的根节点
 * 右子树上所有的节点的值都大于它的根节点
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        // 元素值
        private E e;
        // 左子节点
        private Node left;
        // 右子节点
        private Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 根节点
    private Node root;

    // 节点数量
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 添加元素 e
    public void add(E e) {
        root = add(root, e);
    }

    // 以node为根节点插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {
        // node 根节点为null，则直接返回新Node
        if (node == null) {
            size++;
            return new Node(e);
        }
        // 元素e小于根节点，递归添加到左子树
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            // 元素e大于根节点，递归添加到右子树
            node.right = add(node.right, e);
        }
        return node;
    }

    // 看二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 以node为根节点的二分搜索树中是否包含元素e
    private boolean contains(Node node, E e) {
        // 节点为空，不包含
        if (node == null) {
            return false;
        }
        // 节点的元素相等，找到了元素e
        if (e.compareTo(node.e) == 0) {
            return true;
        }
        // 元素e比节点小，到左子树中寻找
        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            // e.compareTo(node.e) > 0
            // 元素e比节点大，到右子树中寻找
            return contains(node.right, e);
        }
    }

    // 二分搜索树前序遍历；（深度优先遍历）
    public void preOrder() {
        preOrder(root);
    }

    // 前序遍历以node为根节点的二分搜索树
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        // 访问节点元素
        System.out.println(node.e);
        // 遍历左子树
        preOrder(node.left);
        // 遍历右子树
        preOrder(node.right);
    }

    // 二分搜索树中序遍历；从小到大有序遍历
    public void inOrder() {
        inOrder(root);
    }

    // 中序遍历以node为根节点的二分搜索树
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        // 遍历左子树
        inOrder(node.left);
        // 访问节点元素
        System.out.println(node.e);
        // 遍历右子树
        inOrder(node.right);
    }

    // 二分搜索树后序遍历
    public void postOrder() {
        postOrder(root);
    }

    // 后序遍历以node为根节点的二分搜索树
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        // 遍历左子树
        postOrder(node.left);
        // 遍历右子树
        postOrder(node.right);
        // 访问节点元素
        System.out.println(node.e);
    }

    // 二分搜索树前序遍历；非递归写法
    public void preOrderNR() {
        // 使用栈结构先进后出特性遍历二分搜索树
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    // 二分搜索树层序遍历；（广度优先遍历）
    public void levelOrder() {
        // 使用队列结构先进先出特性广度优先遍历二分搜索树
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.e);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    // 寻找最小元素
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("Tree is empty");
        }
        return minimum(root).e;
    }

    // 返回node为根节点的树的最小值所在节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 寻找最大元素
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("Tree is empty");
        }
        return maximum(root).e;
    }

    // 返回node为根节点的树的最大值所在节点
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    // 删除最小值所在节点，并返回该最小值
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
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

    // 删除最大值所在节点，并返回该最大值
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除以node为根节点的树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        // 右子树没有节点，则当前节点是最大值节点
        // 该节点的左子树补充当前删除的节点，以保持二分搜索树结构
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        // 递归继续，找到最大值节点后，新的根节点赋值给 node.right，完成删除操作
        node.right = removeMax(node.right);
        return node;
    }

    // 删除元素e所在节点
    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除以node为根节点的树中元素e的节点
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        // 元素e小于当前根节点，在左子树中寻找要删除的节点
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }
        // 元素e大于当前根节点，在右子树中寻找要删除的节点
        if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        }
        // e == node.e:元素e等于当前根节点元素时，则找到要删除的元素
        // 要删除的元素节点没有左子树
        if (node.left == null) {
            // 该节点的右子树补充当前删除的节点，以保持二分搜索树结构
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        // 要删除的元素节点没有右子树
        if (node.right == null) {
            // 该节点的左子树补充当前删除的节点，以保持二分搜索树结构
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        // 要删除的元素所在节点的左右子树都不为空时
        // 找到比要删除的元素节点还大的最小节点（最近节点）
        // 即删除元素节点的右子树的最小节点
        // 用这个最近节点，作为继任节点补充当前删除的节点
        // 找到最近节点，并赋值给继任节点
        Node successor = minimum(node.right);
        // 右子树删除最近节点；并将右子树新根节点，作为继任节点的右子节点
        successor.right = removeMin(node.right);
        // 被删除元素节点左子节点，作为继任节点左子节点
        successor.left = node.left;
        // 被删除元素左右节点清空
        node.left = null;
        node.right = null;
        // 返回继任节点；继任节点作为根节点补充当前删除的节点
        return successor;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 以node为根节点，深度为depth的二分搜索树字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}
