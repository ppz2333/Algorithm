package tree.haffmanTree;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/25 20:56
 * @description:
 */




/**哈夫曼树与哈夫曼编码--根据节点不同和查找频率构造更有效的搜索树
 * 假设学生考试成绩：小于60；60-70；70-80；80-90；90-100
 * 假设成绩分布不同，如何提高查找效率？--Haffman树
 *
 * 带权路径长度：二叉树所有叶子节点带有权值，从根到叶子节点的路径长度为l，长度乘上权值之和。
 *
 * 哈夫曼树特点：
 * (1)没有度为1的节点（度为0或者2）
 * (2)n个叶子节点的哈夫曼树共有2n-1个节点
 * (3)同一组权值，存在不同构的两棵哈夫曼树
 *
 *不用等长编码：为了避免二义性
 *
 *用二叉树进行非等长编码(Haffman树)：---最优编码不一定通过haffman算法得到
 *(1)左0右1
 *(2)字符只在叶结点上
 *eg:四个字符的频率：a:4  u:3  x:2  z:1
 *
 */
public class HaffmanTree<T extends Comparable<T>> {

    //构造哈夫曼树O(nlogn)：给定一个数组，每次挑选数组中较小的两个值进行合并（使用到最小堆）
    /**需要重新定义节点
     HaffmanNode<T> createHuffman(MinHeap<T> heap) {
     HaffmanNode<T> tree = new HaffmanNode<T>();
     for(int i =0;i<heap.getSize();i++) {
     tree.left.data = heap.delete();
     tree.right.data = heap.delete();
     tree.weight = tree.left.weight+tree.right.weight;
     heap.insert(tree);
     }
     tree = heap.delete();
     }
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
class HaffmanNode<T extends Comparable<T>>{
    int weight;
    T data;
    HaffmanNode<T> left,right;
}
