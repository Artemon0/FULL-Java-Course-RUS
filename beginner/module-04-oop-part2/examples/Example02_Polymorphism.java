
/**
 * Example 2: Полиморфизм - один интерфейс, много реализаций
 */

class Shape {

    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public double getArea() {
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }

    public void displayInfo() {
        System.out.println("Цвет: " + color);
        System.out.printf("Площадь: %.2f%n", getArea());
        System.out.printf("Периметр: %.2f%n", getPerimeter());
    }
}

class Circle extends Shape {

    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void displayInfo() {
        System.out.println("Фигура: Круг");
        System.out.println("Радиус: " + radius);
        super.displayInfo();
    }
}

class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void displayInfo() {
        System.out.println("Фигура: Прямоугольник");
        System.out.println("Ширина: " + width);
        System.out.println("Высота: " + height);
        super.displayInfo();
    }
}

class Triangle extends Shape {

    private double a, b, c;

    public Triangle(String color, double a, double b, double c) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public void displayInfo() {
        System.out.println("Фигура: Треугольник");
        System.out.printf("Стороны: %.1f, %.1f, %.1f%n", a, b, c);
        super.displayInfo();
    }
}

public class Example02_Polymorphism {

    public static void main(String[] args) {
        System.out.println("=== СОЗДАНИЕ ФИГУР ===\n");

        Circle circle = new Circle("Красный", 5.0);
        Rectangle rectangle = new Rectangle("Синий", 4.0, 6.0);
        Triangle triangle = new Triangle("Зелёный", 3.0, 4.0, 5.0);

        circle.displayInfo();
        System.out.println();

        rectangle.displayInfo();
        System.out.println();

        triangle.displayInfo();
        System.out.println();

        System.out.println("=== ПОЛИМОРФИЗМ В ДЕЙСТВИИ ===\n");

        Shape[] shapes = {circle, rectangle, triangle};

        double totalArea = 0;
        double totalPerimeter = 0;

        for (Shape shape : shapes) {
            totalArea += shape.getArea();
            totalPerimeter += shape.getPerimeter();
        }

        System.out.printf("Общая площадь всех фигур: %.2f%n", totalArea);
        System.out.printf("Общий периметр всех фигур: %.2f%n", totalPerimeter);

        System.out.println("\n=== ОБРАБОТКА РАЗНЫХ ТИПОВ ===\n");

        for (int i = 0; i < shapes.length; i++) {
            System.out.println("Фигура " + (i + 1) + ":");
            shapes[i].displayInfo();
            System.out.println();
        }
    }
}
