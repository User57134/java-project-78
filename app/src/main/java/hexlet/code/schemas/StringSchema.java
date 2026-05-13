package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {


    public StringSchema() {
        isDefiniteValue = (v) -> (v != null) && (!v.isEmpty());
    }


    public StringSchema required() {
        required = true;

        return this;
    }


    public StringSchema contains(String str) {
        addCheck("contains", (v) -> ((str == null) || str.isEmpty() || v.contains(str)));

        return this;
    }


    public StringSchema minLength(int len) {
        addCheck("minLength", (v) -> ((len <= 0) || (v.length() >= len)));

        return this;
    }
}

