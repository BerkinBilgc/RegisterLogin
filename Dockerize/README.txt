./gradlew bootJar
build > libs > JAR

docker-compose up
docker-compose down

http://localhost:2222

docker image ls
docker ps

## Register (POST)
http://localhost:1111/api/authentication/register
{
   "name":"root",
   "username":"root",
   "password":"root"
}


## Login (POST)
http://localhost:8080/api/authentication/login
{
   "username":"root",
   "password":"root"
}

Bearer Token: eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJyb290IiwidXNlcklkIjoxLCJyb2xlcyI6IlVTRVIiLCJleHAiOjE2Njk0Nzk5NjN9.eRICF5a7UFd8JRpXuP52_Q9Mm4g0Cm9un06x8Q88rZq-Kx2-P-1zUgaC7Jw6RllAURnFsz0BJmrE-ZqGKyxssBeXFF6GAEdqo32P9KI7kQvT150RaAz9MDPcTBOmUzioJuMaRF6uGWvQO45Ks3QSS1cIMRPmuTEX1tiEnAbg2_XdMcUep072nqt15DJW3DEY5DSrgdNzcSBgAWLvmy9JB3RKYj_giXsCuZMD-NCRIFNKsHJf4sS52BmyO1g7Ccf1FvPBq3r3ypqcHXdMwVAPToFnDy37XqgEwst0eNOaBRebW9k5Cpqg7N2FOc8fF87zJlq51un53NqVj3VlcbnTCg


Register üzerinden Dailym  Erişmek

#CREATE (POST)
http://localhost:1111/gateway/daily
{
    "dailyHeader":"header2",
    "dailyContent":"içerik2",
    "email":"email@xyz.com",
    "password":"Şifre1"
}

#Listelemek
http://localhost:1111/gateway/daily

#FIND
http://localhost:1111/gateway/daily/1

#DELETE
http://localhost:1111/gateway/daily/1

#UPDATE
http://localhost:1111/gateway/daily/1
