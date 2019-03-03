FROM openjdk:8-jre-alpine
MAINTAINER "Sheshenya Petr"
# label for the image
LABEL Description="Gateway Server"

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/myservice/lib
# Add the service itself
ARG JAR_FILE
COPY target/${JAR_FILE} /usr/share/service/gateway-server.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/service/gateway-server.jar"]