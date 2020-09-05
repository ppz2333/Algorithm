package designPattern.prototype;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/20 20:26
 * @description: 原型模式通常适用于以下场景。
 * 对象之间相同或相似，即只是个别的几个属性不同的时候。
 * 对象的创建过程比较麻烦，但复制比较简单的时候。
 */


public class ProtoTypeCitation {
    public static void main(String[] args) throws CloneNotSupportedException{
        Citation obj1 = new Citation("张三","同学：在2020学年第一学期中表现优秀，被评为三好学生。","XX小学");
        obj1.display();
        Citation obj2 = (Citation) obj1.clone();
        obj2.setName("李四");
        obj2.display();

    }
}


//奖状类
class Citation implements Cloneable {
    String name;
    String info;
    String college;
    Citation(String name, String info, String college) {
        this.name = name;
        this.info = info;
        this.college = college;
        System.out.println("奖状创建成功！");
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    void display() {
        System.out.println(name + info + college);
    }

    public Object clone() throws CloneNotSupportedException {
        System.out.println("奖状拷贝成功！");
        return (Citation) super.clone();
    }

}
