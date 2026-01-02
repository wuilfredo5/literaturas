📚 SISTEMA DE GESTIÓN DE LITERATURAS
📋 DESCRIPCIÓN
Aplicación Spring Boot que consume la API pública de Gutendex.com para buscar, almacenar y gestionar información sobre libros de dominio público. Sistema con interfaz de consola interactiva.

🚀 CARACTERÍSTICAS PRINCIPALES
🔍 Búsqueda en tiempo real de libros

💾 Almacenamiento en PostgreSQL

📊 Análisis estadístico

🌐 Filtrado por idioma

👥 Gestión de autores

🎂 Autores por período histórico

📈 Métricas y reportes

🖥️ Interfaz de consola amigable

🛠️ TECNOLOGÍAS
Backend
Java 21

Spring Boot 4.0.1

Spring Data JPA

Spring Web MVC

Base de Datos
PostgreSQL

Herramientas
Maven 3.9.12

Maven Wrapper

Spring Boot DevTools

API Externa
Gutendex API (https://gutendex.com/books)

📦 INSTALACIÓN
1. Clonar el repositorio
bash
git clone https://github.com/tu-usuario/literaturas.git
cd literaturas
2. Configurar base de datos PostgreSQL
sql
CREATE DATABASE gutendex_db;
CREATE USER gutendex_user WITH PASSWORD 'tu_password';
GRANT ALL PRIVILEGES ON DATABASE gutendex_db TO gutendex_user;
3. Configurar variables de entorno
bash
# Linux/Mac
export DB_USER=postgres
export DB_PASS=tu_password

# Windows
set DB_USER=postgres
set DB_PASS=tu_password
4. Compilar el proyecto
bash
./mvnw clean compile
5. Ejecutar la aplicación
bash
./mvnw spring-boot:run
📖 USO
Menú Principal
La aplicación muestra un menú interactivo con opciones:

🔍 Buscar libros por título

📖 Listar libros registrados

👥 Listar autores registrados

🎂 Listar autores vivos en un año

🌐 Listar libros por idioma

📊 Mostrar estadísticas

❌ Salir

Ejemplo de uso
text
Seleccione una opción (0-6): 1
Ingrese el título a buscar: Moby Dick

✅ ¡3 libros guardados exitosamente!
🏗️ ESTRUCTURA DEL PROYECTO
text
literaturas/
├── src/main/java/com/gutendex/literaturas/
│   ├── config/AppConfig.java
│   ├── client/GutendexClient.java
│   ├── console/ConsoleMenu.java
│   ├── console/ConsoleApplicationRunner.java
│   ├── model/dto/
│   ├── model/entity/
│   ├── repository/
│   ├── service/
│   └── LiteraturasApplication.java
├── src/main/resources/
│   ├── application.properties
│   ├── banner.txt
│   └── static/
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
⚙️ CONFIGURACIÓN
application.properties
properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gutendex_db
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
spring.jpa.hibernate.ddl-auto=update
gutendex.api.url=https://gutendex.com/books
server.port=8080
🧪 TESTING
bash
# Ejecutar todos los tests
./mvnw test

# Ejecutar tests específicos
./mvnw test -Dtest=BookServiceTest
🐛 SOLUCIÓN DE PROBLEMAS
Error de conexión a PostgreSQL
bash
sudo systemctl status postgresql
Error de memoria
bash
export MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=512m"
🤝 CONTRIBUCIONES
Fork el proyecto

Crea tu rama: git checkout -b feature/nueva-funcionalidad

Commit: git commit -m 'Agrega nueva funcionalidad'

Push: git push origin feature/nueva-funcionalidad

Abre un Pull Request

📄 LICENCIA
Apache License 2.0

✍️ AUTOR
Tu Nombre - GitHub

🙏 AGRADECIMIENTOS
Gutendex.com por la API pública

Project Gutenberg

Spring Boot

Alura por el desafío

⭐ ¡Si te gusta el proyecto, dale una estrella en GitHub!
