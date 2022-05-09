# CheatHub

CheatHub es una página web colaborativa dónde podrás encontrar trucos de tus videojuegos favoritos y buscar o reportar bugs.

Vídeo explicativo:

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/IktruEFStls/0.jpg)](https://www.youtube.com/watch?v=IktruEFStls)

## FUNCIONALIDADES PÚBLICAS
-	Buscar un videojuego según categoría o buscador.
-	Ver publicaciones.
-	Ver comentarios de publicaciones.

## FUNCIONALIDADES PRIVADAS
- Publicar un truco o bug.
- Comentar publicaciones.
- Puntuar publicaciones.
- Editar o eliminar tus publicaciones.
- Asignarte una foto de perfil.
- Editar datos de tu perfil.
- Los usuarios pueden seguir algún videojuego del que tengan interés.

## ENTIDADES
- Usuario: Publicará trucos, bugs, comentarios o votos. 
- Categoría: Contendrá N juegos dependiendo de su género o plataforma.
- Videojuego: Contendrá distintas publicaciones sobre el mismo.
- Publicación: Truco o bug. Contenido, consejos o códigos que pueden ayudarte con un determinado videojuego.
- Comentario: Publicado por usuarios en una publicación.

## SERVICIOS WEB
- Publicar o editar publicaciones.
- Buscar videojuegos en función de un buscador.
- Clasificar juegos mediante una categoría o plataforma.
- Puntuar un truco o bug.
- Editar información de usuario.
- Inicio de sesión, crear cuenta o salir de la cuenta.


## SERVICIOS INTERNOS
- Notificar a usuarios cuando se sube una publicación de tu interés.
- Notificar al usuario cuando otro usuario comenta o puntúa una de tus publicaciones.
- Generar una lista de los trucos o bugs  más comentados.
- Notificar al nuevo usuario por correo cuando se crea una cuenta.(?)
- Escalado de imágenes.(?)


## Diagramas

### Diagrama Entidad-Relación
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/diagrama_ER.PNG)
### Diagrama de Clases
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/CheatHubUML.png)
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/diragrama%20clases.png?raw=true)
(Diagrama actualizado a la fase 4)
### Diagrama de Flujo web
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/diagramaFlujo.png)

## Capturas web

### Index
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/index.png)
Página principal, donde podemos seleccionar qué queremos hacer.
### Categoría
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/categoria.png)
Si hacemos click en una categoría, nos mostrará todos los juegos de esta.
### Publicaciones Juego
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/publicacionesJuego.png)
Si hacemos click en un juego, se mostrarán todas las publicaciones que le correspondan.
### Publicación
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/publicacion.png)
Si hacemos click en una publicación, se mostrará la misma. Esta publicación se podrá eliminar y publicar comentarios (si estás logueado).
### Búsqueda publicación
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/busquedaPublicacion.png)
Puedes buscar publicaciones con determinados filtros. Los resultados se mostrarán en esta ventana.
### Nueva publicación
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/nuevaPublicacion.png)
La ventana para introducir los datos de la publicación a crear.
### Añadir juego
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/añadirJuego.png)
La ventana para introducir los datos del juego a crear.
### Búsqueda usuario
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/busquedaUsuario.png)
Puedes buscar usuarios por nombre. Los resultados se mostrarán en esta ventana.
### Iniciar sesión
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/iniciarSesion.png)
La ventana para hacer inicio de sesión con tu cuenta.
### Crear/Editar cuenta
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/Crear_editarCuenta.png)
La ventana que se usa para registrarse o para editar la información de tu cuenta.
### Perfil de usuario
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/Screenshots/perfilUsuario.png)
La ventana en el que podrás ver el perfil de cualquier usuario.

## CREACION DE JAR

### 1.-COMPILACIÓN DE PROYECTO Y GENERAR EJECUTABLE
- Primero compilaremos el proyecto del servicio interno. Para ello haremos click derecho en la carpeta raíz de este > Run As > Maven build...
- Nos saldrá la siguiente ventana y únicamente dentremos que poner en goals la palabra package. Luego de clickamos Run
- Estaremos atentos a la consola y cuando salga el mensaje de BUILD SUCCESS es que estará completado. Más arriba nos aparecerá el directorio en el que se ha    guardado.
Por defecto será el siguiente: carpetaProyecto\target\nombre.jar
- Ahora compilaremos la aplicación principal. Para ello primero tendremos que ejecutar el servicio interno o si no nos dará fallo. Lo podemos hacer como si fuera una aplicación normal desde el SpringBoot o en el jar creado anteriormente.
	Nosotros lo haremos desde el SpringBoot.
- Repetimos los primeros 3 pasos, pero con el proyecto de la aplicación principal.
- Copiamos los archivos .jar que se encuentran en la ruta especificada anteriormente y los llevamos a la máquina virtual. Este proceso se puede hacer de numerosas maneras, con carpetas compartidas, mediante un usb, drive, etc.

