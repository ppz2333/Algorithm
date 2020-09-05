package tree.banlanceBinaryTree;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/25 20:54
 * @description:
 */



/**平衡二叉树--空树或者任意节点左右子树高度差绝对值不超过2
 * 又称AVL树---平衡二叉查找树，一种特殊的二叉搜索树(查找速度与高度成正比)
 *
 * 平衡二叉树的高度：h=O(logn)
 *
 * https://www.cnblogs.com/qm-article/p/9349681.html
 */

//平衡二叉树的调整：在进行插入，删除前后，破坏了平衡树的结构，需要调整
/**插入或者删除平衡二叉树中一个节点，可能会打破平衡，如何调整?
 * 失去平衡的4种姿态：LL LR  RR RL
 *
 * (1)LL(插入或删除一个节点后，根节点的左子树的左子树还有非空节点)
 * 			A
 * 		  B
 * 		C
 * 		A是被破坏节点，C在A的左子树B的左孩子上，打破平衡
 *
 * (2)LR(插入或删除一个节点后，根节点的左子树的右子树还有非空节点)
 * 			A
 * 		  B
 * 		    C
 * 		A是被破坏节点，C在A的左子树B的右孩子上，打破平衡
 *
 * (3)RR(插入或删除一个节点后，根节点的右子树的右子树还有非空节点)
 * 			A
 * 		 	  B
 * 		    	C
 * 		A是被破坏节点，C在A的左子树B的右孩子上，打破平衡
 *
 * (4)RL(插入或删除一个节点后，根节点的右子树的左子树还有非空节点)
 * 			A
 * 		  	  B
 * 		    C
 * 		A是被破坏节点，C在A的左子树B的右孩子上，打破平衡
 *
 *	插入和删除节点后导致AVL失衡，需要通过旋转恢复AVL树的平衡
 */

public class BalanceBinaryTree<T extends Comparable<T>> {

    //根节点
    private AVLNode<T> root;

    //获取树的高度
    public int getHeight(AVLNode<T> root) {
        if(root!=null)
            return root.height;
        return 0;
    }

    //比较大小
    private int max(int a, int b) {
        return a>b ? a : b;
    }

    /**LL情况：左单旋，即将被破坏平衡的节点A的左孩子定住，旋转一次
     * 将A的左子树赋值给B
     * B的右子树赋值给A的左子树
     * A赋值给B的右子树
     * B替代A成为根节点
     */
    public AVLNode<T> LeftLeftRotation(AVLNode<T> aNode){
        //A的左子树赋给B
        AVLNode<T> bNode = aNode.left;
        //由于B上可能有右孩子，就把B的右孩子赋给A
        aNode.left = bNode.right;
        //把A变成B的右孩子
        bNode.right = aNode;

        aNode.height = max(getHeight(aNode.left),getHeight(aNode.right));
        bNode.height = max(getHeight(bNode.left),getHeight(bNode.right));
        return bNode;
    }

    /**RR情况：右单旋，即将被破坏平衡的节点A的右孩子定住，旋转一次
     * 将A的右子树赋值给B
     * B的左子树赋值给A的右子树
     * A赋值给B的左子树
     * B替代A成为根节点
     */
    public AVLNode<T> RightRightRotation(AVLNode<T> aNode){
        //A的右子树赋给B
        AVLNode<T> bNode = aNode.right;
        //由于B上可能有左孩子，就把B的左孩子赋给A的右孩子
        aNode.right = bNode.left;
        //把A变成B的左孩子
        bNode.left = aNode;

        aNode.height = max(getHeight(aNode.left),getHeight(aNode.right));
        bNode.height = max(getHeight(bNode.left),getHeight(bNode.right));
        return bNode;
    }


    /**LR情况：左双旋
     * (1)第一次旋转：将被破坏平衡的节点A的左孩子定住，RR旋转
     * 		即先处理A节点的左子树部分，变成LL破坏平衡情况,
     * (2)第二次旋转：将原来被破坏平衡的节点A定住，LL旋转
     *
     */
    public AVLNode<T> LeftRightRotation(AVLNode<T> aNode){
        aNode.left = RightRightRotation(aNode.left);
        return LeftLeftRotation(aNode);
    }

