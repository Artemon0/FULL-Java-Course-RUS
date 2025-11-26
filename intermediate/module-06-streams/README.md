# Module 6: Stream API и функциональное программирование

```
╔════════════════════════════════════════════════════════════╗
║  MODULE 6: STREAM API                                      ║
║  Уровень: Intermediate                                     ║
║  Время: 2-3 недели                                         ║
╚════════════════════════════════════════════════════════════╝
```

## 📖 Теория

### 6.1 Что такое Stream API?

**Stream API** (Java 8+) - мощный инструмент для обработки коллекций в функциональном стиле.

```
Коллекция → Stream → Промежуточные операции → Терминальная операция → Результат
```

**Преимущества:**
- 📝 Декларативный код (что делать, а не как)
- 🔄 Цепочки операций
- ⚡ Ленивые вычисления
- 🧵 Параллельная обработка

### 6.2 Создание Stream

```java
// Из коллекции
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();

// Из массива
String[] array = {"a", "b", "c"};
Stream<String> stream = Arrays.stream(array);

// Напрямую
Stream<String> stream = Stream.of("a", "b", "c");

// Бесконечный stream
Stream<Integer> infinite = Stream.iterate(0, n -> n + 1);

// Генерация
Stream<Double> random = Stream.generate(Math::random);
```

### 6.3 Промежуточные операции (Intermediate)

Возвращают новый Stream, выполняются лениво.

```
filter()    - фильтрация элементов
map()       - преобразование элементов
flatMap()   - преобразование + объединение
distinct()  - удаление дубликатов
sorted()    - сортировка
limit()     - ограничение количества
skip()      - пропуск элементов
peek()      - выполнение действия (для отладки)
```

### 6.4 Терминальные операции (Terminal)

Запускают обработку и возвращают результат.

```
forEach()       - выполнить действие для каждого
collect()       - собрать в коллекцию
reduce()        - свести к одному значению
count()         - подсчитать количество
anyMatch()      - есть ли хотя бы один
allMatch()      - все ли соответствуют
noneMatch()     - ни один не соответствует
findFirst()     - найти первый
findAny()       - найти любой
min() / max()   - минимум / максимум
```

### 6.5 Лямбда-выражения

```java
// Старый способ (анонимный класс)
Comparator<String> comp = new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
};

// Лямбда-выражение
Comparator<String> comp = (s1, s2) -> s1.length() - s2.length();
```

**Синтаксис:**
```java
(параметры) -> выражение
(параметры) -> { блок кода }
```

### 6.6 Функциональные интерфейсы

```java
@FunctionalInterface
interface MyFunction {
    int apply(int x);
}

// Использование
MyFunction square = x -> x * x;
System.out.println(square.apply(5));  // 25
```

**Встроенные функциональные интерфейсы:**
```
Predicate<T>      - T -> boolean
Function<T, R>    - T -> R
Consumer<T>       - T -> void
Supplier<T>       - () -> T
UnaryOperator<T>  - T -> T
BinaryOperator<T> - (T, T) -> T
```

### 6.7 Optional

**Optional** - контейнер для значения, которое может отсутствовать.

```java
Optional<String> optional = Optional.of("value");
Optional<String> empty = Optional.empty();

// Проверка наличия
if (optional.isPresent()) {
    System.out.println(optional.get());
}

// Или лучше
optional.ifPresent(System.out::println);

// Значение по умолчанию
String value = optional.orElse("default");
String value = optional.orElseGet(() -> "default");
```

---

## 💻 Примеры кода

### Пример 1: Базовые операции Stream
```java
import java.util.*;
import java.util.stream.*;

public class BasicStreamOperations {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Фильтрация чётных чисел
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Чётные: " + evenNumbers);
        
        // Умножение каждого на 2
        List<Integer> doubled = numbers.stream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("Удвоенные: " + doubled);
        
        // Сумма всех чисел
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        System.out.println("Сумма: " + sum);
        
        // Или проще
        int sum2 = numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
        
        // Количество элементов больше 5
        long count = numbers.stream()
            .filter(n -> n > 5)
            .count();
        System.out.println("Больше 5: " + count);
    }
}
```

