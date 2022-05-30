package algorithm.string;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap();
        int max = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
//            if (map.get(s.charAt(i)) != null) {
            if (map.containsKey(s.charAt(i))) {
                start = Math.max(start, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);


            max = Math.max(max, i - start + 1);
            System.out.println(i + ":" + map.get(s.charAt(i)) + ":" + start + ":" + max);


        }
        return max;


    }
}