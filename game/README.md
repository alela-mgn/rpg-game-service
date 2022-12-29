# Game Admin Application
## Описание
CRUD приложение, которое позволяет редактировать параметры персонажей (игроков) и назначать банны. Информация о игроках
хранится в базе данных.

***
## Системные требования
Установленные:
- Java 11
- Apache Maven
- Apache Tomcat
- MySQL
***
## Установка
- Клонируйте репозиторий
  `git clone https://github.com/alela-mgn/movie-storage.git`
- выполните команду
  `mvn clean install`
- запустите файл
  `init.sql`
***
## Запуск программы
- Выполните команду
  `java -jar target/MovieStorage-0.0.1-SNAPSHOT.jar`
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
