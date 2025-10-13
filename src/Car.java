import java.util.Objects;

public class Car implements Comparable<Car>{
    private final String VIN;
    private final String model;
    private final String manufacturer;
    private final int year;
    private final int mileage;
    private final double price;
    private final CarType type;

    public Car(String VIN, String model, String manufacturer, int year, int mileage, double price, CarType type){
        this.VIN = VIN;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        this.type = type;
    }

    public String getVIN() {return VIN;}
    public String getModel() {return model;}
    public String getManufacturer() {return manufacturer;}
    public int getYear() {return year;}
    public int getMileage() {return mileage;}
    public double getPrice() {return price;}
    public CarType getType() {return type;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return VIN.equals(car.VIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(VIN);
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.year, this.year);
    }

    @Override
    public String toString() {
        return String.format("Машина: VIN = %s, модель = %s, производитель = %s, год выпуска = %d, пробег = %d, цена = %.0f, тип = %s", VIN, model, manufacturer, year, mileage, price, type);
    }
}