### Пример 2: Работа со строками
```java
public class StringStreamExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList(
            "Java", "Python", "JavaScript", "C++", "Ruby", "Go"
        );
        
        // Фильтр и сортировка
        List<String> result = words.stream()
            .filter(w -> w.length() > 4)
            .sorted()
            .collect(Collectors.toList());
        System.out.println(result);
        
        // Преобразование в верхний регистр
        List<String> uppercase = words.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println(uppercase);
        
        // Объединение в строку
        String joined = words.stream()
            .collect(Collectors.joining(", "));
        System.out.println(joined);
        
        // Проверки
        boolean hasJava = words.stream()
            .anyMatch(w -> w.equals("Java"));
        
        boolean allLong = words.stream()
            .allMatch(w -> w.length() > 2);
        
        boolean noneEmpty = words.stream()
            .noneMatch(String::isEmpty);
    }
}
```

### Пример 3: Работа с объектами
```java
class Person {
    private String name;
    private int age;
    private String city;
    
    // конструктор, геттеры
}

public class ObjectStreamExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Иван", 25, "Москва"),
            new Person("Мария", 30, "Санкт-Петербург"),
            new Person("Петр", 22, "Москва"),
            new Person("Анна", 28, "Казань")
        );
        
        // Фильтрация по возрасту
        List<Person> adults = people.stream()
            .filter(p -> p.getAge() >= 25)
            .collect(Collectors.toList());
        
        // Получение списка имён
        List<String> names = people.stream()
            .map(Person::getName)
            .collect(Collectors.toList());
        
        // Средний возраст
        double averageAge = people.stream()
            .mapToInt(Person::getAge)
            .average()
            .orElse(0.0);
        
        // Группировка по городу
        Map<String, List<Person>> byCity = people.stream()
            .collect(Collectors.groupingBy(Person::getCity));
        
        // Самый старший
        Optional<Person> oldest = people.stream()
            .max(Comparator.comparingInt(Person::getAge));
        
        oldest.ifPresent(p -> 
            System.out.println("Самый старший: " + p.getName())
        );
    }
}
```

### Пример 4: flatMap
```java
public class FlatMapExample {
    public static void main(String[] args) {
        List<List<Integer>> nested = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5),
            Arrays.asList(6, 7, 8, 9)
        );
        
        // Преобразование вложенного списка в плоский
        List<Integer> flat = nested.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println(flat);  // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        
        // Разбиение строк на слова
        List<String> sentences = Arrays.asList(
            "Hello World",
            "Java Stream API",
            "Functional Programming"
        );
        
        List<String> words = sentences.stream()
            .flatMap(s -> Arrays.stream(s.split(" ")))
            .collect(Collectors.toList());
        System.out.println(words);
    }
}
```

### Пример 5: Collectors
```java
public class CollectorsExample {
    public static void main(String[] args) {
        List<Person> people = getPeople();
        
        // В List
        List<String> names = people.stream()
            .map(Person::getName)
            .collect(Collectors.toList());
        
        // В Set
        Set<String> cities = people.stream()
            .map(Person::getCity)
            .collect(Collectors.toSet());
        
        // В Map
        Map<String, Integer> nameToAge = people.stream()
            .collect(Collectors.toMap(
                Person::getName,
                Person::getAge
            ));
        
        // Группировка
        Map<String, List<Person>> byCity = people.stream()
            .collect(Collectors.groupingBy(Person::getCity));
        
        // Подсчёт по группам
        Map<String, Long> countByCity = people.stream()
            .collect(Collectors.groupingBy(
                Person::getCity,
                Collectors.counting()
            ));
        
        // Разделение на две группы
        Map<Boolean, List<Person>> partitioned = people.stream()
            .collect(Collectors.partitioningBy(p -> p.getAge() >= 25));
        
        List<Person> adults = partitioned.get(true);
        List<Person> young = partitioned.get(false);
        
        // Статистика
        IntSummaryStatistics stats = people.stream()
            .mapToInt(Person::getAge)
            .summaryStatistics();
        
        System.out.println("Средний возраст: " + stats.getAverage());
        System.out.println("Минимум: " + stats.getMin());
        System.out.println("Максимум: " + stats.getMax());
    }
}
```

