### Hexlet tests and linter status:
[![Actions Status](https://github.com/User57134/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/User57134/java-project-78/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=User57134_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=User57134_java-project-78)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=User57134_java-project-78&metric=bugs)](https://sonarcloud.io/summary/new_code?id=User57134_java-project-78)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=User57134_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=User57134_java-project-78)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=User57134_java-project-78&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=User57134_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=User57134_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=User57134_java-project-78)


## Validator java project:
Validator is a library for checking correctness of some data.

## Usage
Firstly you create a validator object then select a validation schema and after that you apply it to check the data.
You can set restrictions to numbers, strings and maps. Also you can define restrictions for map elements.

```ts
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

var v = new Validator();
var stringSchema = v.string();

stringSchema.minLength(10).minLength(4).isValid("Hexlet"); // true

var numSchema = v.number();

numSchema.isValid(5); // true
numSchema.isValid(null); // true
numSchema.positive().isValid(null); // true

var mapSchema = v.map();

var mapSchema = v.map();
mapSchema.isValid(null); // true

mapSchema.required();

mapSchema.isValid(null); // false
mapSchema.isValid(new HashMap<>()); // true
var data = new HashMap<String, String>();
data.put("key1", "value1");
mapSchema.isValid(data); // true

```
