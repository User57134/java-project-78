package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema<Number> {
    static class Range {

        Range(int minValue, int maxValue) {
            if (minValue <= maxValue) {
                min = minValue;
                max = maxValue;
            } else {
                throw new IllegalArgumentException(("The range minimum '"
                        + minValue
                        + "' is greater than the range maximum '"
                        + maxValue + "'"));
            }

        }

        public boolean contains(Number n) {
            var dv = n.doubleValue();

            return dv >= min && (dv <= max);
        }


        private final int min;
        private final int max;
    }


    private boolean isPositive = false;
    private Range values = null;

    public NumberSchema() {
        checks.put("positive", (v) -> ((!isPositive) || (v.doubleValue() > 0)));
        checks.put("range", (v) -> ((values == null) || (values.contains(v))));
    }


    public NumberSchema required() {
        required = true;

        return this;
    }


    public NumberSchema positive() {
        isPositive = true;

        return this;
    }


    public NumberSchema range(int min, int max) {
        values = new Range(min, max);

        return this;
    }
}
