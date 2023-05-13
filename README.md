Esta API esta desarrollada para dar Cumplimiento al ejercicio propuesto DEVSU.

Como tecnólogias se usaron: java 17, spring boot, hybernate, mysql, aws, lombok, mapstruck, maven, swagger, mockito y Junit.

Para llevar a cabo la API se crearon los siguientes endpoints divididos en dos microservicios con esquemas de base de datos diferentes:

/api/v1/person

/api/v1/customer

/api/v1/account

/api/v1/movement

-----------------------------
A nivel de código su uso el diseño controller-service-repository recomendado por Spring, así mismo para el manejo de POJOs se trabajo del lado de la base de datos con objetos "dominios" y para el lado del controlador DTOs usando mappers entre puente para convertir los objetos.

A nivel de base de datos se crearon las entidades:

challenge_devsu_person.person

challenge_devsu_person.customer

challenge_devsu_account.account

challenge_devsu_account.movement
---------------------------------
Los repositorios del codigo se pueden encontrar en:

https://github.com/fagudeloor/devsu-bank-account.git

https://github.com/fagudeloor/devsu-bank-person.git


-------------------------------
Postman Collection
nube:

https://elements.getpostman.com/redirect?entityId=8417226-a8f5e139-50ee-4650-b7e3-9a4a2049be8a&entityType=collection
local:

https://www.postman.com/fdavidagudelo/workspace/david-ag27/collection/8417226-a8f5e139-50ee-4650-b7e3-9a4a2049be8a?action=share&creator=8417226