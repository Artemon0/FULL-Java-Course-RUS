
/**
 * Example 1: Простой класс - основы ООП
 */

// Класс Person
class Person {

    // Поля (атрибуты)
    String name;
    int age;
    String city;

    // Метод для вывода информации
    void displayInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Город: " + city);
        System.out.println();
    }

    // Метод для приветствия
    void greet() {
        System.out.println("Привет! Меня зовут " + name + ", мне " + age + " лет.");
    }

    // Метод для проверки совершеннолетия
    boolean isAdult() {
        return age >= 18;
    }

    // Метод для празднования дня рождения
    void celebrateBirthday() {
        age++;
        System.out.println("С днём рождения, " + name + "! Теперь тебе " + age + " лет!");
    }
}

public class Example01_SimpleClass {

    public static void main(String[] args) {
        System.out.println("=== СОЗДАНИЕ ОБЪЕКТОВ ===\n");

        // Создание первого объекта
        Person person1 = new Person();
        person1.name = "Иван";
        person1.age = 25;
        person1.city = "Москва";

        System.out.println("Объект person1:");
        person1.displayInfo();

        // Создание второго объекта
        Person person2 = new Person();
        person2.name = "Мария";
        person2.age = 22;
        person2.city = "Санкт-Петербург";

        System.out.println("Объект person2:");
        person2.displayInfo();

        // Создание третьего объекта
        Person person3 = new Person();
        person3.name = "Алексей";
        person3.age = 17;
        person3.city = "Казань";

        System.out.println("Объект person3:");
        person3.displayInfo();

        System.out.println("=== ВЫЗОВ МЕТОДОВ ===\n");

        person1.greet();
        person2.greet();
        person3.greet();

        System.out.println();

        System.out.println("=== ПРОВЕРКА СОВЕРШЕННОЛЕТИЯ ===\n");

        System.out.println(person1.name + " совершеннолетний? " + person1.isAdult());
        System.out.println(person2.name + " совершеннолетняя? " + person2.isAdult());
        System.out.println(person3.name + " совершеннолетний? " + person3.isAdult());

        System.out.println("\n=== ДЕНЬ РОЖДЕНИЯ ===\n");

        person3.celebrateBirthday();
        System.out.println(person3.name + " теперь совершеннолетний? " + person3.isAdult());
    }
}
