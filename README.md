# arango-helidon-native-example

Sample Helidon MP project that includes multiple REST operations.

## prerequisites

Start a local database:

```shell script
SSL=true ./docker/start_db.sh
``` 

## Build and run

With JDK21
```bash
mvn package
java -jar target/arango-helidon-native-example.jar
```

## Exercise the application

Basic:
```
curl -X GET http://localhost:8080/version
{"license":"COMMUNITY","server":"arango","version":"3.12.0-2"}
```

## Building a Native Image

You can build a native binary using Maven as follows:

```
mvn -Pnative-image package -DskipTests
```

You can then execute your native executable with:

```
./target/arango-helidon-native-example
```
