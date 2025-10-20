import java.util.Arrays;

public class LuxRoomServiceImpl<T extends LuxRoom> extends BasicRoomService<T> implements LuxRoomService<T> {

    @Override
    public void foodDelivery(T room, String... items) {
        if (!room.isBooked()) {
            System.out.println("Нельзя оформить доставку — номер #" + room.getRoomNumber() + " не забронирован.");
            return;
        }

        String list = (items == null || items.length == 0) ? "базовый набор" : String.join(", ", Arrays.asList(items));

        System.out.println("Доставка в номер #" + room.getRoomNumber() + ": " + list);
    }

}
