import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        // задание 1.1
        Printable printer = () -> System.out.println("Hello world!");

        printer.print();
        System.out.println();

        // задание 1.2
        // проверка на null
        Predicate<String> notNull = s -> s != null;

        // проверка на пустую строку
        Predicate<String> notEmpty = s -> !s.isEmpty();

        // строка не null и не пуста
        Predicate<String> notNullAndNotEmpty = notNull.and(notEmpty);

        String[] tests = {null, "", "  ", "test"};

        Arrays.stream(tests).forEach(str -> System.out.printf("'%s' -> %s%n", str, notNullAndNotEmpty.test(str)));
        System.out.println();

        // задание 1.3
        // проверка первой буквы и последней буквы (без учета регистра)
        Predicate<String> beginJ = s -> s.startsWith("J");
        Predicate<String> beginN = s -> s.startsWith("N");
        Predicate<String> endA = s -> s.endsWith("A");

        Predicate<String> beginJorN = beginJ.or(beginN);
        Predicate<String> check = beginJorN.and(endA);

        String[] test2 = {"Jtest", "Ntest", "Testa", "Jtesta", "Ntesta", "JtestA", "NtestA"};

        Arrays.stream(test2).forEach(str -> System.out.printf("%s -> %s%n", str, check.test(str)));
        System.out.println();

        // задание 1.4
        HeavyBox box = new HeavyBox(10);

        Consumer<HeavyBox> load = b -> System.out.println("Отгрузили ящик с весом " + b.getWeight());
        Consumer<HeavyBox> send = b -> System.out.println("Отправили ящик с весом " + b.getWeight());

        Consumer<HeavyBox> process = load.andThen(send);

        process.accept(box);
        System.out.println();

        // задание 1.5
        Function<Integer, String> checkNumber = n -> {
            if (n > 0) return "Положительное число";
            if (n < 0) return "Отрицательное число";
            else return "Ноль";
        };

        int[] numbers = {1, -1, 0, 4, -5, 7};

        Arrays.stream(numbers).forEach(number -> System.out.println(number + ": " + checkNumber.apply(number)));
        System.out.println();

        // задание 1.6
        Random random = new Random();
        Supplier<Integer> randomNumber = () -> random.nextInt(11);

        for (int i = 0; i < 5; i++) {
            System.out.println("Случайное число: " + randomNumber.get());
        }
        System.out.println();

        // задание 2.1
        DeprecatedScanner.scan(OldService.class);
        DeprecatedScanner.scan(NewService.class);
        System.out.println();

        OldService oldService = new OldService();
        NewService newService = new NewService();
        oldService.runDemo();
        newService.runDemo();
        System.out.println();

        // задание 2.2

        Person p1 = new Person(1, "Person", true, Person.Role.ADMIN);
        Person p2 = new Person(2, null, false, Person.Role.USER);

        System.out.println("p1 to json: " + JsonSerializer.toJson(p1));
        System.out.println("p2 to json: " + JsonSerializer.toJson(p2));
    }
}