FROM openjdk:latest


COPY ../.. .

CMD mvn build

ENTRYPOINT app.jar
