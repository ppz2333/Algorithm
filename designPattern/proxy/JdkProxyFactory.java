package designPattern.proxy;

import java.lang.reflect.Proxy;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/27 20:18
 * @description:
 */


public class JdkProxyFactory {
    //getProxy() ：主要通过Proxy.newProxyInstance（）方法获取某个类的代理对象
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载
                target.getClass().getInterfaces(),  // 代理需要实现的接口，可指定多个
                new DynamicInvocationHandler(target)   // 代理对象对应的自定义 InvocationHandler
        );
    }
}
