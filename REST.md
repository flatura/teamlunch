##### REST API documentation with CURL examples

You can run curl commands by using GitBash 

Users:
Admin admin@gmail.com admin
User1 user1@gmail.com 12345678
User2 user2@gmail.com 12345678

###### For user with Admin role:

GET all Restaurants: 

`curl -s http://localhost:8080/api/restaurants --user admin@gmail.com:admin`

GET Restaurant with id 1: 

`curl -s http://localhost:8080/api/restaurants/1 --user admin@gmail.com:admin`

DELETE Restaurant with id 1: 

`curl -s -X DELETE http://localhost:8080/api/restaurants/1/delete --user admin@gmail.com:admin`

CREATE Restaurant: 

`curl -s -X POST -d '{"name":"Teremok", "enabled": "true"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/restaurants/create --user admin@gmail.com:admin`

UPDATE Restaurant: 

`curl -s -X PUT -d '{"id": 1, "name": "Tanuki2", "enabled": "True"}' -H 'Content-Type: application/json' http://localhost:8080/api/restaurants/1/update --user admin@gmail.com:admin`

GET all Dishes:

`curl -s http://localhost:8080/api/dishes --user admin@gmail.com:admin`

GET Dish with id 4

`curl -s http://localhost:8080/api/dishes/4 --user admin@gmail.com:admin`

DELETE Dish with id 5:

`curl -s -X DELETE http://localhost:8080/api/dishes/5/delete --user admin@gmail.com:admin`

CREATE Dish:

`curl -s -X POST -d '{"date":"2019-03-20", "name": "Cocktail B52", "price": 200, "restaurant" : {"id": 2, "name": "Durdin", "enabled": "true"}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/dishes --user admin@gmail.com:admin`

UPDATE Dish:

`curl -s -X PUT -d '{"id": 5, "date":"2019-03-20", "name": "Cocktail B52", "price": 200, "restaurant" : {"id": 2, "name": "Durdin", "enabled": "true"}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/dishes/5/update --user admin@gmail.com:admin`

###### For user with User role:

GET all Restaurants:

`curl -s http://localhost:8080/api/restaurants --user user1@gmail.com:12345678`

GET dishes of Restaurant with id 2 and date 20 march 2019

`curl -s http://localhost:8080/api/restaurants/2/dishes?date=2019-03-20 --user user1@gmail.com:12345678`

GET vote for User and date: 

`curl -s http://localhost:8080/api/votes?date=2019-03-20 --user user1@gmail.com:12345678`

GET Vote history for User with id 1:

`curl -s http://localhost:8080/api/votes/history --user user1@gmail.com:12345678`

GET Vote history from 1 May 19 for User with id 1:

`curl -s http://localhost:8080/api/votes/history?startDate=2019-05-01 --user user1@gmail.com:12345678`

CREATE(UPDATE) Vote:

`curl -s -X POST http://localhost:8080/api/votes/1 --user user1@gmail.com:12345678`