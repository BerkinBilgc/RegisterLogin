docker container ls
docker container stop 587830e964af  9aebe436b56d
docker container rm e385d3511ae3  9aebe436b56d

docker network ls
docker network rm dailyjar_default  microdaily-blogjar_default

docker network create daily 
2ed62da2dbd30d2ccad9088b386cb4917d4b3b2bfd9b6d9655720b810153aacd

docker network create micro 
bfd5dd5594ad2ffbd7ef4b00d7d63868316d46bb1e2100b9e824ac83560044ba

docker image ls 
mainmicro.jar  
daily.jar  

docker network ls 
docker container run --network micro --name microContainer -p 1111:1111  mainmicro.jar
docker container run --network daily --name dailyContainer -p 2222:2222  daily.jar 


docker-compose up -d

docker network ls
docker network rm networkID 
docker network prune
docker inspect networkID

docker container logs containerID
docker container exec -it containerID

ping 1111
ping 2222

docker ps 
docker container inspect 5e2f72bedafd
"NetworkSettings"
 "Networks"
    "IPAddress": "192.168.96.2",
	
	
docker container inspect 8fcce7ff1a32	
    "IPAddress": "192.168.80.2",

