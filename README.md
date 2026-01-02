# 📚 Sistema de Gestión de Literaturas - Gutendex API

## Descripción

Aplicación Spring Boot que consume la API pública de **Gutendex.com** para buscar, almacenar y gestionar información sobre libros de dominio público. El sistema proporciona una interfaz de consola interactiva para explorar y analizar literatura clásica.

## ✨ Características Principales

- 🔍 **Búsqueda en tiempo real** de libros por título en Gutendex API
- 💾 **Almacenamiento persistente** en PostgreSQL con Spring Data JPA
- 📊 **Análisis estadístico** de libros y autores
- 🌐 **Filtrado por idioma** (inglés, español, francés, alemán, etc.)
- 👥 **Gestión de autores** con información biográfica
- 🎂 **Búsqueda de autores vivos** en un año específico
- 📈 **Métricas y estadísticas** sobre la colección
- 🖥️ **Interfaz de consola intuitiva** con menús interactivos

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 21** - Lenguaje principal
- **Spring Boot 4.0.1** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Spring Web MVC** - Cliente REST para API externa

### Base de Datos
- **PostgreSQL** - Sistema de base de datos relacional

### Herramientas
- **Maven 3.9.12** - Gestión de dependencias
- **Maven Wrapper** - Ejecución independiente de Maven
- **Spring Boot DevTools** - Recarga en caliente para desarrollo

