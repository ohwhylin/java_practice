public abstract class ProRoom extends Room {
    protected ProRoom(int roomNumber, int maxPeople, Prices price, boolean booked) {
        super(roomNumber, maxPeople, price, booked);
    }
    protected ProRoom(int roomNumber, Prices price) {
        super(roomNumber, price);
    }
}
