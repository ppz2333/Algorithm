package tree.binarySearchTree;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/25 20:55
 * @description:
 */


import algorithm.tree.TreeNode;

/**二叉搜索树（二叉排序树）  左最小--根居中--右最大
 * 可以为null
 * 不为null时，满足：
 * (1)非空左子树的所有键值小于其根节点的键值
 * (2)非空右子树的所有键值大于其根节点的键值
 * (3)左右子树都是二叉搜索树
 *
 *
 */
public class BinarySearchTree{


    private TreeNode<Integer> root; // 树根节点

    /**构建二叉搜索树：往二叉搜索树中插入一个节点
     * (1)树为空，直接插入
     * (2)二叉搜索树不为空，递归找到插入元素的位置
     */
    public TreeNode<Integer> insert(int key,TreeNode<Integer> root) {
        if(root==null)
            root = new TreeNode<Integer>(key);
        else {
            if(key<root.getData()) {
                //递归插入左子树
                root.setLeftChild(insert(key,root.getLeftChild()));
            }else if(key>root.getData()){
                //递归插入右子树
                root.setRightChild(insert(key,root.getRightChild()));
            }else {
                //对相等的情况不处理
            }
        }
        return root;
    }

    /**二叉搜索树的查找---尾递归实现，尾递归均可用循环来实现
     * (1)树为空，返回null
     * (2)树非空，根节点与插入值key比较：
     * 		若key<根data,只需在左子树中搜索
     * 		若key>根data,只需在右子树中搜索
     * 		若key==根data,搜索完成
     *
     * 	查找效率取决于树的高度
     */

    public TreeNode<Integer> find(int key,TreeNode<Integer> root){
        if(root==null)
            return null;
        else {
            if(key>root.getData()) {
                return find(key,root.getRightChild());
            }else if(key<root.getData()){
                return find(key,root.getLeftChild());
            }else {
                return root;
            }

        }
    	/* 将尾递归改为迭代方式
    	TreeNode<Integer> current = root;
    	while(current!=null) {
    		if(key>current.getData()) {
    			root = current.getRightChild();
    		}else if(key<current.getData()){
    			current = current.getLeftChild();
    		}else {
    			return current;
    		}
    	}
    	return null;
    	**/
    }

    /**查找二叉搜索树最小值--尾递归
     * 	最小元素一定在树的最左分支的端节点上
     *
     */
    public TreeNode<Integer> findMin(TreeNode<Integer> root){
        TreeNode<Integer> current = root;
        if(current==null)
            return null;
        else if(current.getLeftChild()!=null) {
            return findMin(current.getLeftChild());
        }else
            return current;
    }


    /**查找二叉搜索树最大值--将尾递归改为迭代
     * 	最大元素一定在树的最右分支的端节点上
     *
     */
    public TreeNode<Integer> findMax(TreeNode<Integer> root){
        TreeNode<Integer> current = root;
        if(current!=null) {
            while(current.getRightChild()!=null) {
                current = current.getRightChild();
            }
        }
        return current;
    }


    /** 删除结点,考虑3种情况：
     * (1)删除节点是叶子节点：直接删除，并修改其父节点对应的指针为null
     * (2)删除节点只有一个孩子：将要删除节点的父节点指向删除节点的孩子
     * (3)删除节点有两个孩子：用另一节点代替被删除节点：右子树的最小元素或者左子树的最大元素
     *
     */
    //假设要删除的元素一定存在与树中
    public TreeNode<Integer> delete(int key,TreeNode<Integer> root) {
        TreeNode<Integer> current = root;
        TreeNode<Integer> temp;
        if(key<current.getData()) {
            //对左子树递归删除
            current.setLeftChild(delete(key,current.getLeftChild()));
        }else if(key>current.getData()){
            //对右子树递归删除
            current.setRightChild(delete(key,current.getRightChild()));;
        }else { //找到要删除的节点
            //（3）删除节点有两个孩子
            if(current.getLeftChild()!=null && current.getRightChild()!=null) {
                temp = findMin(current.getRightChild());
                current.setData(temp.getData());
                current.setRightChild(delete(current.getData(),current.getRightChild()));

            }else {//(1)(2)删除节点有1个孩子或者无孩子
                temp = current;
                if(current.getLeftChild()==null)//有右孩子或无孩子
                    current = current.getRightChild();
                else if(current.getRightChild()==null)
                    current = current.getLeftChild();
            }
        }
        return root;
    }

    public void show(TreeNode node){
        //输出节点的数据域
        if(node!=null)
            System.out.println(node.getData());
        else
            System.out.println("null");
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
    }
}

