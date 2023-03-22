FROM eclipse-temurin:17-jdk-jammy as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src

FROM base as build
RUN ./mvnw package

FROM eclipse-temurin:17-jre-jammy as production
EXPOSE 8082
#ARG JAR_FILE=app/target/*.jar
COPY --from=build /app/target/*.jar auth.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/auth.jar"]