### Пример 6: reduce
```java
public class ReduceExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Сумма
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        System.out.println("Сумма: " + sum);
        
        // Произведение
        int product = numbers.stream()
            .reduce(1, (a, b) -> a * b);
        System.out.println("Произведение: " + product);
        
        // Максимум
        Optional<Integer> max = numbers.stream()
            .reduce((a, b) -> a > b ? a : b);
        max.ifPresent(m -> System.out.println("Максимум: " + m));
        
        // Конкатенация строк
        List<String> words = Arrays.asList("Java", "Stream", "API");
        String concatenated = words.stream()
            .reduce("", (a, b) -> a + " " + b);
        System.out.println(concatenated.trim());
    }
}
```

### Пример 7: Лямбда-выражения
```java
public class LambdaExample {
    public static void main(String[] args) {
        // Без параметров
        Runnable r = () -> System.out.println("Hello");
        
        // Один параметр (скобки можно опустить)
        Consumer<String> print = s -> System.out.println(s);
        print.accept("Hello");
        
        // Несколько параметров
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println(add.apply(5, 3));
        
        // Блок кода
        Predicate<Integer> isEven = n -> {
            System.out.println("Проверка " + n);
            return n % 2 == 0;
        };
        
        // Ссылки на методы
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        
        // Метод экземпляра
        words.forEach(System.out::println);
        
        // Статический метод
        List<Integer> numbers = Arrays.asList("1", "2", "3").stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        
        // Конструктор
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
    }
}
```

### Пример 8: Optional
```java
public class OptionalExample {
    public static void main(String[] args) {
        // Создание Optional
        Optional<String> optional = Optional.of("value");
        Optional<String> empty = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);
        
        // Проверка наличия
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
        
        // ifPresent
        optional.ifPresent(v -> System.out.println("Значение: " + v));
        
        // orElse
        String value1 = empty.orElse("default");
        
        // orElseGet (ленивое вычисление)
        String value2 = empty.orElseGet(() -> "default");
        
        // orElseThrow
        String value3 = optional.orElseThrow(() -> 
            new IllegalStateException("Значение отсутствует")
        );
        
        // map
        Optional<Integer> length = optional.map(String::length);
        
        // filter
        Optional<String> filtered = optional.filter(s -> s.length() > 3);
        
        // flatMap
        Optional<String> result = optional.flatMap(s -> 
            Optional.of(s.toUpperCase())
        );
    }
    
    // Использование Optional в методах
    public Optional<Person> findPersonByName(String name) {
        // поиск в базе данных
        Person person = database.find(name);
        return Optional.ofNullable(person);
    }
    
    public void processPersonOptional() {
        findPersonByName("Иван")
            .map(Person::getAge)
            .filter(age -> age >= 18)
            .ifPresent(age -> System.out.println("Взрослый: " + age));
    }
}
```

### Пример 9: Параллельные Stream
```java
public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, 1000000)
            .boxed()
            .collect(Collectors.toList());
        
        // Последовательная обработка
        long start = System.currentTimeMillis();
        long sum1 = numbers.stream()
            .mapToLong(Integer::longValue)
            .sum();
        long time1 = System.currentTimeMillis() - start;
        System.out.println("Последовательно: " + time1 + " мс");
        
        // Параллельная обработка
        start = System.currentTimeMillis();
        long sum2 = numbers.parallelStream()
            .mapToLong(Integer::longValue)
            .sum();
        long time2 = System.currentTimeMillis() - start;
        System.out.println("Параллельно: " + time2 + " мс");
        
        // Внимание: не всегда быстрее!
        // Используйте для больших объёмов данных и тяжёлых операций
    }
}
```

