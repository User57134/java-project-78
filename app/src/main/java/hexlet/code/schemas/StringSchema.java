package hexlet.code.schemas;

import hexlet.code.Schema;

public class StringSchema implements Schema {
    private boolean isRequired = false;
    private int length = 0;
    private String substring = "";

    public StringSchema() {

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

        if (isRequired) {
            if ((value == null) || (value.isEmpty())) {
                return false;
            }
        }

        if (length > 0) {
            if (value.length() <= length) {
                return false;
            }
        }

        if (!substring.isEmpty()) {
            if (!value.contains(substring)) {
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
