package blog.aop.proxy;

/**
 * 模拟具体业务
 */
public class UserServiceImpl implements UserService {

    @Override
    public void updateUser() {
        System.out.println("$$$$$$执行业务逻辑$$$$$");
    }
}