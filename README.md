# Helidon Quickstart MP

Sample Helidon MP project that includes multiple REST operations.

## prerequisites

Start a local database:

```shell script
docker run -e ARANGO_ROOT_PASSWORD=test -p 8529:8529 --rm arangodb:3.6
``` 

## Build and run

With JDK11+
```bash
mvn package
java -jar target/arango-helidon-native-example.jar
```

## Exercise the application

```
curl -X GET http://localhost:8080/version
{"version":"3.6.4","license":"COMMUNITY","server":"arango"}
```

## Try health and metrics

```
curl -s -X GET http://localhost:8080/health
{"outcome":"UP",...
. . .

# Prometheus Format
curl -s -X GET http://localhost:8080/metrics
# TYPE base:gc_g1_young_generation_count gauge
. . .

# JSON Format
curl -H 'Accept: application/json' -X GET http://localhost:8080/metrics
{"base":...
. . .

```

## Build the Docker Image

```
docker build -t arango-helidon-native-example .
```

## Start the application with Docker

```
docker run --rm -p 8080:8080 arango-helidon-native-example:latest
```

Exercise the application as described above

## Deploy the application to Kubernetes

```
kubectl cluster-info                         # Verify which cluster
kubectl get pods                             # Verify connectivity to cluster
kubectl create -f app.yaml                   # Deploy application
kubectl get service arango-helidon-native-example    # Verify deployed service
```

## Build a native image with GraalVM

GraalVM allows you to compile your programs ahead-of-time into a native
 executable. See https://www.graalvm.org/docs/reference-manual/aot-compilation/
 for more information.

You can build a native executable in 2 different ways:
* With a local installation of GraalVM
* Using Docker

### Local build

Download Graal VM at https://www.graalvm.org/downloads, the version
 currently supported for Helidon is `20.0`.

```
# Setup the environment
export GRAALVM_HOME=/path
# build the native executable
mvn package -Pnative-image
```

You can also put the Graal VM `bin` directory in your PATH, or pass
 `-DgraalVMHome=/path` to the Maven command.

See https://github.com/oracle/helidon-build-tools/tree/master/helidon-maven-plugin#goal-native-image
 for more information.

Start the application:

```
./target/arango-helidon-native-example
```

### Multi-stage Docker build

Build the "native" Docker Image

```
docker build -t arango-helidon-native-example-native -f Dockerfile.native .
```

Start the application:

```
docker run --rm -p 8080:8080 arango-helidon-native-example-native:latest
```


## Build a Java Runtime Image using jlink

You can build a custom Java Runtime Image (JRI) containing the application jars and the JDK modules 
on which they depend. This image also:

* Enables Class Data Sharing by default to reduce startup time. 
* Contains a customized `start` script to simplify CDS usage and support debug and test modes. 
 
You can build a custom JRI in two different ways:
* Local
* Using Docker


### Local build

```
# build the JRI
mvn package -Pjlink-image
```

See https://github.com/oracle/helidon-build-tools/tree/master/helidon-maven-plugin#goal-jlink-image
 for more information.

Start the application:

```
./target/arango-helidon-native-example/bin/start
```

### Multi-stage Docker build

Build the "jlink" Docker Image

```
docker build -t arango-helidon-native-example-jlink -f Dockerfile.jlink .
```

Start the application:

```
docker run --rm -p 8080:8080 arango-helidon-native-example-jlink:latest
```

See the start script help:

```
docker run --rm arango-helidon-native-example-jlink:latest --help
```
