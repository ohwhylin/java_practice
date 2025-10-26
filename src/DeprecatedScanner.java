import java.lang.reflect.Method;

public class DeprecatedScanner {
    public static void scan(Class<?> cls) {
        if (cls.isAnnotationPresent(DeprecatedEx.class)) {
            DeprecatedEx ann = cls.getAnnotation(DeprecatedEx.class);
            System.out.printf("! класс '%s' устарел - альтернатива: '%s' %n", cls.getSimpleName(), ann.message());
        }

        for (Method m : cls.getDeclaredMethods()) {
            if (m.isAnnotationPresent(DeprecatedEx.class)) {
                DeprecatedEx ann = m.getAnnotation(DeprecatedEx.class);
                System.out.printf("! метод '%s' устарел - альтернатива: '%s' %n", m.getName(), ann.message());
            }
        }
    }
}