### Пример 10: Комплексный пример - анализ данных
```java
class Transaction {
    private String id;
    private double amount;
    private String category;
    private LocalDate date;
    
    // конструктор, геттеры
}

public class TransactionAnalysis {
    public static void main(String[] args) {
        List<Transaction> transactions = getTransactions();
        
        // Общая сумма транзакций
        double total = transactions.stream()
            .mapToDouble(Transaction::getAmount)
            .sum();
        
        // Транзакции больше 1000
        List<Transaction> large = transactions.stream()
            .filter(t -> t.getAmount() > 1000)
            .collect(Collectors.toList());
        
        // Группировка по категориям
        Map<String, Double> sumByCategory = transactions.stream()
            .collect(Collectors.groupingBy(
                Transaction::getCategory,
                Collectors.summingDouble(Transaction::getAmount)
            ));
        
        // Топ-5 самых больших транзакций
        List<Transaction> top5 = transactions.stream()
            .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
            .limit(5)
            .collect(Collectors.toList());
        
        // Средняя сумма по категориям
        Map<String, Double> avgByCategory = transactions.stream()
            .collect(Collectors.groupingBy(
                Transaction::getCategory,
                Collectors.averagingDouble(Transaction::getAmount)
            ));
        
        // Транзакции за последний месяц
        LocalDate monthAgo = LocalDate.now().minusMonths(1);
        List<Transaction> recent = transactions.stream()
            .filter(t -> t.getDate().isAfter(monthAgo))
            .collect(Collectors.toList());
        
        // Самая популярная категория
        Optional<Map.Entry<String, Long>> mostPopular = transactions.stream()
            .collect(Collectors.groupingBy(
                Transaction::getCategory,
                Collectors.counting()
            ))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue());
        
        mostPopular.ifPresent(entry ->
            System.out.println("Самая популярная категория: " + entry.getKey())
        );
    }
}
```

---

## ✏️ Мини-задания

### Задание 1: Фильтрация и преобразование
Из списка чисел получите квадраты всех чётных чисел.

### Задание 2: Поиск
Найдите первое слово длиной больше 5 символов.

### Задание 3: Группировка
Сгруппируйте слова по их длине.

### Задание 4: Статистика
Вычислите среднее, минимум и максимум списка чисел.

### Задание 5: Объединение
Объедините несколько списков в один, удалив дубликаты.

---

## 🔨 Практические упражнения

### Упражнение 1: Анализ продаж
Создайте систему анализа продаж:
- Общая выручка
- Топ-10 товаров
- Продажи по категориям
- Средний чек
- Динамика по месяцам

### Упражнение 2: Обработка логов
Напишите анализатор логов:
- Фильтрация по уровню (ERROR, WARN, INFO)
- Подсчёт ошибок по типам
- Временные интервалы
- Поиск паттернов

### Упражнение 3: Работа с CSV
Прочитайте CSV файл и:
- Отфильтруйте данные
- Вычислите статистику
- Сгруппируйте по полям
- Экспортируйте результат

### Упражнение 4: Социальная сеть
Анализ данных социальной сети:
- Самые активные пользователи
- Популярные посты
- Анализ хештегов
- Рекомендации друзей

### Упражнение 5: Финансовый отчёт
Создайте систему финансовой отчётности:
- Доходы и расходы
- Категоризация
- Тренды
- Прогнозы

---

## 🎨 Мини-проект: Система аналитики данных

Создайте систему для анализа больших объёмов данных с использованием Stream API.

**Требования:**
- Загрузка данных из файла
- Фильтрация и преобразование
- Группировка и агрегация
- Статистические вычисления
- Экспорт результатов
- Параллельная обработка для больших файлов

---

## ➡️ Следующий модуль

После завершения переходите к [Module 7: IO/NIO](../module-07-io/README.md)
