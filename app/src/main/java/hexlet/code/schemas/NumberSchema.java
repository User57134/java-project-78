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
        addCheck("positive", (v) -> (v.doubleValue() > 0));

        return this;
    }


    public NumberSchema range(int min, int max) {
        addCheck("range", (v) -> {
            var dv = v.doubleValue();
            return dv >= min && dv <= max;
        });

        return this;
    }
}
