package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema<Number> {

    public NumberSchema() {
        //
    }


    public NumberSchema required() {
        required = true;

        return this;
    }


    public NumberSchema positive() {
        addCheck("positive", v -> (v.doubleValue() > 0));

        return this;
    }


    public NumberSchema range(int min, int max) {
        if ((min < 0) || (max < 0) || (max <= min)) {
            throw new IllegalArgumentException("The range minimum should be lower then range maximum, both parameter "
                    + "can not be negative, 'min, max' : " + min + ", " + max);
        }

        addCheck("range", v -> {
            var dv = v.doubleValue();
            return dv >= min && dv <= max;
        });

        return this;
    }
}
