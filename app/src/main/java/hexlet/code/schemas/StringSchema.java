package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {


    public StringSchema() {
        isDefiniteValue = v -> (v != null) && (!v.isEmpty());
    }


    public StringSchema required() {
        required = true;

        return this;
    }


    public StringSchema contains(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The substring parameter is not defined: null.");
        }

        addCheck("contains", v -> (str.isEmpty() || v.contains(str)));

        return this;
    }


    public StringSchema minLength(int len) {
        if (len < 0) {
            throw new IllegalArgumentException("The length parameter can not be lower than 0: " + len);
        }

        addCheck("minLength", v -> (v.length() >= len));

        return this;
    }
}

