
/**
 * Example 3: Инкапсуляция - сокрытие данных
 */

class BankAccount {

    // Приватные поля
    private String accountNumber;
    private String ownerName;
    private double balance;
    private String pin;

    // Конструктор
    public BankAccount(String accountNumber, String ownerName, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.pin = pin;
        this.balance = initialBalance;
    }

    // Геттеры (только для чтения)
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    // Метод для пополнения счёта
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("✓ Пополнение на %.2f руб. Новый баланс: %.2f руб%n", amount, balance);
        } else {
            System.out.println("❌ Сумма должна быть положительной!");
        }
    }

    // Метод для снятия денег
    public boolean withdraw(String enteredPin, double amount) {
        if (!enteredPin.equals(pin)) {
            System.out.println("❌ Неверный PIN-код!");
            return false;
        }

        if (amount <= 0) {
            System.out.println("❌ Сумма должна быть положительной!");
            return false;
        }

        if (amount > balance) {
            System.out.println("❌ Недостаточно средств!");
            return false;
        }

        balance -= amount;
        System.out.printf("✓ Снято %.2f руб. Остаток: %.2f руб%n", amount, balance);
        return true;
    }

    // Метод для перевода денег
    public boolean transfer(String enteredPin, BankAccount recipient, double amount) {
        if (!enteredPin.equals(pin)) {
            System.out.println("❌ Неверный PIN-код!");
            return false;
        }

        if (amount <= 0) {
            System.out.println("❌ Сумма должна быть положительной!");
            return false;
        }

        if (amount > balance) {
            System.out.println("❌ Недостаточно средств!");
            return false;
        }

        balance -= amount;
        recipient.balance += amount;
        System.out.printf("✓ Переведено %.2f руб на счёт %s%n", amount, recipient.ownerName);
        return true;
    }

    // Метод для отображения информации
    public void displayInfo() {
        System.out.println("┌────────────────────────────────────────┐");
        System.out.println("│ Счёт: " + accountNumber);
        System.out.println("│ Владелец: " + ownerName);
        System.out.printf("│ Баланс: %.2f руб%n", balance);
        System.out.println("└────────────────────────────────────────┘");
    }
}

public class Example03_Encapsulation {

    public static void main(String[] args) {
        System.out.println("=== СОЗДАНИЕ БАНКОВСКИХ СЧЕТОВ ===\n");

        BankAccount account1 = new BankAccount("1234-5678", "Иван Иванов", "1234", 10000.0);
        BankAccount account2 = new BankAccount("8765-4321", "Мария Петрова", "5678", 5000.0);

        account1.displayInfo();
        System.out.println();
        account2.displayInfo();

        System.out.println("\n=== ОПЕРАЦИИ СО СЧЕТАМИ ===\n");

        // Пополнение
        System.out.println("Пополнение счёта Ивана:");
        account1.deposit(2000.0);

        System.out.println();

        // Попытка снять с неверным PIN
        System.out.println("Попытка снять деньги с неверным PIN:");
        account1.withdraw("0000", 1000.0);

        System.out.println();

        // Снятие с правильным PIN
        System.out.println("Снятие денег с правильным PIN:");
        account1.withdraw("1234", 3000.0);

        System.out.println();

        // Попытка снять больше, чем есть
        System.out.println("Попытка снять больше, чем есть на счёте:");
        account1.withdraw("1234", 20000.0);

        System.out.println();

        // Перевод денег
        System.out.println("Перевод денег от Ивана к Марии:");
        account1.transfer("1234", account2, 2000.0);

        System.out.println("\n=== ИТОГОВЫЕ БАЛАНСЫ ===\n");

        account1.displayInfo();
        System.out.println();
        account2.displayInfo();
    }
}
