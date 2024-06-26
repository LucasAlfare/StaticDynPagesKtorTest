FROM gradle:8.5-alpine as my_build
LABEL authors="Francisco Lucas"
COPY . /app
WORKDIR /app
# gradle assemble is used to build without running tests
RUN cd /app; gradle clean; gradle assemble --no-daemon

# multi build is used to discard gradle stuff after build
FROM openjdk:17-alpine
EXPOSE 80
RUN mkdir /app
COPY --from=my_build /app /app
ENTRYPOINT ["java", "-jar", "/app/build/libs/StaticDynPagesKtorTest-1.0.jar"]
