FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/mangalg01/moviebooking.git
FROM maven:3.5-jdk-8-alpine
WORKDIR /app
COPY --from= /app/moviebooking /app
RUN mvn install
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from= /app/target/MovieBooking-0.0.1-SNAPSHOT.jar /app
CMD ["java -jar MovieBooking-0.0.1-SNAPSHOT.jar"]