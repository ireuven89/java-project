FROM openjdk:17-jdk-slim

COPY ./ /

#RUN mvn clean install

CMD ["java -jar school-0.0.1-snapshot.jar"]