@DeprecatedEx(message = "Используйте NewService")
public class OldService {
    @DeprecatedEx(message = "Вместо doOld() вызовите doNew()")
    public void doOld() {
        System.out.println("Старая логика");
    }

    @DeprecatedEx(message = "Вместо doOldPrivate() вызовите doNewPrivate()")
    private void doOldPrivate() {
        System.out.println("Старая логика приватного метода");
    }

    public void runDemo() {
        System.out.println("Работа OldService");
        doOld();
        doOldPrivate();
    }
}
