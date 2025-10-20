import java.util.Random;

public abstract class Room {
    private final int roomNumber;
    private final int maxPeople;
    private final int pricePerNight;
    private boolean booked;

    private static final Random random = new Random();

    protected Room(int roomNumber, int maxPeople, Prices price, boolean booked) {
        this.roomNumber = roomNumber;
        this.maxPeople = maxPeople;
        this.pricePerNight = price.amount();
        this.booked = booked;
    }

    protected Room(int roomNumber, Prices price) {
        this(roomNumber, random.nextInt(4) + 1, price, false);
    }

    public int getRoomNumber() { return roomNumber; }
    public int getMaxPeople() { return maxPeople; }
    public int getPricePerNight() { return pricePerNight; }
    public boolean isBooked() { return booked; }
    void setBooked(boolean booked) { this.booked = booked; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {#" + roomNumber + ", количество человек = " + maxPeople + ", стоимость = " + pricePerNight + ", забронировано = " + booked + "}";
    }
}
