FROM amazoncorretto:17

WORKDIR /app

ADD build/libs/rabbitmq-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
