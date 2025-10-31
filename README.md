# 🍽️ Master Chef API

## 📖 Descripción general

**Master Chef API** es una aplicación desarrollada para gestionar y compartir recetas del reconocido programa de telerrealidad de cocina *Master Chef*.  
La API permite consultar las recetas presentadas a lo largo de las temporadas y también ofrece un espacio interactivo donde los televidentes pueden publicar sus propias creaciones.  
Cada receta incluye su título, lista de ingredientes, pasos de preparación, el nombre del chef y, en caso de ser un participante del programa, la temporada en la que fue presentada.

## 🚀 Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data MongoDB**
- **MapStruct**
- **Lombok**
- **SpringDoc OpenAPI (Swagger UI)**
- **Maven**

## ▶️ Ejecución del proyecto

1. **Clonar el repositorio**
```bash
git clone https://github.com/tulio3101/master-chef-api
```
2. **Acceder al repositorio**
```bash
cd master-chef-api
```
3. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
```

## 🧱 Estructura del proyecto
```markdown
master-chef-api/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── edu/
│ │ │ └── dosw/
│ │ │ └── masterchef/
│ │ │ ├── config/
│ │ │ ├── controller/
│ │ │ ├── exception/
│ │ │ ├── mapper/
│ │ │ ├── model/
│ │ │ │ ├── entity/
│ │ │ │ │   ├── enums/
│ │ │ │ └── dto/
│ │ │ │ │   ├── request/
│ │ │ │ │   ├── response/
│ │ │ ├── repository/
│ │ │ ├── service/
│ │ │ └── MasterChefApiApplication.java
│ │ └── resources/
│ │ ├── application.properties
│ └── test/
│ └── java/
└── pom.xml
```

## ⚙️ Configuración del entorno

### 🔑 Variables y conexión a MongoDB

## 🧩 Estructura de la base de datos

### 🗂️ Colecciones principales

Para la Solución del ejercicio solo necesitamos de la siguiente colección:

- Recipe

A partir de esto podemos realizar todas las funcionalidades que se nos presenta en el enunciado.

### 🧮 Relaciones y embebidos

A partir de la identificación de las colecciones principales, se va a manejar con un unico **@Document** que es **Recipe**, ya que tanto **chefs** como **ingredients** van a ir embebidos en esta clase 


### 🧱 Ejemplo de documento JSON

```json
{
  "id": 6,
  "season": 6,
  "title": "Filete de Res en Salsa de Vino Tinto",
  "ingredients": [
    { "name": "Filete de res" },
    { "name": "Vino tinto" },
    { "name": "Cebolla" },
    { "name": "Ajo" },
    { "name": "Mantequilla" },
    { "name": "Caldo de carne" }
  ],
  "chef": {
    "name": "Carlos Mendoza",
    "role": "JURY"
  },
  "steps": [
    "Sellar los filetes con mantequilla hasta dorar.",
    "Retirar la carne y saltear la cebolla y el ajo.",
    "Desglasar con vino tinto y reducir.",
    "Agregar el caldo y cocinar hasta espesar la salsa.",
    "Servir los filetes con la salsa encima."
  ]
}
```


## Persistencia Mongo DB

Para realizar esto, primero necesitamos crear un cluster en **MongoDB Atlas**

![alt text](docs/mongo0.png)

Haremos la respectiva configuración, en la estructura del proyecto, es decir, resources/application.properties donde pondremos la dirección que nos provee para establecer la conexión, tras esto utilizaremos **MongoDB Compass**.

![alt text](docs/mongo1.png)

A través de esto podremos crear documentos mediante aplicaciones como Postman y veremos reflejados los respectivos documentos en la aplicación.


### Funcionalidades

Al levantar la aplicación, podemos hacer solicitudes mediante **Postman** para verificar todo lo que se nos pide en el enunciado

- Registrar una receta de un televidente

![alt text](docs/viewer.png)

- Registrar una receta de un participante

![alt text](docs/contestant.png)

- Registrar una receta de un chef

![alt text](docs/jury.png)

- Devolver todas las recetas guardadas

![alt text](docs/allRecipe.png)

- Devolver cada receta por su Numero de consecutivo



- Devolver las recetas que hicieron participantes del programa

![alt text](docs/getContestant.png)

- Devolver las recetas que hicieron televidentes del programa

![alt text](docs/viewers.png)

- Devolver las recetas que hicieron los chefs (Jurados) del programa

![alt text](docs/getJury.png)

- Devolver las recetas por temporada

![alt text](docs/season.png)

- Buscar recetas que incluyan un ingrediente específico

![alt text](docs/mantequilla.png)

- Eliminar una receta

![alt text](docs/delete.png)

- Actualizar una receta

![alt text](docs/actualizar.png)

---

## 📗 Tests

Contamos con pruebas unitarias, donde se logra validar que se puede registrar una receta, validar la búsqueda por ingrediente devuelva resultados correctos y validar que devuelva error si se consulta una receta inexistente.

![alt text](docs/pruebas.png)

![alt text](docs/pruebas2.png)

---
## 🎃 Github Actions

Nos apoyamos de Azure y utilizamos el workflow que nos proporciona, donde lo vamos a modificar añadiendo que se ejecuten las pruebas cada vez que se haga push, pull request a la rama develop y desplegando automaticamente en Azure cuando haga push a main.

![alt text](docs/azureWorkflow.png)

![alt text](docs/azure0.png)

![alt text](docs/azure1.png)

![alt text](docs/azure2.png)

---

## 📘 Documentación Swagger

A partir de la configuración del Swagger Config y levantando la aplicación podemos acceder mediante la dirección **http://localhost:8080/swagger-ui/index.html** para mirar la respectiva documentación.

![alt text](docs/swagger0.png)

![alt text](docs/swagger1.png)