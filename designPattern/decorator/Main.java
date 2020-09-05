package designPattern.decorator;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/22 16:40
 * @description:
 */


public class Main {
    public static void main(String[] args) {
        Apple apple = new ConcreteApple();
        System.out.println("-----未装饰-----");
        apple.res();

        System.out.println("--------------");
        ConcreteAppleDecoratorA decoratorA = new ConcreteAppleDecoratorA(apple);
        System.out.println("-----装饰后-----");
        decoratorA.res();

    }
}


interface Apple{
    void res();
}

class ConcreteApple implements Apple {

    @Override
    public void res() {
        System.out.println("一个苹果");
    }
}

abstract class AppleDecorator implements Apple {
    protected Apple apple;

    public AppleDecorator(Apple apple) {
        super();
        this.apple = apple;
    }

    @Override
    public void res() {
        apple.res();
    }
}

class ConcreteAppleDecoratorA extends AppleDecorator {
    public ConcreteAppleDecoratorA(Apple apple) {
        super(apple);
    }

    public void resA() {
        System.out.print("装饰器A=>装饰成红色的-");
    }

    @Override
    public void res() {
        resA();
        super.res();

    }
}

class ConcreteAppleDecoratorB extends AppleDecorator {

    public ConcreteAppleDecoratorB(Apple apple) {
        super(apple);
    }

    public void resB(){
        System.out.println("装饰器B=>装饰成绿色的-");
    }

    @Override
    public void res(){
        resB();
        super.res();

    }
}