package sort;

import static sort.Sort.swap;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/9/5 10:44
 * @description:
 */


public class HeapSort {
    /**
     *堆排序 不稳定
     * 最佳情况：T(n) = O(nlogn)
     * 最差情况：T(n) = O(nlogn)
     * 平均情况：T(n) = O(nlogn)
     * 空间复杂度：O(1)
     */

    //声明全局变量，用于记录数组array的长度
    static int len;

    public static int[] heapSort(int[] array) {
        len = array.length;
        if(len < 1) return array;
        //1.构建一个最大堆
        buildMaxHeap(array);
        //2.循环将堆首位（最大值）与末位交换，然后在重新调整最大堆
        while(len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0);
        }
        return array;
    }

    //建立最大堆
    public static void buildMaxHeap(int[] array) {
        //从最后一个非叶子节点开始向上构造最大堆
        //for循环这样写会更好一点：i的左子树和右子树分别2i+1和2(i+1)
        for (int i = (len / 2 - 1); i >= 0; i--) {
            adjustHeap(array, i);
        }
    }

    //调整使之成为最大堆
    public static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if(i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex])
            maxIndex = i * 2 + 1;
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if(i * 2 + 2 < len && array[i * 2 + 2] > array[maxIndex])
            maxIndex = i * 2 + 2;
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if(maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }
}
