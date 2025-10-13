import java.util.*;
import java.util.stream.Collectors;

public class CarDealership {
    private final Map<String, Car> byVIN = new HashMap<>();

    public boolean addCar(Car car) {
        Objects.requireNonNull(car);
        if (byVIN.containsKey(car.getVIN())) return false;
        byVIN.put(car.getVIN(), car);
        return true;
    }

    public List<Car> findManufacturer(String manufacturer) {
        String search = manufacturer.toLowerCase(Locale.ROOT);
        return byVIN.values().stream().filter(c -> c.getManufacturer().toLowerCase(Locale.ROOT).contains(search)).collect(Collectors.toList());
    }

    public OptionalDouble averagePrice(CarType type) {
        return byVIN.values().stream().filter(c -> c.getType() == type).mapToDouble(Car::getPrice).average();
    }

    public List<Car> sortedByYear() {
        return byVIN.values().stream().sorted().collect(Collectors.toList());
    }

    public Map<CarType, Long> countType() {
        return byVIN.values().stream().filter(c -> c.getType() != null).collect(Collectors.groupingBy(Car::getType, Collectors.counting()));
    }

    public Optional<OldestNewest> oldestAndNewest() {
        return byVIN.values().stream().findAny().map(x -> {
            Car oldest = byVIN.values().stream().min(Comparator.comparingInt(Car::getYear)).orElse(null);
            Car newest = byVIN.values().stream().max(Comparator.comparingInt(Car::getYear)).orElse(null);
            return new OldestNewest(oldest, newest);
        });
    }

    public static class OldestNewest {
        public final Car oldest;
        public final Car newest;
        public OldestNewest(Car oldest, Car newest) {
            this.oldest = oldest; this.newest = newest;
        }
    }
}