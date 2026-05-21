FROM maven:3.9.6-eclipse-temurin-8

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

CMD ["mvn", "test", "-Dbrowser=chrome"]