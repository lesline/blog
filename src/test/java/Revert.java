public class Revert {
    //反转英文句子 how old are you为you are old how，不能使用string split函数

    public static void main(String[] args) {
//        String a = "how old are you";
        String a = "a good   example";
        String b = revert(a);
        System.out.println(b.toCharArray());
    }

    private static String revert(String s) {
        char[] c = trimSpace(s).toCharArray();
        revert(c, 0, c.length - 1);
        int left = -1, right = -1;
        for (int i = 0; i < c.length; i++) {

            if (i == 0 || c[i - 1] == ' ') {
                left = i;
            }
            if (i == c.length - 1 || c[i + 1] == ' ') {
                right = i;
            }

            if (right != -1) {
                revert(c, left, right);
                left = -1;
                right = -1;
            }
        }
        return new String(c);
    }

    private static void revert(char[] str, int left, int right) {
        while (left < right) {
            char tmp = str[left];
            str[left] = str[right];
            str[right] = tmp;
            left++;
            right--;
        }
    }

    private static String trimSpace(String s) {

        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }
        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            ++left;
        }
        return sb.toString();
    }
}
