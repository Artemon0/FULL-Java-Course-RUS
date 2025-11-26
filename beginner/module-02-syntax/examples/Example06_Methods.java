
/**
 * Example 6: Методы в Java - полное руководство
 */
public class Example06_Methods {

    public static void main(String[] args) {
        System.out.println("=== МЕТОДЫ БЕЗ ПАРАМЕТРОВ ===\n");

        printWelcome();
        printSeparator();

        System.out.println("\n=== МЕТОДЫ С ПАРАМЕТРАМИ ===\n");

        greet("Иван");
        greet("Мария");

        printNumber(42);
        printNumber(100);

        System.out.println();
        printSum(10, 20);
        printSum(5, 15);

        System.out.println(

    "\n=== МЕТОДЫ С ВОЗВРАТОМ ЗНАЧЕНИЯ ===\n");
        
        int result1 = add(5, 3);
        System.out.println("5 + 3 = " + result1);
        
        int result2 = multiply(4, 7);
        System.out.println("4 × 7 = " + result2);
        
        double area = calculateCircleArea(5.0);
        System.out.printf("Площадь круга (r=5): %.2f%n", area);
        
        System.out.println("\n=== МЕТОДЫ С НЕСКОЛЬКИМИ ПАРАМЕТРАМИ ===\n");
        
        int max = findMax(10, 25, 15);
        System.out.println("Максимум из (10, 25, 15): " + max);
        
        double avg = calculateAverage(10, 20, 30, 40, 50);
        System.out.println("Среднее (10,20,30,40,50): " + avg);
        
        System.out.println("\n=== ПЕРЕГРУЗКА МЕТОДОВ ===\n");
        
        System.out.println("Площадь квадрата (5): " + calculateArea(5));
        System.out.println("Площадь прямоугольника (4,6): " + calculateArea(4, 6));
        System.out.println("Площадь круга (3.0): " + calculateArea(3.0));
        
        System.out.println("\n=== РЕКУРСИВНЫЕ МЕТОДЫ ===\n");
        
        int factorial5 = factorial(5);
        System.out.println("5! = " + factorial5);
        
        int fib7 = fibonacci(7);
        System.out.println("Fibonacci(7) = " + fib7);
        
        System.out.println("\n=== МЕТОДЫ С МАССИВАМИ ===\n");
        
        int[] numbers = {5, 2, 8, 1, 9};
        printArray(numbers);
        
        int sum = sumArray(numbers);
        System.out.println("Сумма элементов: " + sum);
        
        int maxNum = findMaxInArray(numbers);
        System.out.println("Максимум: " + maxNum);
        
        System.out.println("\n=== ПРАКТИЧЕСКИЕ ПРИМЕРЫ ===\n");
        
        // Проверка простого числа
        System.out.println("17 простое? " + isPrime(17));
        System.out.println("20 простое? " + isPrime(20));
        
        // Проверка палиндрома
        System.out.println("'radar' палиндром? " + isPalindrome("radar"));
        System.out.println("'hello' палиндром? " + isPalindrome("hello"));
        
        // Конвертация температуры
        double celsius = 25.0;
        double fahrenheit = celsiusToFahrenheit(celsius);
        System.out.printf("%.1f°C = %.1f°F%n", celsius, fahrenheit);
    }
    
    // Метод без параметров и возврата
    public static void printWelcome() {
        System.out.println("Добро пожаловать в программу!");
    }
    
    public static void printSeparator() {
        System.out.println("================================");
    }
    
    // Методы с параметрами
    public static void greet(String name) {
        System.out.println("Привет, " + name + "!");
    }
    
    public static void printNumber(int number) {
        System.out.println("Число: " + number);
    }
    
    public static void printSum(int a, int b) {
        System.out.println(a + " + " + b + " = " + (a + b));
    }
    
    // Методы с возвратом значения
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    // Методы с несколькими параметрами
    public static int findMax(int a, int b, int c) {
        int max = a;
        if (b > max) max = b;
        if (c > max) max = c;
        return max;
    }
    
    public static double calculateAverage(int... numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.length;
    }
    
    // Перегрузка методов
    public static int calculateArea(int side) {
        return side * side;
    }
    
    public static int calculateArea(int width, int height) {
        return width * height;
    }
    
    public static double calculateArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    // Рекурсивные методы
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // Методы с массивами
    public static void printArray(int[] array) {
        System.out.print("Массив: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static int sumArray(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }
    
    public static int findMaxInArray(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    
    // Практические методы
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32.0;
    }
}
