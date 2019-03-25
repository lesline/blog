package blog.aop.advice;

/**
 * Created by zhangshaolin on 2017/7/21.
 */
public class Customer {
    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
