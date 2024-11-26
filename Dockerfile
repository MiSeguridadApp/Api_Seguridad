# Utiliza una imagen base con Java
FROM openjdk:17-jdk-slim

# Copia el archivo JAR desde el directorio target de Maven
COPY target/Api-SecurityApp-0.0.1-SNAPSHOT.jar /app/mi-aplicacion.jar

# Expone el puerto 8080 para la aplicación
EXPOSE 8080

# Define el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/mi-aplicacion.jar"]
