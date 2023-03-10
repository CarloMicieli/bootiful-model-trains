# Catalog Service

## REST API

|    Endpoint	     | Method | Req. body | Status | Resp. body | Description    		                        |
|:----------------:|:------:|:---------:|:------:|:----------:|:-----------------------------------------|
|    `/brands`     | `POST` |  `Brand`  |  201   |            | Add a new brand to the catalog           |
|  `/brands/{id}`  | `PUT`  |  `Brand`  |  204   |            | Update the brand with the given `{id}`   |
|  `/brands/{id}`  | `GET`  |           |  200   |  `Brand`   | Get the brand with the given `{id}`      |
|    `/brands`     | `GET`  |           |  200   | `Brand[]`  | Get all brands in the catalog            |
|   `/railways`    | `POST` | `Railway` |  201   |            | Add a new railway to the catalog         |
| `/railways/{id}` | `PUT`  | `Railway` |  204   |            | Update the railway with the given `{id}` |
| `/railways/{id}` | `GET`  |           |  200   |  `Railway`   | Get the railway with the given `{id}`    |
|   `/railways`    | `GET`  |           |  200   | `Railway[]`  | Get all railways in the catalog          |
|    `/scales`     | `POST` |  `Scale`  |  201   |            | Add a new scale to the catalog           |
| `/scales/{id}` | `PUT`  | `Scale` |  204   |            | Update the scale with the given `{id}`   |
| `/scales/{id}` | `GET`  |           |  200   |  `Scale`   | Get the scale with the given `{id}`      |
|   `/scales`    | `GET`  |           |  200   | `Scale[]`  | Get all scales in the catalog            |

## Useful Commands

| Gradle Command	            | Description                                   |
|:---------------------------|:----------------------------------------------|
| `./gradlew bootRun`        | Run the application.                          |
| `./gradlew build`          | Build the application.                        |
| `./gradlew test`           | Run tests.                                    |
| `./gradlew bootJar`        | Package the application as a JAR.             |
| `./gradlew bootBuildImage` | Package the application as a container image. |

After building the application, you can also run it from the Java CLI:

```bash
java -jar build/libs/catalog-service-0.0.0-SNAPSHOT.jar
```

## Running a PostgreSQL Database

Run PostgreSQL as a Docker container

```bash
docker run  -it --rm --name catalog-db-postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=mysecretpassword \
    -e POSTGRES_DB=catalogdb \
    -p 5432:5432 \
    postgres:15.1-alpine
```
