package designPattern.proxy;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/27 20:13
 * @description:
 */


public class DynamicProxy {
    public static void main(String[] args) {
        DynamicInvocationHandler h = new DynamicInvocationHandler(new SmsServiceImpl());

        DynamicProxy dynamicProxy = (DynamicProxy) JdkProxyFactory.getProxy(SmsService.class);

    }
}

