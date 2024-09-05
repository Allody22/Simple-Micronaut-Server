FROM ghcr.io/graalvm/jdk:ol7-java17 as graalvm
WORKDIR /app
EXPOSE 8080

COPY build/libs/globalexception-0.1-all-optimized.jar app.jar

ENV JAVA_OPTS=""
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
