public class Main {
    public static void main(String[] args) {
        System.out.println("1. Создание банковских счётов:");
        BankAccount account1 = new BankAccount("Зинаида Зинаидовна");
        BankAccount account2 = new BankAccount("Инга Ингова");

        System.out.println("Создан счёт 1: " + account1);
        System.out.println("Создан счёт 2: " + account2);
        System.out.println();

        System.out.println("2. Пополнение счёта:");

        System.out.println("Пополнение счёта Зинаиды на 1000");
        boolean deposit1 = account1.deposit(1000);
        System.out.println("Текущее состояние: " + account1);
        System.out.println();

        System.out.println("3. Снятие со счёта:");

        System.out.println("Снятие со счёта Зинаиды суммы 500");
        boolean withdraw1 = account1.withdraw(500);
        System.out.println("Текущее состояние: " + account1);

        System.out.println("Попытка снять 1000 (больше чем на счёте)");
        boolean withdraw2 = account1.withdraw(1000);
        System.out.println("Текущее состояние: " + account1);
        System.out.println();

        System.out.println("4. Переводы между счётами:");

        System.out.println("Перед переводом:");
        System.out.println("Счёт 1: " + account1);
        System.out.println("Счёт 2: " + account2);

        System.out.println("Перевод 300 со счёта Зинаиды на счёт Инги");
        boolean transfer1 = account1.transfer(account2, 300);

        System.out.println("После перевода:");
        System.out.println("Счёт 1: " + account1);
        System.out.println("Счёт 2: " + account2);
        System.out.println();
        System.out.println();

        System.out.println("5. Некорректные переводы:");

        System.out.println("Попытка перевода 1000 (больше чем на счёте)");
        boolean transfer2 = account1.transfer(account2, 1000);
        System.out.println("Результат: " + (transfer2 ? "успех" : "неудача"));

        System.out.println("Попытка перевода на null");
        boolean transfer3 = account1.transfer(null, 100);
        System.out.println("Результат: " + (transfer3 ? "успех" : "неудача"));

        System.out.println("Попытка перевода отрицательной суммы");
        boolean transfer4 = account1.transfer(account2, -50);
        System.out.println("Результат: " + (transfer4 ? "успех" : "неудача"));
        System.out.println();

        System.out.println("6. Блокировка счёта:");

        System.out.println("Блокируем счёт Инги");
        account2.setBlocked(true);
        System.out.println("Счёт: " + account2);

        System.out.println("Попытка пополнить заблокированный счёт");
        boolean depositBlocked = account2.deposit(1000);
        System.out.println("Результат: " + (depositBlocked ? "успех" : "неудача"));

        System.out.println("Попытка перевода на заблокированный счёт");
        boolean transferBlocked = account1.transfer(account2, 100);
        System.out.println("Результат: " + (transferBlocked ? "успех" : "неудача"));
        System.out.println();

        System.out.println("7. Сравнение счётов:");

        System.out.println("Счёт Зинаиды: " + account1.getNumber());
        System.out.println("Счёт Инги: " + account2.getNumber());

        System.out.println("Счёт Зинаиды и Инги: " + account1.equals(account2));
        System.out.println("Счёт Зинаиды и Зинаиды: " + account1.equals(account1));
        System.out.println("Счёт Зинаиды и null: " + account1.equals(null));
        System.out.println();

        System.out.println("account1.hashCode(): " + account1.hashCode());
        System.out.println("account2.hashCode(): " + account2.hashCode());
        System.out.println();

        System.out.println("8. Финальное состояние счётов:");
        System.out.println("Счёт 1: " + account1);
        System.out.println("Счёт 2: " + account2);
    }
}