package algorithm.string;


/**
 * @author zhangshaolin
 * @create 2020/2/27
 */
public class StringReverse {
    public static void main(String[] args) {
        String source = "hello word abc";
        System.out.println(reverse(source));

    }

    public static String reverse(String source) {
        char[] sources = source.toCharArray();
        reverse(sources, 0, source.length() - 1);

        int left = -1;
        int right = -1;
        //ddd
        for (int i = 0; i < sources.length; i++) {
            if (i == 0 || sources[i - 1] == ' ') {
                left = i;
            }
            if (i == sources.length - 1 || sources[i + 1] == ' ') {
                right = i;
            }
            if (right != -1) {
                reverse(sources, left, right);
                left = -1;
                right = -1;
            }

        }
        System.out.println(sources);
        return sources.toString();
    }

    private static void reverse(char[] sources, int left, int right) {
        while (left < right) {
            char tmp = sources[left];
            sources[left] = sources[right];
            sources[right] = tmp;
            left++;
            right--;
        }
    }
}