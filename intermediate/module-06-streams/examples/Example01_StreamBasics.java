
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Example 1: Stream API - основы
 */
public class Example01_StreamBasics {

    public static void main(String[] args) {
        System.out.println("=== ФИЛЬТРАЦИЯ ===\n");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Исходный список: " + numbers);
        System.out.println("Чётные числа: " + evenNumbers);
        System.out.println();

        System.out.println("=== ПРЕОБРАЗОВАНИЕ (MAP) ===\n");

        List<Integer> doubled = numbers.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());

        System.out.println("Удвоенные: " + doubled);

        List<Integer> squared = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println("Квадраты: " + squared);
        System.out.println();

        System.out.println("=== СОРТИРОВКА ===\n");

        List<Integer> unsorted = Arrays.asList(5, 2, 8, 1, 9, 3);

        List<Integer> sorted = unsorted.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Несортированный: " + unsorted);
        System.out.println("Отсортированный: " + sorted);
        System.out.println();

        System.out.println("=== ОГРАНИЧЕНИЕ И ПРОПУСК ===\n");

        List<Integer> first5 = numbers.stream()
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Первые 5: " + first5);

        List<Integer> skip5 = numbers.stream()
                .skip(5)
                .collect(Collectors.toList());

        System.out.println("Пропустить 5: " + skip5);
        System.out.println();

        System.out.println("=== УНИКАЛЬНЫЕ ЭЛЕМЕНТЫ ===\n");

        List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 5);

        List<Integer> unique = withDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("С дубликатами: " + withDuplicates);
        System.out.println("Уникальные: " + unique);
        System.out.println();

        System.out.println("=== ПОДСЧЁТ И СУММА ===\n");

        long count = numbers.stream()
                .filter(n -> n > 5)
                .count();

        System.out.println("Чисел больше 5: " + count);

        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Сумма всех чисел: " + sum);

        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

        System.out.println("Среднее: " + average);
        System.out.println();

        System.out.println("=== ПРОВЕРКИ ===\n");

        boolean allPositive = numbers.stream()
                .allMatch(n -> n > 0);

        System.out.println("Все положительные? " + allPositive);

        boolean anyGreaterThan5 = numbers.stream()
                .anyMatch(n -> n > 5);

        System.out.println("Есть больше 5? " + anyGreaterThan5);

        boolean noneNegative = numbers.stream()
                .noneMatch(n -> n < 0);

        System.out.println("Нет отрицательных? " + noneNegative);
        System.out.println();

        System.out.println("=== РАБОТА СО СТРОКАМИ ===\n");

        List<String> words = Arrays.asList("Java", "Python", "JavaScript", "C++", "Ruby");

        List<String> longWords = words.stream()
                .filter(w -> w.length() > 4)
                .collect(Collectors.toList());

        System.out.println("Слова длиннее 4 символов: " + longWords);

        List<String> uppercase = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("В верхнем регистре: " + uppercase);

        String joined = words.stream()
                .collect(Collectors.joining(", "));

        System.out.println("Объединённые: " + joined);
    }
}
