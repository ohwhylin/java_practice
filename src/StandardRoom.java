public class StandardRoom extends ProRoom {
    public StandardRoom(int roomNumber, int maxPeople, boolean booked) {
        super(roomNumber, maxPeople, Prices.STANDARD, booked);
    }
    public StandardRoom(int roomNumber) {
        super(roomNumber, Prices.STANDARD);
    }
}