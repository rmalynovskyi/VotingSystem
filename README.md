# Voting system for deciding where to have lunch:
    * 2 types of users: admin and regular users;
    * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price);
    * Menu changes each day (admins do the updates);
    * Users can vote on which restaurant they want to have lunch at;
    * Only one vote counted per user;
    * If user votes again the same day:
       * If it is before 11:00 we asume that he changed his mind;
       * If it is after 11:00 then it is too late, vote can't be changed;
    * Each restaurant provides new menu each day.

## curl samples (application deployed in application context `votingsystem`)

### curl samples for Admins

#### get All Restaurants
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants --user admin@gmail.com:admin`

#### get Restaurant 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000 --user admin@gmail.com:admin`

#### delete Restaurant 100000
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/restaurants/100000 --user admin@gmail.com:admin`

#### create Restaurant
`curl -s -X POST -d '{"name":"New Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/admin/restaurants --user admin@gmail.com:admin`

#### update Restaurant 100026
`curl -s -X PUT -d '{"name":"Updated"}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/admin/restaurants/100026 --user admin@gmail.com:admin`

#### get Menu 100003 for Restaurant 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003 --user admin@gmail.com:admin`

#### get Menu 100003 with dishes for Restaurant 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/with?dishes --user admin@gmail.com:admin`

#### get Menu 100003 with dishes for today Restaurant 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/today?today --user admin@gmail.com:admin`

#### get All Menus for Restaurant 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus --user admin@gmail.com:admin`

#### get All Menus with dishes for Restaurant 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/with?dishes --user admin@gmail.com:admin`

#### delete Menu 100004 for Restaurant 100000
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100004 --user admin@gmail.com:admin`

#### create Menu for Restaurant 100000
`curl -s -X POST -d '{"localDate":"2020-07-05","description":"New Menu"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus --user admin@gmail.com:admin`

#### update Menu 100003
`curl -s -X PUT -d '{"localDate":"2020-07-06","description":"Updated"}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003 --user admin@gmail.com:admin`

#### get Dish 100009 for Menu 100003
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/dishes/100009 --user admin@gmail.com:admin`

#### delete Dish 100009 for Menu 100003
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/dishes/100009 --user admin@gmail.com:admin`

#### get All Dishes for Menu 100003
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/dishes --user admin@gmail.com:admin`

#### update Dish 100010 for Menu 100003
`curl -s -X PUT -d '{"name": "Updated dish","price": 300}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/dishes/100010 --user admin@gmail.com:admin`

#### create Dish for Menu 100003
`curl -s -X POST -d '{"name": "New dish","price": 150}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/dishes --user admin@gmail.com:admin`

#### get All Users
`curl -s http://localhost:8080/votingsystem/rest/admin/users --user admin@gmail.com:admin`

#### get User 100021
`curl -s http://localhost:8080/votingsystem/rest/admin/users/100021 --user admin@gmail.com:admin`

#### update User 100021
`curl -s -X PUT -d '{"name":"Updated User","email":"updated@mail.ru","password":"updated-password"}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/admin/users/100021 --user admin@gmail.com:admin`

#### get User by email
`curl -s http://localhost:8080/votingsystem/rest/admin/users/by?email=updated@mail.ru --user admin@gmail.com:admin`

#### delete User 100021
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/users/100021 --user admin@gmail.com:admin`


### curl samples for Users

#### vote for Restaurant 100000
`curl -s -i -X POST -d '{}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/profile/votes?restaurantId=100000 --user user@yandex.ru:password`

#### update vote for Restaurant 100000 today
`curl -s -X PUT -d '{}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/profile/votes/100025?restaurantId=100000 --user user@yandex.ru:password`

#### get All Votes for User 100021
`curl -s http://localhost:8080/votingsystem/rest/profile/votes --user user@yandex.ru:password`

#### get Restaurant 100000 with day menu
`curl -s http://localhost:8080/votingsystem/rest/profile/restaurants/100000/menutoday --user user@yandex.ru:password`

#### get All Restaurants with day menu
`curl -s http://localhost:8080/votingsystem/rest/profile/restaurants/menutoday --user user@yandex.ru:password`

#### register new User
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/profile/register`

#### get Profile for User
`curl -s http://localhost:8080/votingsystem/rest/profile --user test@mail.ru:test-password`


