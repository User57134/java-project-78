package hexlet.code.schemas;


public class StringSchema extends BaseSchema<String> {
    private boolean isRequired = false;
    private int length = 0;
    private String substring = "";


    public StringSchema() {
        //
    }


    @Override
    public boolean isValid(String value) {
        if ((value != null) && (!value.isEmpty())) {
            if (((length > 0) && (value.length() < length))
                    || ((!substring.isEmpty()) && !value.contains(substring))) {
                return false;
            }
        } else {
            if (isRequired) {
                return false;
            }
        }

        return true;
    }


    public StringSchema required() {
        isRequired = true;

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

