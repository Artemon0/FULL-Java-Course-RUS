
/**
 * Example 4: Статические члены класса
 */

class Counter {

    private static int totalCount = 0;  // Статическое поле
    private int instanceCount;           // Поле экземпляра

    public Counter() {
        totalCount++;
        instanceCount = totalCount;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public static void resetTotalCount() {
        totalCount = 0;
    }
}

class MathUtils {

    public static final double PI = 3.14159265359;
    public static final double E = 2.71828182846;

    public static int add(int a, int b) {
        return a + b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static double power(double base, int exponent) {
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

class Employee {

    private static int nextId = 1000;
    private static int employeeCount = 0;

    private int id;
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.id = nextId++;
        this.name = name;
        this.salary = salary;
        employeeCount++;
    }

    public void displayInfo() {
        System.out.printf("ID: %d, Имя: %s, Зарплата: %.2f%n", id, name, salary);
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    public static int getNextId() {
        return nextId;
    }
}

public class Example04_StaticMembers {

    public static void main(String[] args) {
        System.out.println("=== СТАТИЧЕСКИЕ ПОЛЯ И МЕТОДЫ ===\n");

        // Использование статических констант
        System.out.println("Константы MathUtils:");
        System.out.println("PI = " + MathUtils.PI);
        System.out.println("E = " + MathUtils.E);
        System.out.println();

        // Использование статических методов
        System.out.println("Статические методы MathUtils:");
        System.out.println("5 + 3 = " + MathUtils.add(5, 3));
        System.out.println("4 * 7 = " + MathUtils.multiply(4, 7));
        System.out.println("2^10 = " + MathUtils.power(2, 10));
        System.out.println("5! = " + MathUtils.factorial(5));
        System.out.println();

        System.out.println("=== СЧЁТЧИК ОБЪЕКТОВ ===\n");

        System.out.println("Создаём объекты Counter:");
        Counter c1 = new Counter();
        System.out.println("c1 создан. Номер: " + c1.getInstanceCount());
        System.out.println("Всего создано: " + Counter.getTotalCount());
        System.out.println();

        Counter c2 = new Counter();
        System.out.println("c2 создан. Номер: " + c2.getInstanceCount());
        System.out.println("Всего создано: " + Counter.getTotalCount());
        System.out.println();

        Counter c3 = new Counter();
        System.out.println("c3 создан. Номер: " + c3.getInstanceCount());
        System.out.println("Всего создано: " + Counter.getTotalCount());
        System.out.println();

        System.out.println("=== АВТОИНКРЕМЕНТ ID ===\n");

        System.out.println("Создаём сотрудников:");
        Employee emp1 = new Employee("Иван Петров", 50000);
        emp1.displayInfo();

        Employee emp2 = new Employee("Мария Сидорова", 60000);
        emp2.displayInfo();

        Employee emp3 = new Employee("Алексей Иванов", 55000);
        emp3.displayInfo();

        System.out.println("\nВсего сотрудников: " + Employee.getEmployeeCount());
        System.out.println("Следующий ID: " + Employee.getNextId());
    }
}
