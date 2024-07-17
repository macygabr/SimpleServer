FROM maven:3.8.5-openjdk-11
WORKDIR /app
COPY . .
CMD ["mvn", "clean", "package", "exec:java"]
