package hexlet.code.schemas;


import java.util.Map;


public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema() {
        //
    }


    private boolean isApplicable(Map<?, ?> inputData, Map<?, ? extends BaseSchema<?>> rules) {
        return ((rules != null)  && (rules.size() == inputData.size())
                && (inputData.keySet().equals(rules.keySet())));
    }


    private boolean checkForRulesCompliance(Map<?, ?> inputData, Map<?, ? extends BaseSchema<?>> rules) {
        if (!isApplicable(inputData, rules)) {
            throw new IllegalArgumentException("The provided rules can not be applicable to the input data.");
        }

        for (var el : inputData.entrySet()) {
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

        return true;
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


    public <K, T> MapSchema shape(Map<K, BaseSchema<T>>  validationRules) {
        addCheck("shape", v -> checkForRulesCompliance(v, validationRules));

        return this;
    }
}
