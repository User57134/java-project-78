package hexlet.code.schemas;


import hexlet.code.Schema;


public class StringSchema implements Schema {
    private boolean isRequired = false;
    private int length = 0;
    private String substring = "";

    public StringSchema() {
        //
    }

    @Override
    public boolean isValid(Object obj) {
        if (obj != null) {
            if (!(obj instanceof String value)) {
                throw new IllegalArgumentException("A passed data should have the string type.");
            }

            if (value.isEmpty()) {
                if (isRequired) {
                    return false;
                }
            } else {
                if (((length > 0) && (value.length() < length))
                        || ((!substring.isEmpty()) && !value.contains(substring))) {
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
