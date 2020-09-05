package sort;

import java.util.Arrays;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/9/5 10:48
 * @description:
 */


public class MergeSort {/**
 * 归并排序 稳定
 * 最佳情况：T(n) = O(n)
 * 最差情况：T(n) = O(nlogn)
 * 平均情况：T(n) = O(nlogn)
 * 空间复杂度：O(n)
 */
public static int[] mergeSort(int[] array) {
    if(array.length < 2)
        return array;
    int mid = array.length / 2;
    int[] left = Arrays.copyOfRange(array, 0, mid);
    int[] right = Arrays.copyOfRange(array, mid, array.length);
    return merge(mergeSort(left), mergeSort(right));
}

    /**
     * 将两段排序好的数组结合成一个排序数组
     * @param left 左有序数组
     * @param right 右有序数组
     * @return 合并后的有序数组
     */
    public static int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < res.length; index++) {
            if(i >= left.length)
                res[index++] = right[j++];
            else if(j >= right.length)
                res[index++] = left[i++];
            else if(left[i] > right[j])
                res[index++] = right[j++];
            else
                res[index++] = left[i++];
        }
        return res;
    }

}
