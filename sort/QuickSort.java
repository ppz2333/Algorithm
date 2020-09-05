package sort;

import static sort.Sort.swap;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/9/5 10:40
 * @description:
 */


public class QuickSort {

    /**
     * 快速排序 不稳定
     * 最佳情况：T(n) = O(nlogn)
     * 最差情况：T(n) = O(n2)
     * 平均情况：T(n) = O(nlogn)
     * 空间复杂度：O(logn)
     */
    public static int[] quickSort(int[] array, int start, int end) {
        if(start >= end) return array;
        int l = start + 1, r = end;
        int pilot = array[start];
        while (l <= r) {
            if(array[l] < pilot) {
                l++;
                continue;
            }
            if(array[r] >= pilot) {
                r--;
                continue;
            }
            swap(array, l, r);
        }
        swap(array, start, r);
        quickSort(array, start, r-1);
        quickSort(array, l, end);
        return array;
    }
}
