# Music Tracker


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software

```
Java
Maven
Mysql Database
```

### Installing

Clone the repository
```
git clone https://github.com/MarceloSilva00/musicTracker.git
```


Create database

```
mysql -u <DB_USERNAME>
CREATE DATABASE <DB_DATABASE_NAME>
```

Edit the `application.properties` file located on `musicTracker/src/main/resources/` 

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://<DB_ADDRESS>:3306/<DB_DATABASE_NAME>
spring.datasource.username=<DB_USERNAME>
spring.datasource.password=<DB_PASSWORD>
```

## Running

```
mvn clean package
cd target/
java -jar musicTracker-0.0.1.jar
```

## Built With

* [Spring](https://spring.io/) - Framework used
* [Maven](https://maven.apache.org/) - Dependency Management