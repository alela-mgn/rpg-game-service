# Game Admin Application
## Описание
CRUD приложение, которое позволяет редактировать параметры персонажей (игроков) и назначать баны. Информация о игроках
хранится в базе данных.

***
## Системные требования
Установленные:
- Java 11
- Apache Maven 3.6.0
- Apache Tomcat 9.0.x
- MySQL 8.0.x
***
## Установка
- Клонируйте репозиторий
  ```
  git clone https://github.com/alela-mgn/rpg-game-service.git
  ```
- выполните команду
  ```
  mvn clean install
  ```
- скопируйте файл приложения `<project_root>/target/rpg-1.0-SNAPSHOT.war` в директорию `<apache_tomcat_directory>/webapps`

***
## Запуск программы
- В директории `<apache_tomcat_directory>/bin` выполнить команду 
  ```
  ./startup
  ``` 

***
CRUD endpoints
---
Endpoint :
```
GET /rest/players
```
Получить всех игроков можно по следующей ссылке:
http://localhost:8080/rest/players

Endpoint Delete:
```
DELETE /rest/players/{id}
```
Удалить игрока можно по следующей ссылке:
http://localhost:8080/rest/players/{id}

Endpoint Get:
```
GET /rest/players/{id}
```
Получить игрока по id можно по следующей ссылке:
http://localhost:8080/rest/players/{id}

Endpoint Update:
```
POST /rest/players/{id}
```
Обновить игрока можно по следующей ссылке:
http://localhost:8080/rest/players/{id}

Endpoint Save:
```
POST /rest/players
```
Сохранить игрока можно по следующей ссылке:
http://localhost:8080/rest/players

Endpoint Get:
```
GET /rest/players/count
```
Получить игрока по id можно по следующей ссылке:
http://localhost:8080/rest/players/{id}
***
