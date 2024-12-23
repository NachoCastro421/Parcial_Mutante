# Mutant Detector API
Este proyecto es una API RESTful que permite detectar si un humano es mutante basándose en su secuencia de ADN. La API también proporciona estadísticas sobre el número de verificaciones de ADN realizadas y el porcentaje de mutantes detectados.

## Tecnologías Utilizadas
- Java
- Spring Boot para la construcción de la API
- H2 Database como base de datos en memoria para almacenar las verificaciones de ADN
- JMeter para pruebas de carga
- Postman para pruebas de carga
## Estructura del Proyecto
El proyecto se organiza en tres capas principales: Controladores, Servicios y Repositorios.

## Controladores
- **DnaController:** Controlador principal para manejar solicitudes relacionadas con la verificación de ADN.
- **StatsController:** Controlador para obtener estadísticas de las verificaciones de ADN realizadas.
## Servicios
- **DnaService:** Servicio que contiene la lógica para analizar una secuencia de ADN y determinar si un humano es mutante.
- **StatsService:** Servicio que calcula las estadísticas basadas en el número de mutantes y humanos verificados.
## Entidades
- **Base:** Entidad base que permite agregar nuevas entidades en el futuro de manera más sencilla.
- **Dna:** Entidad que representa una secuencia de ADN .
## Excepciones
- **DnaValidationExceptionHandler:** Manejador de excepciones personalizado para capturar errores en el formato del ADN y devolver mensajes de error apropiados al cliente.
- **DnaException:** devuelve mensajes de error al cliente
## Repositorios
- **DnaRepository:** Repositorio para la persistencia de datos de ADN verificados en la base de datos H2.
## DTOs (Data Transfer Objects)
- **DnaResponse:** DTO que representa la respuesta cuando se verifica una secuencia de ADN.
- **StatsResponse:** DTO que representa la respuesta para las estadísticas de ADN.
## Endpoints
1. **POST /mutant:** Verifica si una secuencia de ADN corresponde a un mutante.

  - **Cuerpo:** JSON con una lista de secuencias de ADN.
  - **Respuesta:**
    - 200 OK si el humano es mutante.
    - 403 Forbidden si el humano no es mutante.
  
2. **GET /stats:** Devuelve estadísticas de las verificaciones de ADN.

  - Respuesta: JSON con el número de mutantes, el número de humanos, y el ratio de mutantes/humanos.
## Ejemplo de Uso
### Verificar ADN de un mutante
```
POST /mutant


Content-Type: application/json


{
  "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
  - **Respuesta (Mutante)**: 200 OK
  - **Respuesta (No Mutante)**: 403 Forbidden
 ```
     Obtener Estadísticas
```
**GET /stats**
- Respuesta:
  
  
 
 {
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
 } 
```



## Pruebas
1. **Pruebas Unitarias:** Se implementaron pruebas con Postman para verificar el correcto funcionamiento de la lógica de detección de mutantes y del cálculo de estadísticas.
2. **Pruebas de Carga:** Se utilizó JMeter para verificar que la API soporte una alta cantidad de solicitudes por segundo, asegurando su estabilidad bajo carga.



## Instrucciones de Instalación
1. Clonar el repositorio desde GitHub.
2. Importar el proyecto en tu IDE de preferencia (Eclipse, IntelliJ).
3. Ejecutar el proyecto utilizando la configuración de Spring Boot.
4. La API estará disponible en https://parcial-mutante-p1ci.onrender.com.
## Entregables
- **Código Fuente:** Disponible en el repositorio de GitHub.
- **Instrucciones de Ejecución:** Este README contiene los pasos necesarios para ejecutar el programa.
- **URL de la API:** https://parcial-mutante-p1ci.onrender.com
- **Documentación:** Incluye este README para entender la estructura y el uso de la API.
Las cosas pedidades en el nivel 3 se encuentran en el archivo PDF  Nivel3.pdf
