package algorithm.dynamic.bag;

/**
 * Created by zhangshaolin on 2017/12/5.
 */
public class PackTest {


    public static void main(String[] args) {

        String[] nameArr = {"a", "b", "c", "d", "e"};
        Integer[] weightArr = {2, 2, 6, 5, 4};
        Integer[] valueArr = {6, 3, 5, 4, 6};
        PackageItem[] bagItems = {};
        for (int i = 0; i < nameArr.length; i++) {
            PackageItem bagItem = new PackageItem(nameArr[i], weightArr[i], valueArr[i]);
            bagItems[i] = bagItem;
        }
        Integer[][] arr = get01PackageAnswer(bagItems, 10);
    }

    private static Integer[][] get01PackageAnswer(PackageItem[] bagItems, int i) {
        return new Integer[0][];
    }
}