    /**RL情况：右双旋
     * (1)第一次旋转：将被破坏平衡的节点A的右孩子定住，LL旋转
     * 		即先处理A节点的右子树部分，变成RR破坏平衡情况,
     * (2)第二次旋转：将原来被破坏平衡的节点A定住，RR旋转
     *
     */
    public AVLNode<T> RightLeftRotation(AVLNode<T> aNode){
        aNode.right = LeftLeftRotation(aNode.right);
        return RightRightRotation(aNode);
    }

    //插入节点
    public AVLNode<T> insert(AVLNode<T> root,T data){
        if(root==null)
            root = new AVLNode<T>(data, null, null);
        int cmp = data.compareTo(root.data);
        if(cmp<0) {//插入到左子树
            root.left = insert(root.left,data);
            //插入节点后失去平衡
            if(getHeight(root.left)-getHeight(root.right)==2) {
                //插入到左子树的左边
                if(data.compareTo(root.left.data)<0)
                    root = LeftLeftRotation(root);
                    //插入到左子树的右边
                else
                    root = LeftRightRotation(root);
            }
        }else if(cmp>0) {//插入右子树
            root.right = insert(root.left,data);
            //破坏了平衡
            if(getHeight(root.right)-getHeight(root.left)==2) {
                //插入到了右边
                if(data.compareTo(root.right.data)>0)
                    root = RightRightRotation(root);
                else
                    root = RightLeftRotation(root);
            }
        }else {//cmp=0
            System.out.println("添加失败：不允许添加相同的节点！");
        }

        root.height = max(getHeight(root.left),getHeight(root.right));
        return root;
    }

    //删除节点
    public AVLNode<T> delete(AVLNode<T> root,T data){

        if(root==null||data==null)
            root = null;
        int cmp = data.compareTo(root.data);
        if(cmp<0) {//删除左子树中的节点
            root.left = delete(root.left,data);
            //删除节点后失去平衡,是右子树多出来冲突节点
            if(getHeight(root.right)-getHeight(root.left)==2) {
                //查找右子树的右节点冲突还是左节点冲突
                if(getHeight(root.right.right)>getHeight(root.right.left))
                    root = RightRightRotation(root);
                else
                    root = RightLeftRotation(root);
            }
        }else if(cmp>0) {//删除右子树中的节点
            root.left = delete(root.left,data);
            //删除节点后失去平衡,是左子树多出来冲突节点
            if(getHeight(root.right)-getHeight(root.left)==2) {
                //查找左子树的右节点冲突还是左节点冲突
                if(getHeight(root.left.right)>getHeight(root.left.left))
                    root = LeftRightRotation(root);
                else
                    root = LeftLeftRotation(root);
            }
        }else {//cmp=0,找到要删除的节点
            //tree的左右孩子都非空
            if(root.left!=null && root.right!=null) {
                if(getHeight(root.left)>getHeight(root.right)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                    AVLNode<T> max =  maximum(root.left);
                    root.data = max.data;
                    root.left = delete(root.left,max.data);
                }else {
                    // 如果tree的左子树不比右子树高；
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    AVLNode<T> min =  minimum(root.right);
                    root.data = min.data;
                    root.left = delete(root.right,min.data);
                }
            }else {
                root = (root.left!=null) ? root.left : root.right;
            }
        }

        root.height = max(getHeight(root.left),getHeight(root.right));
        return root;
    }

    //查找树的最小值，二叉搜索树的最小值一定在最左分支的端点
    private AVLNode<T> minimum(AVLNode<T> root) {
        if(root==null)
            return null;
        else if(root.left==null)
            return root;
        else
            return minimum(root.left);
    }

    //查找右子树的最大值，二叉搜索树的最大值一定在最右分支的端点
    private AVLNode<T> maximum(AVLNode<T> root) {
        if(root==null)
            return null;
        else if(root.right==null)
            return root;
        else
            return maximum(root.right);
    }

}
