package algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Dfs {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute(nums);
        System.out.println(result);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        Deque deque = new LinkedList();
        int len = nums.length;
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, used, result, deque);

        return result;

    }

    public static void dfs(int[] nums, int len, int depth, boolean[] used, List<List<Integer>> result, Deque deque) {
        if (len == depth) {
            result.add(new ArrayList(deque));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            deque.addLast(nums[i]);
            dfs(nums, len, depth + 1, used, result, deque);
            used[i] = false;
            deque.removeLast();
        }
    }

}