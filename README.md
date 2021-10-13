# e-shop-be

## HEROKU
Project is running on Heroku https://eshopbe.herokuapp.com/api/public/swagger-ui/index.html

## Mysql for Docker

```bash
docker run -it -e TZ='Europe/Istanbul' --name mysql8 -e MYSQL_ROOT_PASSWORD='root' -d -p 3306:3306 mysql:8.0.20 --character-set-server=utf8 --collation-server=utf8_general_ci --lower_case_table_names=1
```
## Swagger
```bash
http://localhost:8080/api/public/swagger-ui.html
```

## Default created data


### User Table
|email|name|surname|pass|role|permissions|
|:---:|:---:|---:|---|:---:|:---:|
|yk@gmail.com| Yasin| Kılınç|123123|ROLE_ADMIN|VIEW_PROFILE,VIEW_DASHBOARD

## Run Docker Project
* Firstly, you need to run **mvn clean and install** on IntelliJ IDEA.

```bash
docker-compose up
```

You can access project http://localhost:8082

