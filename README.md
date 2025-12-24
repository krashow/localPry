#  Sistema de Gestión de Incidencias - Fullstack

Aplicación web integral para el reporte, gestión y seguimiento de incidencias técnicas. El sistema permite el control de estados, carga de archivos adjuntos y comunicación automática mediante un microservicio de notificaciones.

---

##  Estructura del Proyecto

El repositorio está organizado como un **Monorepo** para facilitar la gestión de los diferentes componentes:

* **/frontend-vue**: Interfaz de usuario moderna desarrollada con **Vue.js 3** y Vite.
* **/magri-backend**: API REST robusta desarrollada con **Java (Spring Boot)**, encargada de la persistencia y lógica de negocio.
* **/node-notifier**: Microservicio en **Node.js** especializado en el envío de alertas y notificaciones por correo electrónico.
* **/database**: Contiene los scripts SQL y archivos de respaldo (.sql / .backup) para la replicación de la base de datos.

---

##  Tecnologías Utilizadas

### Frontend
* **Vue.js 3** (Composition API)
* **Axios** para consumo de APIs
* **CSS3** (Diseño responsivo)

### Backend (Core)
* **Java 17** con **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **Jackson** para manejo de JSON y MultipartFiles

### Microservicio de Notificaciones
* **Node.js** con **Express**
* **Nodemailer** para envío de correos

### Base de Datos
* **MySQL / PostgreSQL** (según configuración de `application.properties`)

---

## Configuración e Instalación

### Requisitos previos
* Java JDK 17 o superior.
* Node.js instalado (v16+).
* Maven instalado (o usar el wrapper de Spring).
* Gestor de Base de Datos configurado.

### Pasos para ejecutar localmente

1.  **Base de Datos**:
    * Crea una base de datos llamada `sistema_incidencias`.
    * Importa el archivo ubicado en `/database/sistema_incidencias.sql`.

2.  **Backend (Spring Boot)**:
    ```bash
    cd magri-backend
    ./mvnw spring-boot:run
    ```

3.  **Servicio de Notificaciones (Node)**:
    ```bash
    cd node-notifier
    npm install
    node server.js
    ```

4.  **Frontend (Vue.js)**:
    ```bash
    cd frontend-vue
    npm install
    npm run dev
    ```

---

##  Contacto

**Kevin Agrada** - https://www.linkedin.com/in/kevi%C3%B1o-js-a-b53857294/
**Proyecto**: [https://github.com/krashow/localPry](https://github.com/krashow/localPry)

---

##  Características Destacadas
* **Gestión de Adjuntos:** Subida y almacenamiento de archivos mediante `MultipartFile`.
* **Historial Dinámico:** Seguimiento detallado de cada incidencia con cambios de estado y tiempos invertidos.
* **Notificaciones en Tiempo Real:** Integración con servicio Node para mantener informados a técnicos y usuarios.
