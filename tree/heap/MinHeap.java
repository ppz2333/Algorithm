package tree.heap;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/25 20:57
 * @description:
 */


public class MinHeap<T extends Comparable<T>> {
    private static final int default_capacity =10;
    private int size;//堆中实际元素个数
    private T[] heap ;

    //扩容
    public void ensureCapacity(int newCapacity) {
        if(newCapacity<this.getSize())
            return;
        T[] oldHeap = this.heap;
        this.heap =  (T[]) new Comparable[newCapacity];
        for(int i = 0;i<getSize();i++) {
            this.heap[i] = oldHeap[i];
        }
    }

    //创建一个空的小顶堆，数组存储
    public MinHeap(int newCapacity) {
        ensureCapacity(newCapacity);
        this.setSize(0);
    }
    public MinHeap() {
        this(default_capacity);//µ÷ÓÃ±¾ÀàµÄ¹¹Ôìº¯Êý
        this.setSize(0);
    }

    //判断堆是否已满
    public boolean isFull() {
        if(this.getSize()==this.heap.length)
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
    public T getMin() {
        return heap[0];
    }

    //插入
    public void insert(T data) {
        int i;
        if(isFull()) {
            int j = this.heap.length;
            ensureCapacity(j*2);
        }
        i = this.setSize(this.getSize() + 1);
        //data与其父节点比较，比父节点小则变成父节点
        while(data.compareTo(this.heap[(i-1)/2])==-1) {
            this.heap[i] = this.heap[(i-1)/2];//±È½»»»Êý¾Ý¸ü¿ì£¬¿ÉÒÔ¿´×öÊÇ´Ó×îºóÒ»²ã¿ªÊ¼±éÀú
            i=(i-1)/2;
        }
        this.heap[i] = data;
    }


    //删除：删除堆的根节点，为了保证堆的完全二叉树结构
    //i=a++是先将变量a的值保存在i中，再将变量a的值加1
    //i=++a是先将变量a的值加1,再将变量a的值保存在i中，
    //直接遍历的方法
    public T delete() {
        if(isEmpty()) {
            return null;
        }
        T data = getMin();
        //取出最后一个元素,将temp的值去取代data节点
        T temp = this.heap[this.size--];
        int parent=0;
        this.heap[parent] = temp;
        //然后调整为最小堆
        buildMinHeap(this.heap);
        return data;

    }


    //建立小顶堆,完全二叉树性质：最后一个非叶子结点是第len/2(索引从1开始)，O(nlogn)
    public void buildMinHeap(T[] array) {
        //从最后一个非叶子节点len/2-1开始向上构造小顶堆
        //i的左孩子为2i+1,右孩子为2i+2
        for(int i = this.getSize()/2-1;i>=0;i--) {
            adjustMinHeap(array,i);
        }
    }

    //调整为小顶堆
    public void adjustMinHeap(T[] array,int i) {
        int maxIndex = i;
        //如果有左孩子，且左孩子大于父节点，则将最大指针指向左孩子
        if(2*i+1 < this.getSize()-1 && array[2*i+1].compareTo(array[maxIndex])==-1) {
            maxIndex = 2*i+1;
        }
        //如果有右孩子，且右孩子大于父节点，则将最大指针指向右孩子
        if(2*i+2 < this.getSize()-1 && array[2*i+2].compareTo(array[maxIndex])==-1) {
            maxIndex = 2*i+2;
        }

        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置
        if(maxIndex != i) {
            swap(array,maxIndex,i);
            adjustMinHeap(array,maxIndex);
        }

    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        MinHeap<Integer> m = new MinHeap<>();
        System.out.println(m.getSize());

    }

    public int getSize() {
        return size;
    }

    public int setSize(int size) {
        this.size = size;
        return size;
    }

}


