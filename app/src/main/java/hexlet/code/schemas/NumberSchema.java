package hexlet.code.schemas;


public class NumberSchema extends BaseSchema<Number> {
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

        public final int min;
        public final int max;
    }

    private boolean isRequired = false;
    private boolean isPositive = false;
    private Range valuesRange = null;

    public NumberSchema() {
        //
    }

    @Override
    public boolean isValid(Number value) {
        if (value != null) {
            var dv = value.doubleValue();

            if ((isPositive) && (dv < 0)) {
                return false;
            }

            if ((valuesRange != null) && ((dv < valuesRange.min) || (dv > valuesRange.max))) {
                return false;
            }

        } else {
            if (isRequired) {
                return false;
            }
        }

        return true;
    }


    public NumberSchema required() {
        isRequired = true;

        return this;
    }


    public NumberSchema positive() {
        isPositive = true;

        return this;
    }


    public NumberSchema range(int min, int max) {
        valuesRange = new Range(min, max);

        return this;
    }
}
