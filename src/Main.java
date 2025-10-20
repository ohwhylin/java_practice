public class Main {
    public static void main(String[] args) {
        EconomyRoom eco = new EconomyRoom(111);
        StandardRoom std = new StandardRoom(222);
        LuxRoom lux = new LuxRoom(333);
        UltraLuxRoom ultra = new UltraLuxRoom(444);

        RoomService<Room> basicService = new BasicRoomService<>();
        LuxRoomService<LuxRoom> luxService = new LuxRoomServiceImpl<>();

        System.out.println("Работа обычного сервиса");
        basicService.clean(eco);
        basicService.book(eco);

        // попытка повторного бронирования
        try {
            basicService.book(eco);
        } catch (RoomAlreadyBookedException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println(eco);
        basicService.free(eco);
        basicService.book(std);
        basicService.free(std);
        System.out.println(std);
        System.out.println();

        System.out.println("Работа люксового сервиса");
        luxService.book(lux);
        luxService.clean(lux);
        System.out.println(lux);
        System.out.println(ultra);
        try {
            luxService.book(lux);
        } catch (RoomAlreadyBookedException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        luxService.foodDelivery(lux, "стейк", "салат", "кофе");
        // в нелюксовый номер еду заказать нельзя
        // luxService.foodDelivery(eco, "стейк", "салат", "кофе");
        LuxRoomService<UltraLuxRoom> ultraService = new LuxRoomServiceImpl<>();

        // попытка заказать доставку до брони
        ultraService.foodDelivery(ultra, "икра", "шампанское");
        ultraService.book(ultra);
        ultraService.foodDelivery(ultra, "икра", "шампанское");
    }
}
