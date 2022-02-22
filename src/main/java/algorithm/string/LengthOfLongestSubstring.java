package algorithm.string;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 * 要点：HashMap 存出现过最大坐标
 *
 *
 * <p>
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
 *
 * @author zsl274196@cainiao.com
 * @date 2022/02/13 下午8:32
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        int a = lengthOfLongestSubstring("abcabcbb");
        System.out.println(a);
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int start = 0, end = 0; end < n; end++) {
            char tmp = s.charAt(end);
            if (map.containsKey(tmp)) {
                start = map.get(tmp) + 1;
            }
            map.put(tmp, end);
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }
}