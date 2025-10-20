public interface LuxRoomService<T extends LuxRoom> extends RoomService<T> {
    void foodDelivery(T room, String... items);
}
