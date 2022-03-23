FROM openjdk:11
COPY "./build/libs/finder-1.0.0.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]