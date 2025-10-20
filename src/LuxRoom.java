public class LuxRoom extends ProRoom {
    public LuxRoom(int roomNumber, int maxPeople, boolean booked) {
        super(roomNumber, maxPeople, Prices.LUX, booked);
    }
    public LuxRoom(int roomNumber) {
        super(roomNumber, Prices.LUX);
    }

    public LuxRoom(int roomNumber, int maxPeople, Prices prices, boolean booked) {
        super(roomNumber, maxPeople, prices, booked);
    }

    public LuxRoom(int roomNumber, Prices prices) {
        super(roomNumber, prices);
    }
}