### APIs Externas
- **Gutendex API** (https://gutendex.com/books) - Fuente de datos de libros

## 📋 Prerrequisitos

### Software Requerido
- **Java JDK 21** o superior
- **PostgreSQL 12** o superior
- **Maven 3.9+** (incluido en el wrapper)

### Variables de Entorno
```bash
# Configuración de base de datos
export DB_USER=tu_usuario_postgres
export DB_PASS=tu_password_postgres

🚀 🚀 Instalación y Configuración
1. Clonar el Repositorio
git clone <url-del-repositorio>
cd literaturas

2. Configurar Base de Datos
-- Conéctate a PostgreSQL
sudo -u postgres psql

-- Crear base de datos
CREATE DATABASE gutendex_db;

-- Crear usuario (opcional)
CREATE USER gutendex_user WITH PASSWORD 'tu_password';
GRANT ALL PRIVILEGES ON DATABASE gutendex_db TO gutendex_user;

3. Configurar Variables de Entorno
bash
# Linux/Mac
export DB_USER=postgres
export DB_PASS=tu_password

# Windows (PowerShell)
$env:DB_USER="postgres"
$env:DB_PASS="tu_password"

4. Compilar el Proyecto
bash
# Usar Maven Wrapper
./mvnw clean compile

# O con Maven instalado
mvn clean compile
5. Ejecutar la Aplicación
bash
./mvnw spring-boot:run

# O compilar y ejecutar el JAR
./mvnw clean package
java -jar target/literaturas-0.0.1-SNAPSHOT.jar
🖥️ Uso de la Aplicación
Menú Principal
Al iniciar la aplicación, se mostrará un menú interactivo con las siguientes opciones:

🔍 Buscar libros - Busca libros por título en Gutendex API y los almacena localmente

📖 Listar libros registrados - Muestra todos los libros almacenados en la base de datos

👥 Listar autores registrados - Muestra todos los autores almacenados

🎂 Listar autores vivos en un año - Filtra autores por período de vida

🌐 Listar libros por idioma - Filtra libros por código de idioma (ej: 'es', 'en')

📊 Mostrar estadísticas - Muestra métricas y análisis de la colección

❌ Salir - Cierra la aplicación

Ejemplos de Uso
Buscar Libros
text
Seleccione una opción (0-6): 1
Ingrese el título a buscar: Moby Dick

✅ ¡3 libros guardados exitosamente!
Filtrar por Idioma
text
Seleccione una opción (0-6): 5
Ingrese el código del idioma (ej: 'es' para español): es

📚 Libros en español: 15
Ver Estadísticas
text
Seleccione una opción (0-6): 6

📊 ESTADÍSTICAS DEL SISTEMA
📚 Total de libros registrados: 45
👥 Total de autores registrados: 28
🌐 Libros por idioma:
   en: 30 libros
   es: 10 libros
   fr: 5 libros
🏗️ Estructura del Proyecto
text
literaturas/
├── src/main/java/com/gutendex/literaturas/
│   ├── config/                 # Configuraciones Spring
│   │   └── AppConfig.java
│   ├── client/                 # Cliente para API externa
│   │   └── GutendexClient.java
│   ├── console/               # Interfaz de consola
│   │   ├── ConsoleMenu.java
│   │   └── ConsoleApplicationRunner.java
│   ├── model/
│   │   ├── dto/              # Objetos de transferencia de datos
│   │   │   ├── AuthorDTO.java
│   │   │   ├── BookDTO.java
│   │   │   ├── BookMapper.java
│   │   │   ├── BookResponseDTO.java
│   │   │   ├── GutendexResponseDTO.java
│   │   │   └── PersonDTO.java
│   │   └── entity/           # Entidades JPA
│   │       ├── Author.java
│   │       ├── Book.java
│   │       └── BookFormats.java
│   ├── repository/           # Repositorios Spring Data JPA
│   │   ├── AuthorRepository.java
│   │   └── BookRepository.java
│   ├── service/              # Lógica de negocio
│   │   ├── AuthorService.java
│   │   └── BookService.java
│   └── LiteraturasApplication.java # Clase principal
├── src/main/resources/
│   ├── application.properties # Configuración de la aplicación
│   ├── banner.txt            # Banner de inicio
│   └── static/              # Recursos estáticos
├── .mvn/                    # Maven wrapper
├── mvnw, mvnw.cmd          # Scripts Maven wrapper
├── pom.xml                 # Configuración Maven
└── HELP.md                 # Documentación de ayuda
📊 Modelo de Datos
Entidad Book
id - Identificador único

gutenbergId - ID único de Gutendex

title - Título del libro

authors - Relación muchos-a-muchos con autores

languages - Idiomas disponibles

downloadCount - Número de descargas

formats - Formatos disponibles (PDF, EPUB, etc.)

Entidad Author
id - Identificador único

name - Nombre completo

birthYear - Año de nacimiento

deathYear - Año de fallecimiento

books - Libros escritos

translatedBooks - Libros traducidos

🔧 Configuración
Archivo application.properties
properties
# Base de datos PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/gutendex_db
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}

# Configuración JPA
spring.jpa.hibernate.ddl-auto=update

# API Externa
gutendex.api.url=https://gutendex.com/books

# Puerto del servidor
server.port=8080
Personalización
Cambiar server.port para usar un puerto diferente

Modificar spring.jpa.hibernate.ddl-auto a create-drop para reiniciar la base de datos

Ajustar niveles de log en logging.level.* para depuración

🧪 Testing
bash
# Ejecutar todos los tests
./mvnw test

# Ejecutar tests específicos
./mvnw test -Dtest=BookServiceTest

# Ejecutar con cobertura
./mvnw test jacoco:report
📈 Métricas y Estadísticas
La aplicación proporciona:

Conteo total de libros y autores

Distribución por idioma

Top 5 libros más descargados

Autores con más obras

Promedio de autores por libro

🐛 Solución de Problemas
Error de Conexión a PostgreSQL
bash
# Verificar que PostgreSQL esté corriendo
sudo systemctl status postgresql

# Verificar credenciales
echo $DB_USER
echo $DB_PASS
Error de API Externa
Verificar conexión a internet

Confirmar que https://gutendex.com/books esté accesible

Revisar logs de la aplicación

Problemas de Memoria
bash
# Aumentar memoria para Maven
export MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=512m"
./mvnw spring-boot:run
🤝 Contribuciones
Las contribuciones son bienvenidas. Por favor:

Haz un Fork del proyecto

Crea una rama para tu feature (git checkout -b feature/AmazingFeature)

Haz commit de tus cambios (git commit -m 'Add some AmazingFeature')

Push a la rama (git push origin feature/AmazingFeature)

Abre un Pull Request

📄 Licencia
Este proyecto está bajo la Licencia Apache 2.0. Ver el archivo LICENSE para más detalles.

✍️ Autores
Tu Nombre - Desarrollo inicial - TuUsuario

🙏 Agradecimientos
Gutendex.com por proporcionar la API pública de libros

Project Gutenberg por el acceso a literatura de dominio público

Spring Boot por el excelente framework

Alura por el desafío que inspiró este proyecto

🚀 Próximas Características
Interfaz web con Thymeleaf

API REST para integración externa

Exportación de datos a CSV/JSON

Búsqueda avanzada con múltiples filtros

Sistema de recomendaciones

Dashboard de métricas en tiempo real

📞 Soporte
Para soporte, abra un issue en el repositorio o contacte a tu-email@ejemplo.com.

