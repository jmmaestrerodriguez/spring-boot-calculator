# Microservicio Calculadora

Proyecto basado en microservicios de Spring Boot, cuya funcionalidad es la de realizar cálculos aritméticos simples.

## Stack tecnológico

* Java JDK 11
* Maven 3.9.1
* Spring Boot 2

## Estructura
La estructura del proyecto es la siguiente:
```text
├───lib
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───jmmr
│   │   │           └───calculator
│   │   │               ├───config
│   │   │               ├───controller
│   │   │               │   └───impl
│   │   │               ├───dto
│   │   │               ├───enums
│   │   │               ├───exception
│   │   │               ├───log
│   │   │               ├───mapper
│   │   │               ├───model
│   │   │               └───service
│   │   └───resources
│   │       ├───static
│   │       └───templates
│   └───test
│       └───java
│           └───com
│               └───jmmr
│                   └───calculator
│                       ├───controller
│                       ├───exception
│                       └───service
```
* config: Clases de configuración habituales (Swagger, ...) 
* controller: Para evitar un problema habitual que se da en los proyectos SpringBoot, se han separado los controladores en implementación e interfaz, de tal forma que las anotaciones habituales (@ApiResponse, @RequestMapping...) viven en la interfaz y la implementación queda más limpia.
* dto: Ruta en la que viven los objetos de transferencia de datos.
* enums: Tipos enumerados para valores determinados.
* exception: Gestión de excepciones
* log: Implementación de un logger (usando la librería Tracer)
* mapper: Mapeadores de modelos a DTOs
* model: Modelo de dominio (no persistido en este caso)
* service: Capa de servicios

## Dependencias

Las dependencias vienen detalladas en el fichero pom.xml, no obstante cabe mencionar que se ha incluido una dependencia gestionada llamada tracer:

```xml
        <!-- Tracer -->
		<dependency>
			<groupId>io.corp.calculator</groupId>
			<artifactId>tracer</artifactId>
			<version>1.0.0</version>
		</dependency>
```
Figura su instalación en la sección "build".

## Funcionalidades
Como se ha mencionado, la funcionalidad principal de esta calculadora es la de realizar operaciones aritméticas simples. En esta primera versión, las operaciones soportadas son la suma y la resta, pero podrían añadirse el resto.

## Puesta en marcha en local y funcionamiento
Para poner en funcionamiento el microservicio, lo primero será construirlo:

```shell
mvn clean install
```

Una vez hecho lo anterior, podrá ser puesto en marcha usando el comando
```shel
mvn spring-boot:run
```
Finalmente, el microservicio se encontrará desplegado en http://localhost:8080/, o lo que es lo mismo, http://127.0.0.1:8080/

### Invocando a la API
Para invocar a la API, podemos usar una herramienta como Postman o CURL, pero se recomienda acceder a la interfaz visual provista por Swagger, y así obtener más información (especificación OpenAPI) acerca de los endpoints disponibles, los tipos de entrada aceptados en las solicitudes, y las respuestas.  

Para acceder al Swagger, basta con entrar a http://localhost:8080/swagger-ui/

## Tests
De nuevo, usando el comando mvn, podremos ejecutar los tests mediante

```shell
mvn clean test
```

