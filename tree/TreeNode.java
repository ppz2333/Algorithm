package tree;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/25 20:39
 * @description:
 */

/**Java中Tree需要自己建立
 * 树：除根节点，每个节点只有一个父节点；子树之间不相交
 * 兄弟结点：具有相同父结点的结点
 * 节点的度：节点具有的子树数
 * 树的度：树中所有结点的度的最大值
 * 树的深度：树中所有结点的层次的最大值
 *
 * 树的表示：儿子-兄弟表示法，即根节点左边连接孩子节点，右边连接兄弟节点 ==》二叉树
 *
 */

/**满二叉树：每一层节点均达到最大数
 * 除了最底层节点的度为0，其余各层节点的度均为2
 */
/**二叉树度为0,1,2的节点数n0,n1,有n0=n2+1
 * 总结点数n满足：n = n0+n1+n2,
 * 二叉树分支数e: e=n-1=n0+n1+n2-1
 * 由于：e = 2*n2+n1,可得上式
 *
 */
/**完全二叉树：除了最后一层不满，其余各层都是满的，且最后一层的叶子节点连续
 *
 */
//采用儿子-兄弟表示法,存储树

public class TreeNode<T> {
    //节点在二叉树中的索引
    private int index;
    // 数据项
    private T data;
    // 指向左结点分支
    private TreeNode<T> leftChild;
    // 指向右结点分支
    private TreeNode<T> rightChild;


    public TreeNode(T data) {
        this.data = data;
    }
    public TreeNode() {

    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public TreeNode<T> getLeftChild() {
        return leftChild;
    }
    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }
    public TreeNode<T> getRightChild() {
        return rightChild;
    }
    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

}
