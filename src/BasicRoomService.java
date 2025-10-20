public class BasicRoomService<T extends Room> implements RoomService<T> {
    @Override
    public void clean(T room) {
        System.out.println("Комната " + room.getRoomNumber() + " убрана.");
    }

    @Override
    public void book(T room) {
        if (room.isBooked()) {
            throw new RoomAlreadyBookedException("Комната #" + room.getRoomNumber() + " уже забронирована");
        }
        room.setBooked(true);
        System.out.println("Комната #" + room.getRoomNumber() + " забронирована.");
    }

    @Override
    public void free(T room) {
        room.setBooked(false);
        System.out.println("Комната #" + room.getRoomNumber() + " освобождена.");
    }
}
