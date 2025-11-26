
import java.util.ArrayList;
import java.util.Collections;

/**
 * Example 1: ArrayList - динамический массив
 */
public class Example01_ArrayList {

    public static void main(String[] args) {
        System.out.println("=== СОЗДАНИЕ И ДОБАВЛЕНИЕ ===\n");

        ArrayList<String> fruits = new ArrayList<>();

        // Добавление элементов
        fruits.add("Яблоко");
        fruits.add("Банан");
        fruits.add("Апельсин");
        fruits.add("Груша");
        fruits.add("Киви");

        System.out.println("Список фруктов: " + fruits);
        System.out.println("Размер: " + fruits.size());
        System.out.println();

        System.out.println("=== ДОСТУП К ЭЛЕМЕНТАМ ===\n");

        System.out.println("Первый фрукт: " + fruits.get(0));
        System.out.println("Третий фрукт: " + fruits.get(2));
        System.out.println("Последний фрукт: " + fruits.get(fruits.size() - 1));
        System.out.println();

        System.out.println("=== ИЗМЕНЕНИЕ ЭЛЕМЕНТОВ ===\n");

        fruits.set(1, "Манго");
        System.out.println("После замены: " + fruits);
        System.out.println();

        System.out.println("=== УДАЛЕНИЕ ЭЛЕМЕНТОВ ===\n");

        fruits.remove("Киви");
        System.out.println("После удаления 'Киви': " + fruits);

        fruits.remove(0);
        System.out.println("После удаления первого: " + fruits);
        System.out.println();

        System.out.println("=== ПОИСК ЭЛЕМЕНТОВ ===\n");

        boolean hasOrange = fruits.contains("Апельсин");
        System.out.println("Есть апельсин? " + hasOrange);

        int index = fruits.indexOf("Груша");
        System.out.println("Индекс груши: " + index);
        System.out.println();

        System.out.println("=== ИТЕРАЦИЯ ===\n");

        System.out.println("Цикл for:");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(i + ". " + fruits.get(i));
        }

        System.out.println("\nЦикл for-each:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }
        System.out.println();

        System.out.println("=== СОРТИРОВКА ===\n");

        Collections.sort(fruits);
        System.out.println("Отсортировано: " + fruits);

        Collections.reverse(fruits);
        System.out.println("Обратный порядок: " + fruits);
        System.out.println();

        System.out.println("=== РАБОТА С ЧИСЛАМИ ===\n");

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i * 10);
        }

        System.out.println("Числа: " + numbers);

        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("Сумма: " + sum);

        int max = Collections.max(numbers);
        int min = Collections.min(numbers);
        System.out.println("Максимум: " + max);
        System.out.println("Минимум: " + min);
        System.out.println();

        System.out.println("=== ОЧИСТКА ===\n");

        fruits.clear();
        System.out.println("После очистки: " + fruits);
        System.out.println("Пустой? " + fruits.isEmpty());
    }
}
