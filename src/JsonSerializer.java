import java.lang.reflect.Field;

public final class JsonSerializer {
    public static String toJson (Object obj) {
        if (obj == null) return "null";

        Class<?> clazz = obj.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        Field[] fields = clazz.getDeclaredFields();
        boolean first = true;

        for (Field f : fields) {
            if (!f.isAnnotationPresent(JsonField.class)) continue;

            JsonField jf = f.getAnnotation(JsonField.class);
            String jsonName = jf.name();

            boolean accessible = f.isAccessible();
            try {
                f.setAccessible(true);
                Object value = f.get(obj);

                if (!first) sb.append(", ");
                sb.append("\"").append(escape(jsonName)).append("\": ");
                sb.append(formatValue(value));

                first = false;
            } catch (IllegalAccessException e) {
                if (!first) sb.append(", ");
                sb.append("\'").append(escape(jsonName)).append("\" : null");
                first = false;
            } finally {
                f.setAccessible(accessible);
            }
        }

        sb.append("}");
        return sb.toString();
    }

    private static String formatValue(Object v) {
        if (v ==  null) return "null";
        if (v instanceof String) {
            return "\"" + escape((String) v) + "\"";
        }
        if (v instanceof Character) {
            return "\"" + escape(String.valueOf(v)) + "\"";
        }
        if (v instanceof Number || v instanceof Boolean) {
            return String.valueOf(v);
        }
        if (v.getClass().isEnum()) {
            return "\"" + escape(((Enum<?>) v).name()) + "\"";
        }

        return "\"" + escape(String.valueOf(v)) + "\"";
    }

    private static String escape(String s) {
        StringBuilder out = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\"': out.append("\\\""); break;
                case '\\': out.append("\\\\"); break;
                case '\b': out.append("\\b"); break;
                case '\f': out.append("\\f"); break;
                case '\n': out.append("\\n"); break;
                case '\r': out.append("\\r"); break;
                case '\t': out.append("\\t"); break;
                default:
                    if (c < 0x20) {
                        out.append(String.format("\\u%04x", (int) c));
                    } else {
                        out.append(c);
                    }
            }
        }
        return out.toString();
    }
}
