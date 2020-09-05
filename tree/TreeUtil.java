package tree;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/25 20:51
 * @description:
 */



import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**树的遍历
 * 			A
 * 		B		C
 *	 D	   E  F    	G
 */

/**树的前序遍历：根-左-右
 * ABDECFG
 */

/**树的中序遍历：左-根-右
 * DBEAFCG
 */

/**树的后序遍历：左-右-根
 * DEBFGCA
 */

/**已知前序和中序遍历，求后序遍历
 * 前序遍历：ABDECFG
 * 中序遍历：DBEAFCG
 *
 * (1)根据前序遍历特点，确定A为根节点
 * (2)结合中序遍历：A的左子树的中序遍历是DBE，A的右子树的中序遍历是FCG
 * (3)观察可知B是左子树的根节点，C是右子树根节点
 */
//https://blog.csdn.net/qingtian_1993/article/details/80877487

//递归遍历二叉树

public class TreeUtil {

    //前序遍历
    public void preOrder(TreeNode<?> root) {
        if (root == null) {
            return;
        } else {
            //1.打印根节点
            System.out.println("preOrder data:" + root.getData());
            //2.递归遍历左子树
            preOrder(root.getLeftChild());
            //3.递归遍历右子树
            preOrder(root.getRightChild());
        }
    }

    //中序遍历
    public void midOrder(TreeNode<?> root) {
        if (root == null) {
            return;
        } else {
            //1.递归遍历左子树
            midOrder(root.getLeftChild());
            //2.打印根节点
            System.out.println("preOrder data:" + root.getData());
            //3.递归遍历右子树
            midOrder(root.getRightChild());
        }
    }

    //后序遍历
    public void postOrder(TreeNode<?> root) {
        if (root == null) {
            return;
        } else {
            //1.递归遍历左子树
            postOrder(root.getLeftChild());
            //2.递归遍历右子树
            postOrder(root.getRightChild());
            //3.打印根节点
            System.out.println("preOrder data:" + root.getData());

        }
    }

    //二叉树的非递归遍历：使用堆栈

    /**非递归前序遍历
     * (1)访问根节点，压入堆栈，遍历根节点的左子树
     * (2)当左子树遍历结束，
     * (3)访问左子树，不为空就设置为根节点，为空就把栈顶元素设置为根节点
     */
    public void preOrderNonRecursive(TreeNode<?> root) {

        Stack<TreeNode<?>> nodeStack = new Stack<>();
        while(root!=null) {
            //1.访问根节点
            System.out.println("preOrderNonRecursive data:"+root.getData());
            //2.当前节点右子树不为空，放入堆栈
            if(root.getRightChild()!=null)
                nodeStack.push(root.getRightChild());
            if(root.getLeftChild()!=null) {
                root = root.getLeftChild();
            }else {
                root = nodeStack.pop();
            }
        }

    }

    /**非递归中序遍历
     * (1)访问根节点，持续压入leftchild，直到leftchild=null
     * (2)从堆栈弹出，访问其数据，遍历其右子树
     */
    public void midOrderNonRecursive(TreeNode<?> root) {
        Stack<TreeNode<?>> nodeStack = new Stack<>();
        do{
            while(root!=null) {
                //1.把根压入栈，遍历左孩子
                nodeStack.push(root);
                root = root.getLeftChild();
            }

            if(nodeStack!=null) {
                root = nodeStack.pop();
                System.out.println("midOrderNonRecursive data:"+root.getData());
                root = root.getRightChild();
            }
        }while(root!=null && nodeStack!=null);

    }

