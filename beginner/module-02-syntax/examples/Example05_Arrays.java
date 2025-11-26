
/**
 * Example 5: Массивы в Java - полное руководство
 */
public class Example05_Arrays {

    public static void main(String[] args) {
        System.out.println("=== ОДНОМЕРНЫЕ МАССИВЫ ===\n");

        // Создание массива
        int[] numbers = new int[5];
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
  numbers[3] = 40;
        numbers[4] = 50;
        
        System.out.println("Массив numbers:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("numbers[" + i + "] = " + numbers[i]);
        }
        System.out.println();
        
        // Инициализация при создании
        String[] fruits = {"Яблоко", "Банан", "Апельсин", "Груша", "Киви"};
        
        System.out.println("Массив fruits:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }
        System.out.println();
        
        // Массив дробных чисел
        double[] prices = {19.99, 29.99, 39.99, 49.99, 59.99};
        
        System.out.println("Цены товаров:");
        for (int i = 0; i < prices.length; i++) {
            System.out.printf("Товар %d: %.2f руб%n", i + 1, prices[i]);
        }
        System.out.println();
        
        System.out.println("=== ОПЕРАЦИИ С МАССИВАМИ ===\n");
        
        // Поиск максимума
        int[] values = {45, 23, 67, 12, 89, 34, 56};
        int max = values[0];
        for (int value : values) {
            if (value > max) {
                max = value;
            }
        }
        System.out.println("Массив: ");
        for (int v : values) System.out.print(v + " ");
        System.out.println("\nМаксимум: " + max);
        
        // Поиск минимума
        int min = values[0];
        for (int value : values) {
            if (value < min) {
                min = value;
            }
        }
        System.out.println("Минимум: " + min);
        
        // Сумма элементов
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        System.out.println("Сумма: " + sum);
        
        // Среднее значение
        double average = (double) sum / values.length;
        System.out.printf("Среднее: %.2f%n%n", average);
        
        System.out.println("=== ДВУМЕРНЫЕ МАССИВЫ ===\n");
        
        // Создание двумерного массива
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Матрица 3x3:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        
        // Таблица умножения
        int[][] multiplicationTable = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                multiplicationTable[i][j] = (i + 1) * (j + 1);
            }
        }
        
        System.out.println("Таблица умножения 10x10:");
        System.out.print("    ");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println("\n    " + "----".repeat(10));
        
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d |", i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.printf("%4d", multiplicationTable[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        
        System.out.println("=== ПРАКТИЧЕСКИЕ ПРИМЕРЫ ===\n");
        
        // Пример 1: Реверс массива
        int[] original = {1, 2, 3, 4, 5};
        int[] reversed = new int[original.length];
        
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        
        System.out.print("Оригинал: ");
        for (int n : original) System.out.print(n + " ");
        System.out.print("\nРеверс:   ");
        for (int n : reversed) System.out.print(n + " ");
        System.out.println("\n");
        
        // Пример 2: Сортировка пузырьком
        int[] unsorted = {64, 34, 25, 12, 22, 11, 90};
        System.out.print("До сортировки: ");
        for (int n : unsorted) System.out.print(n + " ");
        
        for (int i = 0; i < unsorted.length - 1; i++) {
            for (int j = 0; j < unsorted.length - i - 1; j++) {
                if (unsorted[j] > unsorted[j + 1]) {
                    int temp = unsorted[j];
                    unsorted[j] = unsorted[j + 1];
                    unsorted[j + 1] = temp;
                }
            }
        }
        
        System.out.print("\nПосле сортировки: ");
        for (int n : unsorted) System.out.print(n + " ");
        System.out.println("\n");
        
        // Пример 3: Поиск элемента
        int[] searchArray = {10, 20, 30, 40, 50};
        int searchValue = 30;
        int foundIndex = -1;
        
        for (int i = 0; i < searchArray.length; i++) {
            if (searchArray[i] == searchValue) {
                foundIndex = i;
                break;
            }
        }
        
        if (foundIndex != -1) {
            System.out.println("Элемент " + searchValue + " найден на позиции " + foundIndex);
        } else {
            System.out.println("Элемент не найден");
        }
        System.out.println();
        
        // Пример 4: Копирование массива
        int[] source = {1, 2, 3, 4, 5};
        int[] copy = new int[source.length];
        
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        
        System.out.print("Исходный: ");
        for (int n : source) System.out.print(n + " ");
        System.out.print("\nКопия:    ");
        for (int n : copy) System.out.print(n + " ");
        System.out.println("\n");
        
        // Пример 5: Подсчёт чётных и нечётных
        int[] mixedNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int evenCount = 0;
        int oddCount = 0;
        
        for (int num : mixedNumbers) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        
        System.out.println("Чётных чисел: " + evenCount);
        System.out.println("Нечётных чисел: " + oddCount);
    }
}
