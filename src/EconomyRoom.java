public class EconomyRoom extends Room {
    public EconomyRoom(int roomNumber, int maxPeople, boolean booked) {
        super(roomNumber, maxPeople, Prices.ECONOMY, booked);
    }
    public EconomyRoom(int roomNumber) {
        super(roomNumber, Prices.ECONOMY);
    }
}