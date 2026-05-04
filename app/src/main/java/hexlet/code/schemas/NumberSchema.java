package hexlet.code.schemas;


import hexlet.code.Schema;


public class NumberSchema implements Schema {
    static class Range {

        Range(int minValue, int maxValue) {
            if (minValue <= maxValue) {
                min = minValue;
                max = maxValue;
            } else {
                throw new RuntimeException(("Logical error: a range minimum '"
                        + minValue
                        + "' is greater than a range maximum '"
                        + maxValue + "'"));
            }

        }

        public int min;
        public int max;
    }

    private boolean isRequired = false;
    private boolean isPositive = false;
    private Range valuesRange = null;

    public NumberSchema() {
        //
    }

    @Override
    public boolean isValid(Object obj) {
        if (obj != null) {
            if (!(obj instanceof Number value)) {
                throw new IllegalArgumentException("A passed data should have the number type.");
            }

            var dv = value.doubleValue();
            if ((isPositive) && (dv < 0)) {
                return false;
            }

            if (valuesRange != null) {
                if ((dv < valuesRange.min) || (dv > valuesRange.max)) {
                    return false;
                }
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
