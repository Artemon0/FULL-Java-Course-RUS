
/**
 * Example 4: Цикл while и do-while
 */
public class Example04_WhileLoop {

    public static void main(String[] args) {
        System.out.println("=== ЦИКЛ WHILE ===\n");

        // Простой цикл while
        int i = 1;
        while (i <= 5) {
            System.out.println("Итерация " + i);
            i++;
        }
        System.out.println();

        // Обратный отсчёт
        int count = 10;
        System.out.println("Обратный отсчёт:");
        while (count > 0) {
            System.out.print(count + " ");
            count--;
        }
        System.out.println("Пуск!\n");

        // Сумма чисел
        int sum = 0;
        int num = 1;
        while (num <= 10) {
            sum += num;
            num++;
        }
        System.out.println("Сумма чисел от 1 до 10: " + sum + "\n");

        System.out.println("=== ЦИКЛ DO-WHILE ===\n");

        // Выполнится минимум один раз
        int j = 10;
        do {
            System.out.println("j = " + j);
            j++;
        } while (j < 10);
        System.out.println();

        // Факториал
        int n = 5;
        long factorial = 1;
        int k = 1;
        do {
            factorial *= k;
            k++;
        } while (k <= n);
        System.out.println(n + "! = " + factorial + "\n");

        System.out.println("=== ПРАКТИЧЕСКИЕ ПРИМЕРЫ ===\n");

        // Пример 1: Поиск первого числа, делящегося на 7
        int number = 1;
        while (number % 7 != 0) {
            number++;
        }
        System.out.println("Первое число, делящееся на 7: " + number);

        // Пример 2: Степени двойки меньше 1000
        System.out.println("\nСтепени двойки меньше 1000:");
        int power = 1;
        while (power < 1000) {
            System.out.print(power + " ");
            power *= 2;
        }
        System.out.println("\n");

        // Пример 3: Переворот числа
        int original = 12345;
        int reversed = 0;
        int temp = original;
        while (temp > 0) {
            int digit = temp % 10;
            reversed = reversed * 10 + digit;
            temp /= 10;
        }
        System.out.println("Число " + original + " наоборот: " + reversed);
    }
}
