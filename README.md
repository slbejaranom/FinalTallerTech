# Taller final del TechCamp

Para la instalación del software se deben seguir los siguientes pasos:

El primero es tener la base de datos de Oracle Express 18C la cual puede ser encontrada en este link:
https://www.oracle.com/co/database/technologies/xe-downloads.html

Luego de esto, para que sea más amigable se debe poner el SQL Developer para ejecutar los scripts de la base de datos:
https://www.oracle.com/tools/downloads/sqldev-downloads.html#license-lightbox

Después de tener estos instalados se corren en este orden los scripts de la base de datos:
-CrearUsuario.sql
-CrearBaseDatos.sql
-Triggers.sql
-ProcesoMasivo.sql

El backend corre sobre java springboot por lo cual se debe tener instalado java 11 en el computador.
https://www.oracle.com/co/java/technologies/javase-jdk11-downloads.html

Además de esto, necesitaremos NodeJS para levantar el servicio de front end, se recomienda descargar la LTS.
https://nodejs.org/es/

Para compilar el backend y correrlo se utilizará gradle, se debe agregar a las variables del entorno su aplicativo
https://gradle.org/next-steps/?version=7.1.1&format=bin

Luego de agragarlo a las variables del entorno se abre un cmd y se ejecuta el comando

gradle build

Finalmente para correrlo se ejecuta

gradle bootRun

Así en la consola debería verse corriendo la aplicación de springboot para el backend
