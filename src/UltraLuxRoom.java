public class UltraLuxRoom extends LuxRoom {
    public UltraLuxRoom(int roomNumber, int maxPeople, boolean booked) {
        super(roomNumber, maxPeople, Prices.ULTRALUX, booked);
    }
    public UltraLuxRoom(int roomNumber) {
        super(roomNumber, Prices.ULTRALUX);
    }
}
