### curl samples (application deployed in application context `votingsystem`).

#### vote for Restaurants 100000
`curl -s -i -X POST -d '{}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/profile/votes/restaurants/100000/vote  --user user@yandex.ru:password`

#### update vote for Restaurants 100000 today
`curl -s -X PUT -d '{}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/profile/votes/restaurants/100000/vote/100025 --user user@yandex.ru:password`

#### get All Votes for Users 100021
`curl -s http://localhost:8080/votingsystem/rest/profile/votes --user user@yandex.ru:password`

#### get All Restaurants
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants --user admin@gmail.com:admin`

#### get Restaurants 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000 --user admin@gmail.com:admin`

#### delete Restaurants 100000
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/restaurants/100000 --user admin@gmail.com:admin`

#### create Restaurants
`curl -s -X POST -d '{"name":"New Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/admin/restaurants --user admin@gmail.com:admin`

#### update Restaurants 100026
`curl -s -X PUT -d '{"name":"Updated"}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/admin/restaurants/100026 --user admin@gmail.com:admin`

#### get Restaurants 100000 with day menu
`curl -s http://localhost:8080/votingsystem/rest/profile/restaurants/100000/menutoday --user user@yandex.ru:password`

#### get All Restaurants with day menu
`curl -s http://localhost:8080/votingsystem/rest/profile/restaurants/menutoday --user user@yandex.ru:password`

#### get Menus 100003 for Restaurants 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003 --user admin@gmail.com:admin`

#### get Menus 100003 with dishes for Restaurants 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/with --user admin@gmail.com:admin`

#### get Menus 100003 with dishes for today Restaurants 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/today --user admin@gmail.com:admin`

#### get All Menus for Restaurants 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus --user admin@gmail.com:admin`

#### get All Menus with dishes for Restaurants 100000
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/dishes --user admin@gmail.com:admin`

#### delete Menus 100004 for Restaurants 100000
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100004 --user admin@gmail.com:admin`

#### create Menus for Restaurants 100000
`curl -s -X POST -d '{"localDate":"2020-07-05","description":"New Menu"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus --user admin@gmail.com:admin`

#### update Menus 100003
`curl -s -X PUT -d '{"localDate":"2020-07-06","description":"Updated"}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003 --user admin@gmail.com:admin`

#### get Dishes 100009 for Menus 100003
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/dishes/100009 --user admin@gmail.com:admin`

#### delete Dishes 100009 for Menus 100003
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/dishes/100009 --user admin@gmail.com:admin`

#### get All Dishes for Menus 100003
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/dishes --user admin@gmail.com:admin`

#### update Dishes 100010 for Menus 100003
`curl -s -X PUT -d '{"name": "Updated dish","price": 300}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/dishes/100010 --user admin@gmail.com:admin`

#### create Dishes for Menus 100003
`curl -s -X POST -d '{"name": "New dish","price": 150}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/admin/restaurants/100000/menus/100003/dishes --user admin@gmail.com:admin`

#### get All Users
`curl -s http://localhost:8080/votingsystem/rest/admin/users --user admin@gmail.com:admin`

#### get Users 100021
`curl -s http://localhost:8080/votingsystem/rest/admin/users/100021 --user admin@gmail.com:admin`

#### register Users
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/profile/register`

#### get Profile
`curl -s http://localhost:8080/votingsystem/rest/profile --user test@mail.ru:test-password`

#### update Users 100021
`curl -s -X PUT -d '{"name":"Updated User","email":"updated@mail.ru","password":"updated-password"}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/admin/users/100021 --user admin@gmail.com:admin`

#### get Users by email
`curl -s http://localhost:8080/votingsystem/rest/admin/users/by?email=updated@mail.ru --user admin@gmail.com:admin`

#### delete Users 100021
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/users/100021 --user admin@gmail.com:admin`

