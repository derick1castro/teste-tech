#FROM openjdk:17-jdk-alpine
#COPY target/manager_restaurant-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

# Arquivo Dockerfile baseado no arquivo número 1

# Estágio 1: Construção da aplicação
FROM builder as build

# ... (As etapas do Dockerfile original do arquivo número 1)

# Estágio 2: Criação do contêiner final
FROM openjdk:17-jdk-alpine

# Copia o JAR da aplicação construída no Estágio 1 para o contêiner final
COPY --from=build /workdir/server/target/dependency/*.jar app.jar

# Define o comando de entrada para executar a aplicação JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
