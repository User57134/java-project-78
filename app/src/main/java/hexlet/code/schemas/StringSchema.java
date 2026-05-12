package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {
    private int length = 0;
    private String substring = null;


    public StringSchema() {
        isDefiniteValue = (v) -> (v != null) && (!v.isEmpty());

        checks.put("length", (v) -> ((length == 0) || ((length > 0) && (v.length() >= length))));
        checks.put("substring", (v) -> ((substring == null) || (!substring.isEmpty() && v.contains(substring))));
    }


    public StringSchema required() {
        required = true;

        return this;
    }


    public StringSchema contains(String str) {
        substring = str;

        return this;
    }


    public StringSchema minLength(int len) {
        length = len;

        return this;
    }
}

