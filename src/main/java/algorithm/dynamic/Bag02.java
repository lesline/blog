package algorithm.dynamic;

/**
 * @author zhangshaolin
 * @create 2020/3/8
 */
public class Bag02 {

    // 回溯算法实现。注意:我把输入的变量都定义成了成员变量。
    private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
    private int[] weight = {2, 2, 4, 6, 3}; // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    public void f(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第i个物品

        }
    }


    public static void main(String[] args) {
        Bag01 bag01 = new Bag01();


        int[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        bag01.f(0, 0, items, 10, 100);
        System.out.println(bag01.maxW);

    }

}