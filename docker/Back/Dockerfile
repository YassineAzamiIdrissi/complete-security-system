FROM maven:3.8.7-openjdk-18 AS build-stage
WORKDIR /build_folder
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
ARG PROFILE=dev
ARG VERSION=1.0.3
WORKDIR /app
COPY --from=build-stage /build_folder/target/robust.api.security.system-*.jar /app/
EXPOSE 8088
ENV DB_URI=jdbc:postgresql://postgres_container:5432/draft
ENV CURRENT_PROFILE=${PROFILE}
ENV APP_VERSION=${VERSION}

CMD java -jar -Dspring.profiles.active=${CURRENT_PROFILE} -Dspring.datasource.url=${DB_URI} robust.api.security.system-${APP_VERSION}.jar




