package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private boolean isRequired = false;
    private int size = 0;

    private Map<?, ?> rules = null;

    public MapSchema() {
        //
    }

    @Override
    public boolean isValid(Map<?, ?> inputData) {
        if (inputData != null) {
            if ((size > 0) && (inputData.size() != size))  {
                return false;
            }
        } else {
            if (isRequired) {
                return false;
            }
        }

        return true;
    }


    public MapSchema required() {
        isRequired = true;

        return this;
    }


    public MapSchema sizeof(int limit) {
        if (limit >= 0) {
            size = limit;
        } else {
            size = (int) Integer.toUnsignedLong(limit);
        }

        return this;
    }
}
