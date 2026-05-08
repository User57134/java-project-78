package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private boolean isRequired = false;
    private int size = 0;
    private Map<?, ? extends BaseSchema<?>> rules = null;

    public MapSchema() {
        //
    }

    private <K, V> boolean checkForRulesCompliance(Map<K, V> inputData) {
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

    @Override
    public boolean isValid(Map<?, ?> inputData) {
        if (inputData != null) {
            if (((size > 0) && (inputData.size() != size)) || (!checkForRulesCompliance(inputData))) {
                return false;
            }
        } else {
            if (isRequired) {
                return false;
            }
        }

        return true;
    }


    public MapSchema required() {
        isRequired = true;

        return this;
    }


    public MapSchema sizeof(int limit) {
        if (limit >= 0) {
            size = limit;
        } else {
            size = (int) Integer.toUnsignedLong(limit);
        }

        return this;
    }


    public <K, T> MapSchema shape(Map<K, BaseSchema<T>>  validationRules) {
        rules = validationRules;

        return this;
    }
}
