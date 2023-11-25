# Prueba Técnica/Práctica

# Arquitectura Microservicio

## Autor: **Adrian Evangelista**

### Indicaciones 
La solución se despliega y funciona en Docker, para iniciarla simplemente se debe ejecutar el comando:

    docker-compose up --build -d

Para bajar los servicios:

    docker-compose down

### Consideraciones que tuve en cuenta para el desarrollo:
Se crearon dos microservicios:

 - Accounts: con cuentas y movimientos 
 - Customer: con Cliente y Persona y acceso a los reportes del cliente

La comunicación entre los microservicios es asincrónica tal cual se solicitó, esto se implementó mediante colas de mensajes, utilizando ***RabbitMQ***. Como base de datos relacional se utilizó ***Mysql***.

**F1**: Se generaron los CRUDS para las entidades Cliente, Cuenta y Movimiento
Los nombres de los endpoints los generé en ingles:

 - /accounts
 - /customers
 - /movements

**F2**: La funcionalidad de registros de movimientos se implemento en el endpoint:

    POST /accounts/{accountId}/movement

**F3**: Cuando se realiza un movimiento y el saldo no es suficiente, se retorna un HTTP STATUS 404 (Bad Request). Esto se implemento con una clase específica para la Excepción: *InsufficientFundsException*
y el tratamiento de esta excepción esta especificado en *GlobalExceptionHandler*(Controller Advice).

**F4**: La generación de reportes se realiza mediante el endpoint:

    GET /reports/{customerID}1?from={dateFrom}&to={dateTo}

Este endpoint esta implementado en el microservicio ***Customer***. Al invocar el endpoint se genera un mensaje en la cola de mensajes, el cual es tomado por el microservicio Account y procede a generar el reporte según los parámetros solicitados (cliente, fecha desde y fecha hasta). 
Este reporte se generará de manera asíncrona. Para poder acceder a los reportes generados, en el microservicio ***Customer*** se debe invocar al siguiente endpoint para obtener la lista de reportes generados:

    GET /reports/customer/{customerID}

De la información resultante se puede obtener el id de reporte, con el cual se puede acceder al reporte final, con el siguiente endpoint:

    GET /reports/{reportId}/detail

**F5**: Se implemento una prueba unitaria para la clase CustomerService
**F6**: Se implemento una prueba de integrácion para probar la funcionalidad de registro de movimientos (F2-F3). Se encuentra en el microservicio ***Account***: *AccountIntegrationTest.java*
**F7**: Se creo un docker compose para el despliegue completo de los contenedores (incluye los servicios de Mysql y RabbitMQ)

### Detalles de la entrega:

 - Se incluye script de la Base de Datos: *BaseDatos.sql*
 - Se utilizó Karate para las pruebas de las API, el set de pruebas esta incluido en cada proyecto e integrado a los tests.
 - El desarrollo se encuentra en el repositorio: https://github.com/adrianevangelistalp/PracticalExerciseJava

Muchas gracias! 
