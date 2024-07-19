# GraalVM Observability - JMX

This repository is only used to demonstrate an issue that I am experiencing with
GraalVM and JMX when using `LocalDateTime` as an MBean property.

This example is based on the
[Build and Run Native Executables with Remote JMX example](https://www.graalvm.org/latest/reference-manual/native-image/guides/build-and-run-native-executable-with-remote-jmx/).

## Prerequisites

- [GraalVM for JDK 21](https://www.oracle.com/java/technologies/downloads/#graalvmjava21)

## Reproduce the issue

1. Build the executable

   Make sure that you don't have Java Mission Control (JMC) or any JMX server
   running as this would fail the build. This is a different issue and not in
   scope.

   ```shell
   ./mvnw -Pnative clean package
   ```

   This will take a few seconds to complete and will create the native image at
   `./target/graalvm-observability-jmx-1.0.0`.

2. Run the executable with JMX Remote enabled

   ```shell
   ./target/graalvm-observability-jmx-1.0.0 \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Dcom.sun.management.jmxremote.port=9996 \
    -Dcom.sun.management.jmxremote.ssl=false
   ```

   The application will keep running until we execute the _exit_ operation
   through JMX.

3. Open Java Mission Control and locate the application.

   ![View in JMC](./assets/images/View%20in%20JMC.png)

   Note that while we can read the primitive `boolean` property, we cannot read
   the `LocalDateTime` property.

   ```
   WARNING: Error getting attributes: error unmarshalling return; nested exception is: java.io.EOFException
   ```
