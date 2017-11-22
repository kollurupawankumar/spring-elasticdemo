# spring-elasticdemo

Running mysql using docker :
--------------------------------------------------------------

docker pull mysql

docker run --name pawan-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag  

docker exec -it pawan-mysql bash

docker logs pawan-mysql



Running elasticsearch using docker 
--------------------------------------------------------------

docker pull cdrocker/elk5

sysctl -w vm.max_map_count=262144

docker run -d -p 5601:5601 -p 9200:9200 -p 5044:5044 -v /var/lib/elasticsearch:/var/lib/elasticsearch --name elk cdrocker/elk5

docker logs -f elk


