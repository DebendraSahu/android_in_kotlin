package task.week1;


/**
 * **Task 9:** Implement polymorphism by creating a `Shape` class with a `draw()` method,
 * and then subclasses `Circle` and `Rectangle` that override the `draw()` method.
 */
public class W1t9 {
    public static void main(String[] args) {
        Shape shape1 = new Circle();
        Shape shape2 = new Rectangle();
        shape1.draw();
        shape2.draw();
    }
}

interface Shape {
    public void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle shape");
    }
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing Rectangle shape");
    }
}
