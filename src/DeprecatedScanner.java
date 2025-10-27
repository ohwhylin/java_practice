import java.lang.reflect.Method;

public class DeprecatedScanner {
    public static void scan(Class<?> clazz) {
        if (clazz.isAnnotationPresent(DeprecatedEx.class)) {
            DeprecatedEx ann = clazz.getAnnotation(DeprecatedEx.class);
            System.out.printf("! класс '%s' устарел - альтернатива: '%s' %n", clazz.getSimpleName(), ann.message());
        }

        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(DeprecatedEx.class)) {
                DeprecatedEx ann = m.getAnnotation(DeprecatedEx.class);
                System.out.printf("! метод '%s' устарел - альтернатива: '%s' %n", m.getName(), ann.message());
            }
        }
    }
}