### 2.-INSTALACIONES NECESARIAS EN LA MÁQUINA VIRTUAL.
- (RECOMENDACIÓN) Para instalar la máquina virtual se recomienda seguir el siguiente videotutorial (https://www.youtube.com/watch?v=GEx046EHphI) y seleccionar la Instalación mínima.
- Instalaremos JRE necesario para ejecutar nuestra aplicación.
  - Abriremos una consola y pondremos el siguiente comando:
			`sudo apt update`
  - Aunque no deberíamos de tenerlo instalado, vamos a comprobar si tenemos Java mediante la sentencia: `java -version`. Deberá de salirnos un mensaje de que dicho           comando no ha sido encontrado, si no, entonces ya lo tendremos instalado y podremos saltar este paso.
	- Instalamos JRE predeterminado con el comando: 
			`sudo apt install default-jre`
	- Ejecutamos el comando `java -version`. Ahora si nos tendría que reconocer el comando, y veremos la versión java instalada.
	
- Ahora instalaremos mysql server y msql workbench de la siguiente manera:
	- Instalaremos primero mysql server con el siguiente comando: `sudo apt-get install mysql-server`
	- Una vez instalado, comprobaremos que se ha hecho correctamente con: `sudo mysql` 
	- Si nos muestra la versión de MySQL y nos aparecerá el cursos de mysql > , es que se habrá instalado correctamente.
	- En caso de que no sea así, se recomienda ejecutar de nuevo el comando `sudo apt-get update` y volver a repetir el subapartado.
	- Ahora instalaremos el workbench para facilitar el manejo de la base de datos. Este será un entorno gráfico de MySQL. Lo instalamos mediante el comando:
			`sudo snap install mysql-workbench-community`
	- Ahora tenemos que establecer la contraseña usada en las propiedades del proyecto de la aplicación principal para acceder a la BD. Para ello accederemos a mysql         con privilegios de administrador.
			`sudo mysql -u root -p`
	- Una vez dentro de la consola de mysql, introducimos los siguientes comandos:
    ```
    use mysql
    SELECT User, Host, plugin FROM mysql.user;
    UPDATE user SET plugin='mysql_native_password' WHERE User='root';
    FLUSH PRIVILEGES;
    ```
  -  De esta manera estamos cambiando la manera que tiene mysql de autentificar el acceso a las bases de datos. 
		Finalmente ponemos `exit` para salir de la consola de mysql
	- Ahora hay que generar la contraseña de la base de datos, con el siguiente comando. En [password] poner la contraseña que se ha establecido en application.properties en la sentencia spring.datasource.password.
			`mysqladmin -u root password [password]`
	- Finalmente, para evitar un error a la hora de conectarnos a la base de datos similar al siguiente, tendremos que otorgar los permisos ssh necesarios para que funcionen correctamente. Lo haremos con los siguientes comandos:
			``
      snap connect mysql-workbench-community:password-manager-service
			snap connect mysql-workbench-community:ssh-keys
      ``
	- Finalmente tendremos instalado y operativo mysql en nuestra máquina virtual, lista para ejectutar la aplicación.
  - Abrimos los puertos para acceder a la aplicación desde otra máquina.
  - Como se especifica que en la máquina virtual solo tenemos que estar corriendo la aplicación, accederemos a ella desde nuestra máquina principal. Para ello tenemos que abrir los puertos y conectarlos. Para ello en el administrador
			de VirtualBox, accederemos a la configuración de nuestra máquina virtual.
	- En la ventana de configuración accedemos a Red > Avanzadas > y clickamos en reenvío de puertos. 
	- Añadimos una nuev aregla clickando en el simbolo + de la derecha y en Puerto Anfitrión y Puerto Invitado ponemos 8443, que es el puerto establecido en la aplicación para https.
	- Ponemos el nombre que queramos a la regla y clickamos en aceptar.
### 3.-EJECTUAMOS LAS APLICACIONES
- Una vez que tenemos los archivos ejecutables en nuestra máquina virtual, vamos a abrir dos consolas en el directorio que se encuentren estos ficheros.
- Primero tendremos que ejecutar el servicio interno con el comando: `java -jar nombreficheroServicioInterno.jar` Vemos que se esté ejecutando correctamente
- Hacemos lo mismo con la aplicación principal.
- Una vez corriendo ambos programas en la máquina virtual, abriremos un navegador en otra máquina y accederemos a la aplicación mediante la dirección (en nuestro caso) https://localhost:8443. Y navegamos por ella al gusto.

## IMAGENES DOCKER
La aplicación estará compuesta por varios Dockerfile: uno para la aplicación web, otro para el servicio interno y otro para el balanceador de carga. No será necesario un contenedor para la base de datos ya que es externa. Todas estas imágenes serán usadas por el fichero docker-compose para crear las instancias espeficadas.

## INTERFAZ DEL SERVICIO INTERNO
Para conectar con el servicio interno usaremos el puerto 8080 y la variable de entorno SERVICIO_INTERNO_NAME, que será el nombre en la red interna creada por el docker compose del propio servicio interno. Pasaremos esta variable de entorno a cada una de las instancias de la aplicación.

## DIAGRAMA DE INFRAESTRUCTURA
![alt text](https://github.com/lm-jim/CheatHub/blob/main/CheatHub/diagramas/diagrama%20infraestructura.png?raw=true)

