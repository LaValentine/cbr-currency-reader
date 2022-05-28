### Soap server that reads the exchange rate from a website cbr.ru

#### Technology stack:
* Spring Web Services
* Microsoft SQLServer
* Spring Data JPA
* Lombok
* Log4j
* JAXB

## Launching the application
### Step #1
#### Set environment variables

- Module 'soap-client' 
  ```
    SERVER_URL=http://localhost:8080/ws
  ```
- Module 'soap-server' 
  ```
    SPRING_DATASOURCE_URL=jdbc:sqlserver://localhost
    SPRING_DATASOURCE_USERNAME=cbrCurrencyReader
    SPRING_DATASOURCE_PASSWORD=cbrCurrencyReader111
    SPRING_JPA_HIBERNATE_DDL_AUTO=create
  ```
### Step #2
#### Configure Data Base
```
CREATE DATABASE cbrCurrencyReader
```
```
CREATE LOGIN cbrCurrencyReader2   
WITH PASSWORD = 'cbrCurrencyReader111',
DEFAULT_DATABASE = cbrCurrencyReader
```
```
Use cbrCurrencyReader
CREATE USER cbrCurrencyReader FOR LOGIN cbrCurrencyReader
```
### Step #3
#### Start up soap-server module
```
mvn spring-boot:run
```
#### Start up soap-client module
```
mvn spring-boot:run
```
### Step 4
#### Open in browser
```
http://localhost:8081/
```
## Launching the application with docker
### Step #1
#### Up docker-compose.yml
```
docker compose up
```
### Step 2
#### Open in browser
```
http://localhost:8081/
```