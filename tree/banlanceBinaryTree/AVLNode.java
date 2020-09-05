package tree.banlanceBinaryTree;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/25 20:54
 * @description:
 */


//Comparable是一个接口，传入的T类型必须已经实现Comparable接口中的compareTo()方法
public class AVLNode<T extends Comparable<T>> {
    // 数据项
    T data;
    int height;
    // 指向左结点分支
    AVLNode<T> left;
    // 指向右结点分支
    AVLNode<T> right;
    //父节点
    AVLNode<T> parent;

    public AVLNode(T data, AVLNode<T> left, AVLNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 0;
    }



    //比较根节点值的大小
    public int compare(AVLNode<T> node) {
        return this.data.compareTo(node.data)==1?1:0;
    }
}

