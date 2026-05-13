package hexlet.code.schemas;


import java.util.Map;


public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema() {
        //
    }


    private <K, V> boolean checkForRulesCompliance(Map<K, V> inputData, Map<?, ? extends BaseSchema<?>> rules) {
        if ((rules != null)) {
            if (rules.size() != inputData.size()) {
                return false;
            }

            var rulesKeyType = rules.entrySet().iterator().next().getKey().getClass();
            var dataKeyType = inputData.entrySet().iterator().next().getKey().getClass();

            if (rulesKeyType != dataKeyType) {
                return false;
            }

            for (var el : inputData.entrySet()) {
                if (!rules.containsKey(el.getKey())) {
                    return false;
                }

                var personalSchema = rules.get(el.getKey());
                var value = el.getValue();

                if (value instanceof String) {
                    @SuppressWarnings("unchecked")
                    var stringSchema = (BaseSchema<String>) personalSchema;
                    if (!stringSchema.isValid((String) value)) {
                        return false;
                    }
                } else if (value instanceof Number) {
                    @SuppressWarnings("unchecked")
                    var numberSchema = (BaseSchema<Number>) personalSchema;
                    if (!numberSchema.isValid((Number) value)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }


    public MapSchema required() {
        required = true;

        return this;
    }


    public MapSchema sizeof(int size) {
        addCheck("sizeof", (v) -> ((size <= 0) || (v.size() == size)));

        return this;
    }


    public <K, T> MapSchema shape(Map<K, BaseSchema<T>>  validationRules) {
        addCheck("shape", (v) -> checkForRulesCompliance(v, validationRules));

        return this;
    }
}
