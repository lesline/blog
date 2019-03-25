package leecode.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {4, 2, 5, 3, 6, 7};
        System.out.println(Arrays.toString(array));
        sort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean didSwap = false;//优化后最好时间复杂度为n,不优化为n2
            for (int j = 0; j < array.length - i - 1; j++) {

                if (array[j] < array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    didSwap = true;
                }
            }
            //以下为优化部分
            if (didSwap == false) {
                return;
            }
        }
    }
}
