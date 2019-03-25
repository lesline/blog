package blog.string;


public class Test1 {
    public static void main(String[] args) {


        String c = "str";
        String a = new StringBuilder("s").append("tr").toString();

        System.out.println(a.intern() == "str");
        System.out.println(a.intern() == c);


    }

}
