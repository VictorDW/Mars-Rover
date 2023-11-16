<div align="center">
   <h1>Mars Rover API</h1>
</div>
<p align="center">
  <img src="https://img.shields.io/badge/ATL Academy-Project-orange">
  <img src="https://img.shields.io/badge/Status-finalizado-blue"><br>
  <img src="https://img.shields.io/badge/Java-17-red">
  <img src="https://img.shields.io/badge/Versión-1.2-green">
</p>

👨🏻‍💻 <strong>Victor A. Agudelo</strong></br>

<a href="https://www.linkedin.com/in/victoragudelodsw/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>

## 🛠️Tools: 

- IntelliJ IDEA
- PostMan
- PhpMyadmin

## 🖥️ Tecnologías Utilizadas:

- Java 17
- Spring Boot 3
- MySQL
- JPA Hibernate
- Lombok
- SpringDoc

## :pushpin: Arquitectura

- Modelo-Vista-Controlador (MVC)
- Principios SOLID
- Patrón de diseño Data Transfer Object (DTO)
- Patrón de diseño Singleton
- Programación Orientada a Objectos
- Programación Funcional

## 📜 Descripción
<p>Bienvenidos al repositorio de la Mars Rover API, una implementación de la <a href="https://kata-log.rocks/mars-rover-kata" target="_blank"> Mars Rover Kata</a>. Este proyecto representa una API que convierte comandos enviados desde la Tierra en instrucciones comprensibles para un Rover marciano simulado. este proyecto no solo simula el movimiento y la navegación del Rover, sino que también incorpora aspectos como la limitación del mapa y la detección de obstáculos.</p>

## 📖 Funcionalidades de la API :

- <h4>Registro de Dimensiones del Mapa:</h4> Esta función permite registrar las dimensiones del mapa, asegurando que tanto el ancho como el alto estén definidos y no sean nulos.
- <h4>Consulta de Dimensiones del Mapa:</h4> Habilita la consulta de las dimensiones (ancho y alto) del mapa almacenadas en la base de datos
- <h4>Creación de Obstáculos:</h4> Facilita la creación de obstáculos, verificando que las coordenadas X y Y no sean nulas. Además, se asegura de que la posición del obstáculo no exceda los límites del mapa, que no coincida con la ubicación de otro obstáculo o del rover, y que el mapa ya esté creado.
- <h4>Consulta de Obstáculos:</h4> Permite visualizar todos los obstáculos registrados en la base de datos, incluyendo sus respectivas posiciones.
- <h4>Actualización de Obstáculo:</h4> Esta función permite actualizar la posición de un obstáculo usando su ID. Se valida que la nueva posición no exceda los límites del mapa, que no haya otro obstáculo o el rover en esa posición, y que exista un obstáculo con el ID proporcionado.
- <h4>Eliminación de Obstáculo:</h4> Habilita la eliminación de un obstáculo específico mediante su ID, asegurándose de que exista un obstáculo con dicho ID.
- <h4>Eliminación de Todos los Obstáculos:</h4>  Esta función permite eliminar todos los obstáculos registrados.
- <h4>Creación del Rover:</h4> Permite la creación del Rover, validando que todos los datos requeridos estén completos, que el nombre no esté vacío y tenga al menos tres caracteres. También se verifica que el mapa esté creado y que la posición especificada para el Rover esté dentro de los límites del mapa y no coincida con la ubicación de un obstáculo.
- <h4>Inicialización del Rover:</h4> Permite dar inicio a todas las funcionalidades necesarias del rover. Es esencial ejecutar esta función antes de realizar cualquier movimiento. Se valida que tanto el Rover como el mapa estén creados.
- <h4>Consulta del Rover:</h4> Permite acceder a los datos básicos del Rover, incluyendo su posición actual y orientación.
- <h4>Movimiento del Rover (F,B):</h4> Esta funcionalidad tiene como responsabilidad mover el rover hacia adelante o hacia atras a partir de una serie de comandos que se le envian, no sin antes validar que el comando o los comandos enviados tengan la estructura correcta, el cual debe contener la letra F (Forward) o B (Back) y si son mas de un comando que esten siempre precedidos de un coma y luego otro comando, como por ejemplo (F,B,B,F), y se toma en cuenta la orientación actual del Rover para realizar el movimiento. Se verifica en cada movimiento que no se excedan los límites del mapa y que no haya obstáculos en la trayectoria. En caso de encontrar un obstáculo o salir del mapa, se reportará el suceso y el rover permanecerá en su última posición registrada.
- <h4>Cambio de Orientación del Rover (R,L):</h4> Similar a la función de movimiento, esta funcionalidad tambien valida que el comando tengan la estructura correcta, el cual debe ser que contenga la letra L (Left) o R (Rigth), de esta manera permite cambiar la orientación del Rover, alternando entre North, East, South y West.


## 🗳️ Diagrama UML
<p>A continuación se muestra el diagrama de relación entre las clases del proyecto:</p>

![Diagrama-MarsRover](https://github.com/VictorDW/Mars-Rover/assets/15878117/a81227b2-8577-4bf1-8451-1580440995a0)

## Controlador del Mapa

![Captura de pantalla (309)](https://github.com/VictorDW/Mars-Rover/assets/15878117/b6e4f9fd-7402-4da3-90f7-e0e261f73c2e)

## Controlador del Obstáculo

![Captura de pantalla (308)](https://github.com/VictorDW/Mars-Rover/assets/15878117/806555e0-8290-47d6-b9f8-82be443e50b4)

## Controlador del Rover

![Captura de pantalla (309)-2](https://github.com/VictorDW/Mars-Rover/assets/15878117/14f3c7cd-d391-4465-b405-e1e21acc55d6)

## Datos de consulta y respuesta para consumir la API

![Captura de pantalla (310)](https://github.com/VictorDW/Mars-Rover/assets/15878117/20f27542-835a-4f4f-8c65-e63affdd151e)

![Captura de pantalla (311)](https://github.com/VictorDW/Mars-Rover/assets/15878117/958c988a-6c54-445d-9900-c6f687aa90fc)

## Ejecución de la API

<ul style = "list-style-type:circle">
<p> Conexión de la base de datos.</p>


<li> Abrir aplicaciones XAMPP y conectar los puertos: Apache y MySQL. </li>

<li> Abre la siguiente página <a href="localhost/phpmyadmin/" target="_blank"> localhost/phpmyadmin/</a> en tu navegador. </li>

<li> Crea una base de datos local con el nombre <b>mars_rover</b>. </li>

<li> Comprueba si el archivo <i>application.properties</i> esta correctamente con los datos de tu base de daba</li>

<li> Si todo es correcto puedes ejecutar Springboot en tu IDE. Sigue estos pasos: Maven -> MarsRover -> Plugins -> spring-boot -> spring-boot:run
<br> Y ahora espera a que spring arranque. </li>
<br>

<p> Ahora podras probar la API.</p>
<li>Para pobrarla tienes diferentes opciones, desde el navegador o aplicaciones como PostMan e Insomnia</li>
<li>Desde el navegador springDoc proporciona una interfaz <a href="localhost/phpmyadmin/" target="_blank">http://localhost:8080/swagger-ui/index.html#/</a> que permite realizar todas las pruebas a la API</li>
</ul>

<br>
<h7> Disfruta de una simulación enviando comandos desde la tierra al Rover en Marte.</h7>
<footer> Muchas gracias por la atención! </footer>


