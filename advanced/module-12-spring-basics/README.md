# Module 12: Spring Framework - Основы

```
╔════════════════════════════════════════════════════════════╗
║  MODULE 12: SPRING FRAMEWORK - ОСНОВЫ                      ║
║  Уровень: Advanced                                         ║
║  Время: 2-3 недели                                         ║
╚════════════════════════════════════════════════════════════╝
```

## 📖 Теория

### 12.1 Что такое Spring?

**Spring Framework** - самый популярный фреймворк для Java enterprise разработки.

```
┌─────────────────────────────────────────────┐
│           Spring Ecosystem                  │
├─────────────────────────────────────────────┤
│  Spring Core (IoC, DI)                      │
│  Spring Boot (автоконфигурация)             │
│  Spring MVC (веб-приложения)                │
│  Spring Data (работа с БД)                  │
│  Spring Security (безопасность)             │
│  Spring Cloud (микросервисы)                │
│  Spring Batch (пакетная обработка)          │
└─────────────────────────────────────────────┘
```

### 12.2 Inversion of Control (IoC)

**IoC** - принцип, при котором фреймворк управляет созданием и жизненным циклом объектов.

```
Традиционный подход:
┌──────────────┐
│   Ваш код    │ создаёт и управляет объектами
└──────────────┘

IoC подход:
┌──────────────┐
│   Spring     │ создаёт и управляет объектами
└──────────────┘
       │
       └──► внедряет зависимости в ваш код
```

### 12.3 Dependency Injection (DI)

**DI** - паттерн, при котором зависимости передаются извне.

```java
// Без DI (плохо)
public class UserService {
    private UserRepository repository = new UserRepository();
}

// С DI (хорошо)
public class UserService {
    private final UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
```

**Типы внедрения:**
```
1. Constructor Injection (рекомендуется)
2. Setter Injection
3. Field Injection (не рекомендуется)
```

### 12.4 Spring Container

**Spring Container** (ApplicationContext) - контейнер, управляющий бинами.

```
┌─────────────────────────────────────────┐
│      Spring Container                   │
├─────────────────────────────────────────┤
│  ┌─────────┐  ┌─────────┐  ┌─────────┐ │
│  │  Bean1  │  │  Bean2  │  │  Bean3  │ │
│  └─────────┘  └─────────┘  └─────────┘ │
│       │            │            │       │
│       └────────────┴────────────┘       │
│         Управление зависимостями        │
└─────────────────────────────────────────┘
```

### 12.5 Bean Scopes

```
┌──────────────┬─────────────────────────────────┐
│   Scope      │   Описание                      │
├──────────────┼─────────────────────────────────┤
│ singleton    │ Один экземпляр на контейнер     │
│ prototype    │ Новый экземпляр при каждом      │
│              │ запросе                         │
│ request      │ Один на HTTP запрос (Web)       │
│ session      │ Один на HTTP сессию (Web)       │
│ application  │ Один на ServletContext (Web)    │
└──────────────┴─────────────────────────────────┘
```

### 12.6 Spring Boot

**Spring Boot** - упрощает создание Spring приложений.

**Преимущества:**
- 🚀 Быстрый старт
- ⚙️ Автоконфигурация
- 📦 Встроенный сервер (Tomcat)
- 🔧 Production-ready функции
- 📝 Минимум конфигурации

---

## 💻 Примеры кода

### Пример 1: Первое Spring Boot приложение

**pom.xml:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
    </parent>
    
    <groupId>com.example</groupId>
    <artifactId>spring-demo</artifactId>
    <version>1.0.0</version>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
    </dependencies>
</project>
```

**Application.java:**
```java
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Пример 2: Dependency Injection - Constructor

```java
// Интерфейс
public interface MessageService {
    String getMessage();
}

// Реализация
@Service
public class EmailService implements MessageService {
    @Override
    public String getMessage() {
        return "Email message";
    }
}

// Использование (Constructor Injection)
@Component
public class MessageProcessor {
    private final MessageService messageService;
    
    @Autowired  // Можно опустить для единственного конструктора
    public MessageProcessor(MessageService messageService) {
        this.messageService = messageService;
    }
    
    public void processMessage() {
        String message = messageService.getMessage();
        System.out.println("Processing: " + message);
    }
}
```

### Пример 3: Различные способы DI

```java
@Component
public class UserService {
    // 1. Constructor Injection (РЕКОМЕНДУЕТСЯ)
    private final UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    
    // 2. Setter Injection
    private EmailService emailService;
    
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
    
    // 3. Field Injection (НЕ РЕКОМЕНДУЕТСЯ)
    @Autowired
    private LogService logService;
}
```

### Пример 4: @Component и стереотипы

```java
// @Component - общий компонент
@Component
public class MyComponent {
    // ...
}

// @Service - бизнес-логика
@Service
public class UserService {
    public User findUser(Long id) {
        // бизнес-логика
    }
}

// @Repository - доступ к данным
@Repository
public class UserRepository {
    public User save(User user) {
        // работа с БД
    }
}

// @Controller - веб-контроллер
@Controller
public class UserController {
    @GetMapping("/users")
    public String getUsers() {
        return "users";
    }
}
```

### Пример 5: Configuration класс

```java
@Configuration
public class AppConfig {
    
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/mydb");
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        return dataSource;
    }
    
    @Bean
    public UserService userService(UserRepository repository) {
        return new UserService(repository);
    }
    
    @Bean
    @Scope("prototype")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }
}
```

### Пример 6: application.properties

