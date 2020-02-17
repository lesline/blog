package algorithm.dynamic.max;

/**
 * 取子数组的最大和
 */
public class CommonTest {
    public static void main(String[] args) {
        int[] a = {0, -2, 3, 5, -1, 2};
        System.out.println(MaxSubString(a));
    }

    /**
     * 动态规划：取子数组的最大和
     * 一般解法：两次循环遍历
     *
     * @param a
     * @return
     */
    static int MaxSubString(int[] a) {
        int max = -1000;  //初始值为负无穷大
        int sum;
        for (int i = 0; i < a.length; i++) {
            sum = 0;
            for (int j = i; j < a.length; j++) {
                sum += a[j];
                if (sum > max)
                    max = sum;
            }
        }
        return max;

    }
}
