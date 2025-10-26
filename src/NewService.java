public class NewService {
    public void doNew() {
        System.out.println("Новая логика");
    }

    private void doNewPrivate() {
        System.out.println("Новая логика приватного метода");
    }

    public void runDemo() {
        System.out.println("Работа NewService");
        doNew();
        doNewPrivate();
    }
}
