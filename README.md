# Catálogo de Libros - LiterAlura Challenge

Este proyecto es un catálogo de libros que utiliza la API de Gutendex y persiste los datos en una base de datos PostgreSQL gestionada con Docker.

## Prerrequisitos

*   **Java JDK 17** o superior.
*   **Docker** y **Docker Compose**.

## Cómo Ejecutar

1.  **Iniciar la base de datos:**
    Desde la raíz del proyecto, ejecuta el siguiente comando para iniciar el contenedor de PostgreSQL en segundo plano:
    ```bash
    docker compose up -d
    ```

2.  **Ejecutar la aplicación Java:**
    Puedes ejecutar la aplicación desde tu IDE (como IntelliJ IDEA) o usando el wrapper de Maven incluido:

    *   **En Linux/macOS:**
        ```bash
        ./mvnw spring-boot:run
        ```
    *   **En Windows:**
        ```bash
        ./mvnw.cmd spring-boot:run
        ```

La aplicación se conectará automáticamente a la base de datos que se está ejecutando en Docker.