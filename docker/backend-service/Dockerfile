FROM alpine/git:latest
WORKDIR /project
RUN git clone https://github.com/Muharsul05/backend-service.git

FROM openjdk:21
COPY --from=0 /project /opt/app/
WORKDIR /opt/app/backend-service
RUN chmod +x ./mvnw
ENV DB_HOST_NAME=postgresql
ENV DB_PORT=5432
ENV DB_NAME=test
ENV DB_USERNAME=magarusik
ENV DB_PASSWORD=1312
ENV SERVER_PORT=8080
CMD ["./mvnw", "spring-boot:run"]