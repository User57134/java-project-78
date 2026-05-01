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
        String value = null;

        if (obj != null) {
            if (!obj.getClass().equals(String.class)) {
                throw new IllegalArgumentException("A passed data should have the string type.");
            }

            value = (String) obj;
        }

        if (value == null || value.isEmpty()) {
            if (isRequired) {
                return false;
            }
        } else {
            if (((length > 0) && (value.length() < length))
                || ((!substring.isEmpty()) && !value.contains(substring))) {
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


    public String contains() {
        return substring;
    }


    public StringSchema minLength(int len) {
        length = len;
        return this;
    }


    public int minLength() {
        return length;
    }
}
