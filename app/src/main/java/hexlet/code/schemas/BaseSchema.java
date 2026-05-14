package hexlet.code.schemas;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;


    public BaseSchema() {
        addCheck("required", Objects::nonNull);
    }


    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }


    public final boolean isValid(T value) {
        var it = checks.entrySet().iterator();
        var isDefiniteValue = it.next().getValue();

        if (isDefiniteValue.test(value)) {
            while (it.hasNext()) {
                var predicate = it.next().getValue();
                if (!predicate.test(value)) {
                    return false;
                }
            }
        } else {
            if (required) {
                return false;
            }
        }

        return true;
    }
}