    /**非递归后序遍历
     * (1)从根节点开始，持续压入leftchild，直到leftchild=null
     * (2)判断当前节点有无右子树，若有，优先访问右子树
     * (3)无右子树或已经访问过右子树则访问当前节点。
     */
    public void postOrderNonRecursive(TreeNode<?> root) {
        Stack<TreeNode<?>> nodeStack = new Stack<>();
        //上一个节点
        TreeNode<?> pre = root;
        do{
            while(root!=null) {
                //1.把根压入栈，遍历左孩子
                nodeStack.push(root);
                root = root.getLeftChild();
            }

            //访问当前节点
            if(nodeStack!=null) {
                //获取右子树
                TreeNode<?> temp = nodeStack.peek().getRightChild();
                //不存在右子树或者已经访问过，访问父节点
                if(temp == null || temp == pre) {
                    root = nodeStack.pop();
                    System.out.println("postOrderNonRecursive data:"+root.getData());
                    //记录访问过的节点
                    pre = root;
                    //当前节点置空
                    root = null;
                }else {
                    temp = root;
                }


            }
        }while(root!=null && nodeStack!=null);
    }

    //获取二叉树的高度:左右子树的最大高度
    public int getHeight(TreeNode<?> root) {
        if(root == null) {
            return 0;
        }else {
            int i = getHeight(root.getLeftChild());
            int j = getHeight(root.getLeftChild());
            return i>=j?i+1:j+1;
        }
    }

    //获取二叉树的度:具有子树的最大数
    public int getSize(TreeNode<?> root) {
        if(root == null) {
            return 0;
        }else {
            return 1+getSize(root.getLeftChild())+getSize(root.getRightChild());
        }
    }


    /**层序遍历：队列实现，先入先出
     * (1)将根节点入队，然后执行循环
     * (2)节点出队。访问该节点，其左右儿子入队。
     */
    public void levelOrder(TreeNode<?> root) {
        if(root==null)return;
        Queue<TreeNode<?>> nodeQueue = new LinkedList<>();
        //1.压入根节点
        nodeQueue.offer(root);
        while(nodeQueue!=null) {
            int levelSize = nodeQueue.size();
            while(levelSize!=0) {
                TreeNode<?> temp = nodeQueue.poll();
                System.out.print(temp.getData());
                if(temp.getLeftChild()!=null)
                    nodeQueue.offer(temp.getLeftChild());
                if(temp.getRightChild()!=null)
                    nodeQueue.offer(temp.getRightChild());
                levelSize--;
            }
            System.out.println("");//换行输出
        }
    }


    /**前序遍历创建一个二叉树
     * 用链式存储结构存储的一颗二叉树为['A','B','C','D','#','#','E','#','#','F','#','#','#']
     * (1)
     *
     */
    public TreeNode<?> createBinaryTree(List<String> data) {

        return null;
    }


    /**输出二叉树的叶子节点
     * 在遍历算法中检测左右子树是否都为空
     */
    public void preOrderPrintLeaves(TreeNode<?> root) {
        if(root!=null) {
            if(root.getLeftChild()==null && root.getRightChild()==null) {
                System.out.print(root.getData());
            }
            preOrderPrintLeaves(root.getLeftChild());
            preOrderPrintLeaves(root.getRightChild());
        }
    }

    /**判断两棵树是否相等
     *
     *
     */
    public boolean equal(TreeNode<?> tree1,TreeNode<?> tree2){

        boolean flag = true;
        if(tree1==null && tree2==null) {
            return true;
        }
        if(tree1!=null && tree2!=null && tree1.getData() == tree2.getData()&&flag==true) {
            boolean leftEqual = equal(tree1.getLeftChild(),tree2.getLeftChild());
            boolean rightEqual = equal(tree1.getRightChild(),tree2.getRightChild());
            flag = leftEqual && rightEqual;
        }
        return flag;
    }

    /**判断两棵树是否同构
     * 只管结构相同，不管值是否相同
     */
    public boolean isOmorphic(TreeNode<?> t1,TreeNode<?> t2) {
        if(t1==null || t2==null)
            return (t1==null) && (t2==null);
        return isOmorphic(t1.getLeftChild(), t2.getRightChild()) && isOmorphic(t1.getRightChild(), t2.getLeftChild());
    }


}

