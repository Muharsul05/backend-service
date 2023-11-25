FROM alpine/git:latest
WORKDIR /project
RUN git clone https://github.com/Muharsul05/backend-service.git 

FROM openjdk:21
COPY --from=0 /project /opt/app/
WORKDIR /opt/app/backend-service
RUN chmod +x ./mvnw
CMD ["./mvnw", "spring-boot:run"]