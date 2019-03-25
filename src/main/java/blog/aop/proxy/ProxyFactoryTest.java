package blog.aop.proxy;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

class ProxyFactoryTest {
    public static void main(String[] args) {

        /**
         * Interceptor VS Advice
         Advice是AOP编程中某一个方面(Aspect)在某个连接点(JoinPoint)所执行的特定动作，这个连接点(JoinPoint)可以是自 定义的；
         而Spring中的Interceptor更多关注程序运行时某些特定连接点(属性访问，对象构造，方法调用)时的动作。确切的 说，Interceptor的范围更窄一些。
         */
        //1.初始化源对象(一定要实现接口)
        UserService target = new UserServiceImpl();
        //2.AOP 代理工厂
        ProxyFactory pf = new ProxyFactory(target);
        //3.装配Advice
        pf.addAdvice(new SecurityInterceptor());
        //pf.addAdvice(new LoggerBeforeAdvice());
        pf.addAdvisor(new DefaultPointcutAdvisor(new LoggerBeforeAdvice()));
        ////4.获取代理对象
        UserService proxy = (UserService) pf.getProxy();
        //5.调用业务
        proxy.updateUser();
    }

}




