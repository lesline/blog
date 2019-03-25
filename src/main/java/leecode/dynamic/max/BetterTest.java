package leecode.dynamic.max;

public class BetterTest {
    public static void main(String[] args) {
        int[] a = {0, -2, 3, 5, -1, 2};
        System.out.println(maxSubStr(a));
    }

    /**
     * 动态规划：取子数组的最大和
     * 动态规划解法：
     * <p>
     * 设sum[i]为以第i个元素结尾且和最大的连续子数组。假设对于元素i，
     * 所有以它前面的元素结尾的子数组的长度都已经求得，那么以第i个元素结尾且和最大的连续子数组实际上，要么是以第i-1个元素结尾且和最大的连续子数组加上这个元素，
     * 要么是只包含第i个元素，即sum[i] = max(sum[i-1] + a[i], a[i])。
     * 可以通过判断sum[i-1] + a[i]是否大于a[i]来做选择，而这实际上等价于判断sum[i-1]是否大于0。
     * 由于每次运算只需要前一次的结果，因此并不需要像普通的动态规划那样保留之前所有的计算结果，只需要保留上一次的即可，
     * 因此算法的时间和空间复杂度都很小。
     * <p>
     * eg:
     * 0, -2, 3, 5, -1, 2
     * sum=3, 5=8
     * result=8
     * sum=3, 5,-1=7
     * result=8
     * sum=3, 5,-1, 2=9
     * result=9
     *
     * @param a
     * @return
     */
    static int maxSubStr(int[] a) {
        int result = Integer.MIN_VALUE;
        int sum = Integer.MIN_VALUE;//临时数据，存储包含“子数组的最大和”的连续字符串
        for (int i = 0; i < a.length; i++) {
            if (sum > 0)
                sum += a[i];
            else
                sum = a[i];
            if (sum > result)
                result = sum;
        }
        return result;
    }
}
