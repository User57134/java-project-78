package hexlet.code.schemas;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;
    protected Predicate<T> isDefiniteValue = Objects::nonNull;


    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }


    public final boolean isValid(T value) {
        if (isDefiniteValue.test(value)) {
            for (var predicate : checks.values()) {
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
