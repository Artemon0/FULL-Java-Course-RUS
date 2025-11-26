
import java.util.Scanner;

/**
 * БОЛЬШОЙ ПРОЕКТ: Система управления студентами
 *
 * Полная реализация с: - Классом Student - Классом StudentDatabase - CRUD
 * операциями - Поиском и фильтрацией - Статистикой
 */
class Student {

    private static int nextId = 1;

    private int id;
    private String name;
    private int age;
    private int course;
    private double gpa;

    public Student(String name, int age, int course, double gpa) {
        this.id = nextId++;
        this.name = name;
        this.age = age;
        this.course = course;
        this.gpa = gpa;
    }

    // Геттеры
    public int getId() {
        return id;
    }
    public String 
getName() { return name; }
    public int getAge() { return age; }
    public int getCourse() { return course; }
    public double getGpa() { return gpa; }
    
    // Сеттеры с валидацией
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }
    
    public void setAge(int age) {
        if (age >= 16 && age <= 100) {
            this.age = age;
        }
    }
    
    public void setCourse(int course) {
        if (course >= 1 && course <= 6) {
            this.course = course;
        }
    }
    
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 5.0) {
            this.gpa = gpa;
        }
    }
    
    public boolean isExcellent() {
        return gpa >= 4.5;
    }
    
    public void displayInfo() {
        System.out.println("┌────────────────────────────────────────┐");
        System.out.printf("│ ID: %d%n", id);
        System.out.println("│ Имя: " + name);
        System.out.println("│ Возраст: " + age);
        System.out.println("│ Курс: " + course);
        System.out.printf("│ GPA: %.2f%n", gpa);
        System.out.println("│ Статус: " + (isExcellent() ? "⭐ Отличник" : "Студент"));
        System.out.println("└────────────────────────────────────────┘");
    }
}

class StudentDatabase {
    private Student[] students;
    private int count;
    
    public StudentDatabase(int capacity) {
        students = new Student[capacity];
        count = 0;
    }
    
    public boolean addStudent(Student student) {
        if (count < students.length) {
            students[count] = student;
            count++;
            return true;
        }
        return false;
    }
    
    public boolean removeStudent(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) {
                // Сдвигаем элементы
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }
    
    public Student findStudentById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }
        return null;
    }
    
    public void findStudentsByName(String name) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].getName().toLowerCase().contains(name.toLowerCase())) {
                students[i].displayInfo();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Студенты не найдены");
        }
    }
    
    public void displayAllStudents() {
        if (count == 0) {
            System.out.println("База данных пуста");
            return;
        }
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║         ВСЕ СТУДЕНТЫ                   ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        for (int i = 0; i < count; i++) {
            students[i].displayInfo();
            System.out.println();
        }
    }
    
    public void displayExcellentStudents() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║         ОТЛИЧНИКИ (GPA >= 4.5)         ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].isExcellent()) {
                students[i].displayInfo();
                System.out.println();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Отличников нет");
        }
    }
    
    public double getAverageGpa() {
        if (count == 0) return 0.0;
        
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += students[i].getGpa();
        }
        return sum / count;
    }
    
    public void displayStatistics() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║            СТАТИСТИКА                  ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        System.out.println("Всего студентов: " + count);
        System.out.printf("Средний GPA: %.2f%n", getAverageGpa());
        
        int excellentCount = 0;
        for (int i = 0; i < count; i++) {
            if (students[i].isExcellent()) {
                excellentCount++;
            }
        }
        System.out.println("Отличников: " + excellentCount);
        
        // Распределение по курсам
        int[] courseDistribution = new int[6];
        for (int i = 0; i < count; i++) {
            int course = students[i].getCourse();
            if (course >= 1 && course <= 6) {
                courseDistribution[course - 1]++;
            }
        }
        
        System.out.println("\nРаспределение по курсам:");
        for (int i = 0; i < 6; i++) {
            if (courseDistribution[i] > 0) {
                System.out.println("  Курс " + (i + 1) + ": " + courseDistribution[i] + " студентов");
            }
        }
    }
    
    public int getCount() {
        return count;
    }
}

