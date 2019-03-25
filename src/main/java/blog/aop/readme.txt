Interceptor VS Advice

Advice是AOP编程中某一个方面(Aspect)在某个连接点(JoinPoint)所执行的特定动作，这个连接点(JoinPoint)可以是自定义的；
而Spring中的Interceptor更多关注程序运行时某些特定连接点(属性访问，对象构造，方法调用)时的动作。确切的 说，Interceptor的范围更窄一些。