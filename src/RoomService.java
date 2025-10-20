public interface RoomService<T extends Room> {
    void clean(T room);
    void book(T room) throws RoomAlreadyBookedException;
    void free(T room);
}