public class StudentManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentDatabase database = new StudentDatabase(100);
    
    public static void main(String[] args) {
        // Добавляем тестовых студентов
        addTestData();
        
        printWelcome();
        
        boolean running = true;
        
        while (running) {
            printMenu();
            int choice = getIntInput("Ваш выбор: ");
            System.out.println();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    findStudentById();
                    break;
                case 4:
                    findStudentsByName();
                    break;
                case 5:
                    database.displayAllStudents();
                    break;
                case 6:
                    database.displayExcellentStudents();
                    break;
                case 7:
                    database.displayStatistics();
                    break;
                case 0:
                    running = false;
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("❌ Неверный выбор!");
            }
            
            if (running) {
                System.out.println("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void printWelcome() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║        СИСТЕМА УПРАВЛЕНИЯ СТУДЕНТАМИ                       ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    private static void printMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║              МЕНЮ                      ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ 1. Добавить студента                   ║");
        System.out.println("║ 2. Удалить студента                    ║");
        System.out.println("║ 3. Найти студента по ID                ║");
        System.out.println("║ 4. Найти студентов по имени            ║");
        System.out.println("║ 5. Показать всех студентов             ║");
        System.out.println("║ 6. Показать отличников                 ║");
        System.out.println("║ 7. Статистика                          ║");
        System.out.println("║ 0. Выход                               ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    private static void addStudent() {
        System.out.println("=== ДОБАВЛЕНИЕ СТУДЕНТА ===\n");
        
        scanner.nextLine(); // Очистка буфера
        
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        
        int age = getIntInput("Введите возраст (16-100): ");
        while (age < 16 || age > 100) {
            System.out.println("❌ Возраст должен быть от 16 до 100");
            age = getIntInput("Введите возраст: ");
        }
        
        int course = getIntInput("Введите курс (1-6): ");
        while (course < 1 || course > 6) {
            System.out.println("❌ Курс должен быть от 1 до 6");
            course = getIntInput("Введите курс: ");
        }
        
        double gpa = getDoubleInput("Введите GPA (0.0-5.0): ");
        while (gpa < 0.0 || gpa > 5.0) {
            System.out.println("❌ GPA должен быть от 0.0 до 5.0");
            gpa = getDoubleInput("Введите GPA: ");
        }
        
        Student student = new Student(name, age, course, gpa);
        
        if (database.addStudent(student)) {
            System.out.println("\n✓ Студент добавлен! ID: " + student.getId());
        } else {
            System.out.println("\n❌ База данных заполнена!");
        }
    }
    
    private static void removeStudent() {
        System.out.println("=== УДАЛЕНИЕ СТУДЕНТА ===\n");
        
        int id = getIntInput("Введите ID студента: ");
        
        if (database.removeStudent(id)) {
            System.out.println("\n✓ Студент удалён");
        } else {
            System.out.println("\n❌ Студент не найден");
        }
    }
    
    private static void findStudentById() {
        System.out.println("=== ПОИСК ПО ID ===\n");
        
        int id = getIntInput("Введите ID: ");
        
        Student student = database.findStudentById(id);
        if (student != null) {
            System.out.println();
            student.displayInfo();
        } else {
            System.out.println("\n❌ Студент не найден");
        }
    }
    
    private static void findStudentsByName() {
        System.out.println("=== ПОИСК ПО ИМЕНИ ===\n");
        
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите имя (или часть имени): ");
        String name = scanner.nextLine();
        
        System.out.println();
        database.findStudentsByName(name);
    }
    
    private static void addTestData() {
        database.addStudent(new Student("Иван Петров", 20, 2, 4.7));
        database.addStudent(new Student("Мария Сидорова", 19, 1, 4.9));
        database.addStudent(new Student("Алексей Иванов", 21, 3, 4.2));
        database.addStudent(new Student("Елена Смирнова", 22, 4, 4.8));
        database.addStudent(new Student("Дмитрий Козлов", 20, 2, 3.9));
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Ошибка! Введите число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("❌ Ошибка! Введите число: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