```properties
# Настройки приложения
spring.application.name=MyApp
server.port=8080

# База данных
spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
spring.datasource.username=user
spring.datasource.password=password

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Логирование
logging.level.root=INFO
logging.level.com.example=DEBUG

# Кастомные свойства
app.name=My Application
app.version=1.0.0
```

### Пример 7: @Value и @ConfigurationProperties

```java
// @Value - для отдельных свойств
@Component
public class AppInfo {
    @Value("${app.name}")
    private String appName;
    
    @Value("${app.version}")
    private String version;
    
    @Value("${server.port:8080}")  // значение по умолчанию
    private int port;
    
    public void printInfo() {
        System.out.println(appName + " v" + version);
        System.out.println("Port: " + port);
    }
}

// @ConfigurationProperties - для группы свойств
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String name;
    private String version;
    private Database database;
    
    // геттеры и сеттеры
    
    public static class Database {
        private String url;
        private String username;
        // геттеры и сеттеры
    }
}
```

### Пример 8: Bean Lifecycle

```java
@Component
public class MyBean {
    
    // 1. Конструктор
    public MyBean() {
        System.out.println("1. Constructor called");
    }
    
    // 2. Внедрение зависимостей
    @Autowired
    public void setDependency(SomeDependency dependency) {
        System.out.println("2. Dependencies injected");
    }
    
    // 3. @PostConstruct
    @PostConstruct
    public void init() {
        System.out.println("3. @PostConstruct - initialization");
    }
    
    // 4. InitializingBean
    public void afterPropertiesSet() {
        System.out.println("4. afterPropertiesSet called");
    }
    
    // 5. Custom init method
    public void customInit() {
        System.out.println("5. Custom init method");
    }
    
    // Перед уничтожением
    @PreDestroy
    public void cleanup() {
        System.out.println("@PreDestroy - cleanup");
    }
}
```

### Пример 9: Profiles

```java
@Configuration
public class DatabaseConfig {
    
    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }
    
    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://prod-server:5432/db");
        return dataSource;
    }
}

// Активация профиля
// application.properties:
// spring.profiles.active=dev

// Или через командную строку:
// java -jar app.jar --spring.profiles.active=prod
```

### Пример 10: Полное приложение - Task Manager

```java
// Entity
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private boolean completed;
    
    // конструкторы, геттеры, сеттеры
}

// Repository
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);
}

// Service
@Service
public class TaskService {
    private final TaskRepository repository;
    
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    
    public List<Task> getAllTasks() {
        return repository.findAll();
    }
    
    public Task createTask(Task task) {
        return repository.save(task);
    }
    
    public Task updateTask(Long id, Task taskDetails) {
        Task task = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        
        return repository.save(task);
    }
    
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}

// Controller
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }
    
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }
    
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}

// Application
@SpringBootApplication
public class TaskManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }
}
```

---

## ✏️ Мини-задания

### Задание 1: Hello Spring
Создайте простое Spring Boot приложение с одним компонентом.

### Задание 2: DI практика
Создайте сервис с несколькими зависимостями, используя Constructor Injection.

### Задание 3: Configuration
Создайте @Configuration класс с несколькими бинами.

### Задание 4: Properties
Используйте @Value для чтения свойств из application.properties.

### Задание 5: Profiles
Создайте разные конфигурации для dev и prod профилей.

---

## 🔨 Практические упражнения

### Упражнение 1: Калькулятор сервис
Создайте калькулятор с разными стратегиями вычислений:
- Интерфейс CalculationStrategy
- Реализации: Addition, Subtraction, Multiplication, Division
- Сервис, использующий стратегии через DI

### Упражнение 2: Notification система
Создайте систему уведомлений:
- Интерфейс NotificationService
- Реализации: EmailNotification, SmsNotification, PushNotification
- Конфигурация для выбора типа уведомлений

### Упражнение 3: User Management
Создайте систему управления пользователями:
- Entity: User
- Repository: UserRepository
- Service: UserService
- Валидация данных
- Обработка исключений

### Упражнение 4: Configuration Management
Создайте систему конфигурации:
- Разные профили (dev, test, prod)
- Внешние конфигурационные файлы
- @ConfigurationProperties для группировки

### Упражнение 5: Event System
Создайте систему событий:
- ApplicationEvent для кастомных событий
- @EventListener для обработки
- Асинхронная обработка событий

---

## 🎨 Мини-проект: Blog API

Создайте REST API для блога с использованием Spring Boot.

**Требования:**

1. **Entities:**
   - User (автор)
   - Post (статья)
   - Comment (комментарий)

2. **Функционал:**
   - CRUD операции для постов
   - Комментарии к постам
   - Поиск постов
   - Пагинация

3. **Технологии:**
   - Spring Boot
   - Spring Data JPA
   - H2 Database (для начала)
   - REST API

4. **Структура:**
```
blog-api/
├── entity/
│   ├── User.java
│   ├── Post.java
│   └── Comment.java
├── repository/
│   ├── UserRepository.java
│   ├── PostRepository.java
│   └── CommentRepository.java
├── service/
│   ├── UserService.java
│   ├── PostService.java
│   └── CommentService.java
├── controller/
│   ├── UserController.java
│   ├── PostController.java
│   └── CommentController.java
└── BlogApplication.java
```

**API Endpoints:**
```
GET    /api/posts          - все посты
GET    /api/posts/{id}     - пост по ID
POST   /api/posts          - создать пост
PUT    /api/posts/{id}     - обновить пост
DELETE /api/posts/{id}     - удалить пост

GET    /api/posts/{id}/comments     - комментарии поста
POST   /api/posts/{id}/comments     - добавить комментарий
```

---

## ➡️ Следующий модуль

После завершения переходите к [Module 13: Spring Advanced](../module-13-spring-advanced/README.md)
