package hexlet.code.schemas;


import java.util.Map;


public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema() {
        //
    }


    public MapSchema required() {
        required = true;

        return this;
    }


    public MapSchema sizeof(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("The size parameter can not be lower than 0: " + size);
        }

        addCheck("sizeof", v -> (v.size() == size));

        return this;
    }


    public <K, T> MapSchema shape(Map<K, BaseSchema<T>> schemas) {
        addCheck(
                "shape",
                map -> {
                    return schemas.entrySet().stream().allMatch(e -> {
                        var key = e.getKey();
                        var v = map.get(key);
                        var schema = e.getValue();
                        @SuppressWarnings("unchecked")
                        T value = (T) v;
                        return schema.isValid(value);
                    });
                }
        );
        return this;
    }
}
