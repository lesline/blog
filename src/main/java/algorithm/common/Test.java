package algorithm.common;

/**
 * @author zhangshaolin
 * @create 2020/2/22
 */
public class Test {
    public static String minWindow(String source, String target) {
        // write your code here
        if (source == null || source.length() == 0) {
            return "";
        }
        if (target == null || target.length() == 0) {
            return "";
        }
        int[] td = new int[256];
        for (char tc : target.toCharArray()) {
            td[tc]++;
        }
        int[] sd = new int[256];
        int minLen = source.length();
        int start = 0;
        int first = -1, end = 0;
        int found = 0;  // 在source中发现了target中元素的数目
        for (int i = 0; i < source.length(); i++) {
            sd[source.charAt(i)]++;
            if (sd[source.charAt(i)] <= td[source.charAt(i)]) {
                found++;
            }
            if (found == target.length()) {
                // 满足条件
                // 处理1：start后移，删除无用元素
                while (start <= i && sd[source.charAt(start)] > td[source.charAt(start)]) {
                    sd[source.charAt(start)]--;
                    start++;
                }
                // 处理2：如果比当前最小子串小，则更新
                if (i + 1 - start <= minLen) {
                    minLen = i + 1 - start;
                    first = start;
                    end = i + 1;
                }
                sd[source.charAt(start)]--;
                start++;
                found--;
            }
        }
        if (first == -1) {
            return "";
        } else {
            return source.substring(first, end);
        }
    }

    public static void main(String[] args) {
        String source = "ADOBECODEBANC", target = "ABC";
        System.out.println(minWindow(source, target));
    }
}