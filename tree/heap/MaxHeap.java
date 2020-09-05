package tree.heap;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/25 20:59
 * @description:
 */

/**优先队列：取出元素的顺序是依照元素的优先权(关键字)大小，而不是元素进入对垒的先后顺序
 * 	即关注优先队列的插入与删除最大值操作
 * 	优先队列采用什么存储结构：
 * 		(1)数组：插入O(1)，删除O(n)
 * 		(2)链表：插入O(1)，删除O(n)
 *
 *	采用完全二叉树存储结构实现，即堆
 */

/**堆的两个特性
 * (1)结构上：用数组表示的完全二叉树
 * (2)有序性：任一节点的关键字大于(或小于)其左右节点的关键字，分别对应大顶堆和小顶堆
 *
 * 接下来是用数组实现的完全二叉树队列的堆
 */

public class MaxHeap<T extends Comparable<T>> {
    private static final int default_capacity =10;
    private int size;//堆中实际元素个数
    private T[] heap ;

    //扩容
    public void ensureCapacity(int newCapacity) {
        if(newCapacity<this.size)
            return;
        T[] oldHeap = this.heap;
        this.heap =  (T[]) new Comparable[newCapacity];
        for(int i = 0;i<size;i++) {
            this.heap[i] = oldHeap[i];
        }
    }

    //创建一个空的大顶堆，数组存储
    public MaxHeap(int newCapacity) {
        ensureCapacity(newCapacity);
        this.size = 0;
    }
    public MaxHeap() {
        this(default_capacity);//调用本类的构造函数
        this.size = 0;
    }

    //判断堆是否已满,
    public boolean isFull() {
        if(this.size==this.heap.length)
            return true;
        return false;
    }

    //判断堆是否为空,即完全二叉树的根节点为null
    public boolean isEmpty() {
        if(this.heap[0]==null)
            return true;
        return false;
    }

    //返回堆的最大元素(高优先级)
    public T getMax() {
        return heap[0];
    }

    //插入
    public void insert(T data) {
        int i;
        if(isFull()) {
            int j = this.heap.length;
            ensureCapacity(j*2);
        }
        i = ++this.size;
        //data与其父节点比较，比父节点大则变成父节点
        this.heap[i] = data;
        adjustMaxHeap(this.heap,0);
    }


    //删除：删除堆的根节点，为了保证堆的完全二叉树结构
    //i=a++是先将变量a的值保存在i中，再将变量a的值加1
    //i=++a是先将变量a的值加1,再将变量a的值保存在i中，
    //直接遍历的方法
    public T delete() {
        if(isEmpty()) {
            return null;
        }
        T data = getMax();
        //取出最后一个元素,将temp的值去取代data节点
        T temp = this.heap[this.size--];
        this.heap[0] = temp;
        //然后调整为最大堆
        adjustMaxHeap(this.heap,0);
        return data;

    }


    //建立大顶堆,完全二叉树性质：最后一个非叶子结点是第len/2(索引从1开始)，O(nlogn)
    public void buildMaxHeap(T[] array) {
        //从最后一个非叶子节点len/2-1开始向上构造大顶堆
        //i的左孩子为2i+1,右孩子为2i+2
        for(int i = this.size/2-1;i>=0;i--) {
            adjustMaxHeap(array,i);
        }
    }

    //调整为大顶堆
    public void adjustMaxHeap(T[] array,int i) {
        int maxIndex = i;
        //如果有左孩子，且左孩子大于父节点，则将最大指针指向左孩子
        if(2*i+1 < this.size-1 && array[2*i+1].compareTo(array[maxIndex])==1) {
            maxIndex = 2*i+1;
        }
        //如果有右孩子，且右孩子大于父节点，则将最大指针指向右孩子
        if(2*i+2 < this.size-1 && array[2*i+2].compareTo(array[maxIndex])==1) {
            maxIndex = 2*i+2;
        }

        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置
        if(maxIndex != i) {
            swap(array,maxIndex,i);
            adjustMaxHeap(array,maxIndex);
        }

    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> m = new MaxHeap<>();
        System.out.println(m.size);

    }

}

