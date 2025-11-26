
import java.util.HashMap;
import java.util.Map;

/**
 * Example 2: HashMap - пары ключ-значение
 */
public class Example02_HashMap {

    public static void main(String[] args) {
        System.out.println("=== СОЗДАНИЕ И ДОБАВЛЕНИЕ ===\n");

        HashMap<String, Integer> ages = new HashMap<>();

        ages.put("Иван", 25);
        ages.put("Мария", 30);
        ages.put("Петр", 28);
        ages.put("Анна", 22);
        ages.put("Дмитрий", 35);

        System.out.println("Возрасты: " + ages);
        System.out.println("Размер: " + ages.size());
        System.out.println();

        System.out.println("=== ПОЛУЧЕНИЕ ЗНАЧЕНИЙ ===\n");

        int ivanAge = ages.get("Иван");
        System.out.println("Возраст Ивана: " + ivanAge);

        int unknownAge = ages.getOrDefault("Сергей", 0);
        System.out.println("Возраст Сергея: " + unknownAge);
        System.out.println();

        System.out.println("=== ПРОВЕРКА НАЛИЧИЯ ===\n");

        boolean hasMaria = ages.containsKey("Мария");
        System.out.println("Есть Мария? " + hasMaria);

        boolean hasAge30 = ages.containsValue(30);
        System.out.println("Есть возраст 30? " + hasAge30);
        System.out.println();

        System.out.println("=== ОБНОВЛЕНИЕ ===\n");

        ages.put("Иван", 26);
        System.out.println("После обновления: " + ages.get("Иван"));

        ages.replace("Мария", 31);
        System.out.println("Мария теперь: " + ages.get("Мария"));
        System.out.println();

        System.out.println("=== ИТЕРАЦИЯ ПО КЛЮЧАМ ===\n");

        for (String name : ages.keySet()) {
            System.out.println(name);
        }
        System.out.println();

        System.out.println("=== ИТЕРАЦИЯ ПО ЗНАЧЕНИЯМ ===\n");

        for (Integer age : ages.values()) {
            System.out.println(age + " лет");
        }
        System.out.println();

        System.out.println("=== ИТЕРАЦИЯ ПО ПАРАМ ===\n");

        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " лет");
        }
        System.out.println();

        System.out.println("=== УДАЛЕНИЕ ===\n");

        ages.remove("Петр");
        System.out.println("После удаления Петра: " + ages);
        System.out.println();

        System.out.println("=== ПРАКТИЧЕСКИЙ ПРИМЕР ===\n");

        HashMap<String, Double> products = new HashMap<>();
        products.put("Хлеб", 45.50);
        products.put("Молоко", 85.00);
        products.put("Яйца", 120.00);
        products.put("Масло", 95.50);

        System.out.println("Прайс-лист:");
        for (Map.Entry<String, Double> product : products.entrySet()) {
            System.out.printf("%s: %.2f руб%n", product.getKey(), product.getValue());
        }

        double total = 0;
        for (double price : products.values()) {
            total += price;
        }
        System.out.printf("\nОбщая стоимость: %.2f руб%n", total);
    }
}
