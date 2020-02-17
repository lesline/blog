package algorithm.sort;

import java.util.Arrays;

/**
 * Created by zhangshaolin on 2017/11/23.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {4, 2, 5, 1, 6, 3, 7};
        System.out.println(Arrays.toString(array));

        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }


    public static void quickSort(int[] array, int left, int right) {
        if(left >= right) return;

        int iLeft = left;
        int iRight = right;
        int mid = array[left];

        while (left < right) {

            while (left < right && mid < array[right]) {
                right--;
            }
            array[left] = array[right];

            while (left < right && mid > array[left]) {
                left++;
            }
            array[right] = array[left];
        }

        array[left] = mid;
        System.out.println(Arrays.toString(array));

        quickSort(array, iLeft, left - 1);
        quickSort(array, left + 1, iRight);

    }
}
