FROM maven:3.8.7-eclipse-temurin-17 AS build

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

COPY src /home/spring/src
COPY pom.xml /home/spring

WORKDIR /home/spring
RUN mvn --no-transfer-progress --update-snapshots clean package

FROM eclipse-temurin:17-jre-alpine
LABEL maintainer="Ricardo Montania Prado de Campos <ricardompcampos@gmail.com>"

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US.UTF-8
ENV LC_ALL en_US.UTF-8

WORKDIR /usr/share/service/
COPY --from=build /home/spring/target/fast-jobs.jar /usr/share/service/service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/share/service/service.jar"]