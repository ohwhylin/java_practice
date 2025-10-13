import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // 2.1
        int[] years = generateYears(50, 2000, 2025);
        System.out.println("Годы выпуска: " + Arrays.toString(years));

        System.out.print("Машины после 2015 года: ");
        Arrays.stream(years).filter(x -> x > 2015).forEach(x -> System.out.print(x + " "));
        System.out.println();

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        double averageAge = Arrays.stream(years).map(x -> currentYear - x).average().orElse(0.0);
        System.out.printf("Средний возраст авто: %.2f", averageAge);
        System.out.println();
        System.out.println();


        // 2.2
        List<String> models = new ArrayList<>(List.of("Toyota Camry", "BMW X5", "Kia Rio", "Lada Granta", "Hyundai Solaris", "Toyota Corolla", "Kia Rio", "Lada Granta", "Tesla Cybertruck", "Tesla Roadster"));
        System.out.println("Модели: " + models);

        List<String> check = models.stream().map(m -> contains(m, "Tesla") ? "ELECTRO_CAR" : m).collect(Collectors.toList());

        List<String> distinct = new ArrayList<>(new LinkedHashSet<>(check));

        distinct.sort(Comparator.reverseOrder());

        System.out.println("Модели после проверки, удаления дубликатов и сортировки: " + distinct);
        System.out.println();

        // 2.3
        Car car1 = new Car("001", "Camry", "Toyota", 2019, 20000, 150000, null);
        Car car2 = new Car("002", "X5", "BMW", 2021, 10000, 350000, null);
        Car car3 = new Car("001", "Camry", "Toyota", 2015, 30000, 100000, null);

        Set<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);
        boolean duplicate = cars.add(car3);
        System.out.println("Машины (дубликат по VIN не добавлен): " + cars);
        System.out.println("Попытка добавить дубликат с VIN 001: " + duplicate);

        List<Car> sorted = new ArrayList<>(List.of(car1, car2, car3));
        Collections.sort(sorted);
        System.out.println("Сортировка по году (от новых к старым): " + sorted);

        // 2.4
        List<Car> fleet = List.of(
                new Car("003", "Cybertruck", "Tesla", 2022, 15000, 550000, null),
                new Car("004", "Corolla", "Toyota", 2020, 70000, 190000, null),
                new Car("005", "X5", "BMW", 2019, 45000, 450000, null),
                new Car("006", "Solaris", "Hyundai", 2016, 90000, 250000, null),
                new Car("007", "Granta", "Lada", 2021, 48500, 230000, null),
                new Car("008", "Rio", "Kia", 2023, 35000, 350000, null),
                new Car("009", "Sportage", "Kia", 2018, 60000, 230000, null)
        );

        List<Car> lowMileage = fleet.stream().filter(car -> car.getMileage() < 50000).collect(Collectors.toList());
        System.out.println("Машины с пробегом меньше 50.000 км: " + lowMileage);

        List<Car> priceSort = fleet.stream().sorted(Comparator.comparingDouble(Car::getPrice).reversed()).collect(Collectors.toList());
        System.out.println("Машины, отсортированные по цене (по убыванию): " + priceSort);

        List<Car> top3 = priceSort.stream().limit(3).collect(Collectors.toList());
        System.out.println("Топ-3 самые дорогие машины: " + top3);

        double averageMileage = fleet.stream().mapToDouble(Car::getMileage).average().orElse(0.0);
        System.out.println("Средний пробег машин: " + averageMileage);

        Map<String, List<Car>> manufacturers = fleet.stream().collect(Collectors.groupingBy(Car::getManufacturer));
        System.out.println("Группировка по производителю: ");
        manufacturers.forEach((manufacturer, car) -> System.out.println(" " + manufacturer + ": " + car));

        // доп. задание
        CarDealership dealer = new CarDealership();

        dealer.addCar(new Car("010", "Camry", "Toyota", 2022, 20000, 280000, CarType.SEDAN));
        dealer.addCar(new Car("011", "Roadster", "Tesla", 2023, 10000, 520000, CarType.ELECTRIC));
        dealer.addCar(new Car("012", "X5", "BMW", 2021, 42000, 560000, CarType.SPORT));
        dealer.addCar(new Car("013", "Kalina", "Lada", 2020, 55000, 210000, CarType.TRUCK));
        dealer.addCar(new Car("014", "Qashqai", "Nissan", 2019, 70000, 165000, CarType.SUV));

        menuLoop(dealer);
    }

    static int[] generateYears(int count, int fromYear, int toYear){
        Random rnd = new Random();
        int[] arr = new int[count];
        for (int i = 0; i < count; i++){
            arr[i] = fromYear + rnd.nextInt(toYear - fromYear + 1);
        }
        return arr;
    }

    static boolean contains (String s, String sub){
        return s != null && sub != null && s.toLowerCase(Locale.ROOT).contains(sub.toLowerCase(Locale.ROOT));
    }

    static void menuLoop(CarDealership dealer) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nМеню:");
                System.out.println("1) Добавить машину");
                System.out.println("2) Найти все машины производителя");
                System.out.println("3) Средняя цена по типу");
                System.out.println("4) Список машин по году (от новых к старым)");
                System.out.println("5) Количество по типам");
                System.out.println("6) Самая старая и самая новая машины");
                System.out.println("0) Выход");
                System.out.print("Выбор: ");

                String choice = sc.nextLine().trim();
                switch (choice) {
                    case "1":
                        addCarInteractive(dealer, sc);
                        break;
                    case "2":
                        System.out.print("Производитель: ");
                        String manufacturer = sc.nextLine().trim();
                        List<Car> byMaker = dealer.findManufacturer(manufacturer);
                        System.out.println("Найдено: " + byMaker.size());
                        byMaker.forEach(System.out::println);
                        break;
                    case "3":
                        System.out.print("Тип (SEDAN, SUV, ELECTRIC, TRUCK, VAN, SPORT): ");
                        String typeStr = sc.nextLine().trim().toUpperCase(Locale.ROOT);
                        try {
                            CarType t = CarType.valueOf(typeStr);
                            OptionalDouble avg = dealer.averagePrice(t);
                            if (avg.isPresent()) {
                                System.out.printf(Locale.US, "Средняя цена для %s: %.2f\n", t, avg.getAsDouble());
                            } else {
                                System.out.println("Нет машин указанного типа.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Неизвестный тип.");
                        }
                        break;
                    case "4":
                        List<Car> sorted = dealer.sortedByYear();
                        sorted.forEach(System.out::println);
                        break;
                    case "5":
                        Map<CarType, Long> counts = dealer.countType();
                        counts.forEach((k, v) -> System.out.println(k + ": " + v));
                        break;
                    case "6":
                        dealer.oldestAndNewest().ifPresentOrElse(pair -> {
                            System.out.println("Самая старая: " + pair.oldest);
                            System.out.println("Самая новая: " + pair.newest);
                        }, () -> System.out.println("В автосалоне нет машин."));
                        break;
                    case "0":
                        System.out.println("Выход.");
                        return;
                    default:
                        System.out.println("Неверный выбор.");
                }
            }
        }
    }

    static void addCarInteractive(CarDealership dealer, Scanner sc) {
        try {
            System.out.print("VIN: ");
            String vin = sc.nextLine().trim();
            System.out.print("Модель: ");
            String model = sc.nextLine().trim();
            System.out.print("Производитель: ");
            String manufacturer = sc.nextLine().trim();
            System.out.print("Год выпуска: ");
            int year = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Пробег: ");
            int mileage = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Цена: ");
            double price = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Тип (SEDAN, SUV, ELECTRIC, TRUCK, VAN, SPORT): ");
            CarType type = CarType.valueOf(sc.nextLine().trim().toUpperCase(Locale.ROOT));

            boolean added = dealer.addCar(new Car(vin, model, manufacturer, year, mileage, price, type));
            System.out.println(added ? "Машина добавлена." : "Дубликат VIN — не добавлена.");
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
}