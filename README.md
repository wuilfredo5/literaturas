# 📚 Sistema de Gestión de Literaturas

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)
![License](https://img.shields.io/badge/License-Apache%202.0-blue?style=for-the-badge)

Aplicación Spring Boot para gestionar información sobre libros de dominio público utilizando la API de Gutendex.com. Sistema completo con interfaz de consola interactiva, persistencia de datos y análisis estadístico.

## ✨ Características Destacadas

| Funcionalidad | Descripción |
|--------------|-------------|
| 🔍 **Búsqueda en Tiempo Real** | Consulta libros desde la API de Gutendex.com |
| 💾 **Persistencia Completa** | Almacenamiento en PostgreSQL con relaciones |
| 📊 **Análisis Estadístico** | Métricas y reportes detallados |
| 🌐 **Filtrado Multilingüe** | Búsqueda por idioma específico |
| 👥 **Gestión de Autores** | CRUD completo de autores y sus obras |
| 📅 **Búsqueda Histórica** | Autores por período temporal |
| 🖥️ **Interfaz Intuitiva** | Menú interactivo con navegación fácil |

## 🚀 Comenzando

### Prerrequisitos

- **Java 21** o superior
- **PostgreSQL 15** o superior
- **Maven 3.9+**
- **Git**

### 📦 Instalación Rápida

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/literaturas.git
   cd literaturas
   ```

2. **Configurar base de datos**
   ```sql
   CREATE DATABASE gutendex_db;
   CREATE USER gutendex_user WITH PASSWORD 'tu_password_secure';
   GRANT ALL PRIVILEGES ON DATABASE gutendex_db TO gutendex_user;
   ```

3. **Configurar variables de entorno**
   ```bash
   # Linux/Mac
   export DB_USER=gutendex_user
   export DB_PASS=tu_password_secure
   
   # Windows (PowerShell)
   $env:DB_USER="gutendex_user"
   $env:DB_PASS="tu_password_secure"
   ```

4. **Compilar y ejecutar**
   ```bash
   # Compilar
   ./mvnw clean compile
   
   # Ejecutar
   ./mvnw spring-boot:run
   
   # O ejecutar el JAR
   java -jar target/literaturas-1.0.0.jar
   ```

## 🏗️ Arquitectura del Proyecto

```
literaturas/
├── src/main/java/com/gutendex/literaturas/
│   ├── config/                 # Configuraciones Spring
│   ├── client/                 # Cliente API Gutendex
│   ├── console/                # Lógica de interfaz consola
│   ├── model/                  # Modelos de datos
│   │   ├── dto/                # Data Transfer Objects
│   │   └── entity/             # Entidades JPA
│   ├── repository/             # Repositorios Spring Data
│   ├── service/                 # Lógica de negocio
│   ├── util/             
│   └── LiteraturasApplication.java
├── src/main/resources/
│   ├── application.properties  # Configuración principal
│   ├── banner.txt             # Banner personalizado
├── src/test/                  # Pruebas unitarias
├── .mvn/                      # Maven Wrapper
├── README.md                  # Este archivo
└── pom.xml                    # Dependencias Maven
```

## ⚙️ Configuración

### application.properties
```properties
# Base de Datos
spring.datasource.url=jdbc:postgresql://localhost:tu puerto/tu base de datos
spring.datasource.username=${DB_USER:postgres}
spring.datasource.password=${DB_PASS:postgres}
spring.datasource.hikari.maximum-pool-size=10

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# API Externa
gutendex.api.url=https://gutendex.com/books
gutendex.api.timeout=5000

# Servidor
server.port=8080
server.servlet.context-path=/api

# Logging
logging.level.com.gutendex.literaturas=DEBUG
```

## 🖥️ Uso de la Aplicación

### Menú Principal Interactivo
```text
╔══════════════════════════════════════════╗
║    📚 SISTEMA DE GESTIÓN DE LITERATURAS   ║
║         Gutendex API + PostgreSQL         ║
╚══════════════════════════════════════════╝

[1] 🔍 Buscar libros por título
[2] 📖 Listar libros registrados
[3] 👥 Listar autores registrados
[4] 🎂 Listar autores vivos en un año
[5] 🌐 Listar libros por idioma
[6] 📊 Mostrar estadísticas
[7] 💾 Exportar datos a JSON
[0] ❌ Salir

Seleccione una opción (0-7): 
```

### Ejemplos de Uso

**🔍 Buscar libros:**
```text
Ingrese el título a buscar: Moby Dick
✅ Encontrados 5 libros sobre "Moby Dick"
✅ 3 libros guardados exitosamente en la base de datos
```

**📊 Ver estadísticas:**
```text
===== 📊 ESTADÍSTICAS DEL SISTEMA =====
📚 Total de libros: 150
👥 Total de autores: 89
🌐 Idiomas disponibles: 12
📈 Libros por idioma:
   - en: 85 libros (56.7%)
   - fr: 28 libros (18.7%)
   - es: 15 libros (10.0%)
📅 Años cubiertos: 1605 - 1923
```

## 🧪 Testing

```bash
# Ejecutar todos los tests
./mvnw test

# Ejecutar tests con cobertura
./mvnw test jacoco:report

# Ejecutar tests específicos
./mvnw test -Dtest=BookServiceTest
./mvnw test -Dtest=*IntegrationTest

# Ejecutar con perfil de pruebas
./mvnw test -Ptest
```


## 🐛 Solución de Problemas

### Problemas Comunes y Soluciones

| Problema | Solución |
|----------|----------|
| **Error de conexión a PostgreSQL** | Verificar servicio y credenciales |
| **OutOfMemoryError** | Aumentar memoria de Maven: `export MAVEN_OPTS="-Xmx2048m"` |
| **API no responde** | Verificar conexión a internet y timeout |
| **Encoding incorrecto** | Asegurar UTF-8: `-Dfile.encoding=UTF-8` |
| **Puerto ocupado** | Cambiar `server.port` en `application.properties` |

### Comandos de Diagnóstico
```bash
# Verificar conexión a BD
psql -h localhost -U gutendex_user -d gutendex_db

# Ver logs de la aplicación
tail -f logs/application.log

# Probar API Gutendex
curl "https://gutendex.com/books?search=moby%20dick"
```

## 🤝 Contribuciones

¡Las contribuciones son bienvenidas! Sigue estos pasos:

1. **Fork** el proyecto
2. Crea una rama: `git checkout -b feature/nueva-funcionalidad`
3. Realiza tus cambios: `git commit -m 'feat: añade nueva funcionalidad'`
4. Sube los cambios: `git push origin feature/nueva-funcionalidad`
5. Abre un **Pull Request**

### Convenciones de Commits
- `feat:` Nueva funcionalidad
- `fix:` Corrección de bug
- `docs:` Cambios en documentación
- `refactor:` Refactorización de código
- `test:` Añadir o modificar tests

## 📄 Licencia

Este proyecto está bajo la Licencia Apache 2.0. Ver el archivo [LICENSE](LICENSE) para más detalles.

## ✨ Reconocimientos

- **Gutendex.com** por la API pública de libros
- **Project Gutenberg** por el acceso a literatura de dominio público
- **Spring Boot** por el excelente framework
- **Alura** por el desafío educativo
- **Comunidad Open Source** por las herramientas utilizadas

## 📞 Soporte

- 📧 **Email**: soporte@tudominio.com
- 🐛 **Issues**: [GitHub Issues](https://github.com/tu-usuario/literaturas/issues)
- 💬 **Discusión**: [GitHub Discussions](https://github.com/tu-usuario/literaturas/discussions)

## 📈 Roadmap

- [ ] Interfaz web con Thymeleaf
- [ ] Exportación a PDF/Excel
- [ ] Sistema de favoritos
- [ ] API GraphQL
- [ ] Cache distribuido con Redis
- [ ] Microservicios desacoplados

---

<div align="center">
  <p>Hecho con ❤️ para amantes de la literatura</p>
  
  ![Estrellas](https://img.shields.io/github/stars/tu-usuario/literaturas?style=social)
  ![Forks](https://img.shields.io/github/forks/tu-usuario/literaturas?style=social)
  
  <p>⭐ ¡Si te gusta el proyecto, dale una estrella en GitHub!</p>
</div>
