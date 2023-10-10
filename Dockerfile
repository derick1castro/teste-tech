#FROM openjdk:17-jdk-alpine
#COPY target/manager_restaurant-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

# Dockerfile para construir e executar um aplicativo Java Spring Boot

# Estágio 1: Construção do aplicativo
FROM maven:3.8.4-openjdk-17 as build

# Copia os arquivos de origem para o contêiner
COPY . /app
WORKDIR /app

# Compila o aplicativo
RUN mvn clean package -DskipTests

# Estágio 2: Criação do contêiner final
FROM openjdk:17-jdk-alpine

# Copia o JAR da aplicação construída no Estágio 1 para o contêiner final
COPY --from=build /app/target/*.jar app.jar

# Define o comando de entrada para executar a aplicação JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
