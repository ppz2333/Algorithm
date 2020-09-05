package designPattern.bridge;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/22 15:56
 * @description: 桥接（Bridge）是用于把抽象化与实现化解耦，使得二者可以独立变化。这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
 *
 * 这种模式涉及到一个作为桥接的接口，使得实体类的功能独立于接口实现类。这两种类型的类可被结构化改变而互不影响。
 *
 * 优点： 1、抽象和实现的分离。 2、优秀的扩展能力。 3、实现细节对客户透明。
 *
 * 缺点：桥接模式的引入会增加系统的理解与设计难度，由于聚合关联关系建立在抽象层，要求开发者针对抽象进行设计与编程。
 *
 * 使用场景： 1、如果一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性，避免在两个层次之间建立静态的继承联系，
 * 通过桥接模式可以使它们在抽象层建立一个关联关系。
 * 2、对于那些不希望使用继承或因为多层次继承导致系统类的个数急剧增加的系统，桥接模式尤为适用。
 * 3、一个类存在两个独立变化的维度，且这两个维度都需要进行扩展。
 *
 * 注意事项：对于两个独立变化的维度，使用桥接模式再适合不过了。
 */

//使用 Shape 和 DrawAPI 类画出不同颜色的圆。
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}

//创建桥接实现接口。
interface DrawAPI {
    public void drawCircle(int radius, int x, int y);
}

//创建实现了 DrawAPI 接口的实体桥接实现类。
class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}

class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}

//使用 DrawAPI 接口创建抽象类 Shape。
abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}

//创建实现了 Shape 的实体类。
class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}


