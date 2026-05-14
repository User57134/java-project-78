package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import java.util.HashMap;
import java.util.Map;


public class App {

    public static void main(String[] args) {
        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string());
        schemas.put("lastName", v.string());
        schema.shape(schemas);

        // Проверяем объекты
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        var res = schema.isValid(human1);

        int a = 5;
        int b = 3;
        int c = a + b;
    }
}
