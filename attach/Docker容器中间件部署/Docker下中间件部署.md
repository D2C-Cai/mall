**#redis**

docker pull redis:3.2

docker run -p 6379:6379 --name redis -v /mnt/docker/redis/data:/data -d redis:3.2 redis-server --appendonly yes


**#mongodb**

docker pull mongo:3.2

docker run -p 27017:27017 --name mongodb -v /mnt/docker/mongodb/db:/data/db -d mongo:3.2


**#rabbitmq**

docker pull rabbitmq:management

docker run -p 5672:5672 -p 15672:15672 --name rabbitmq -d rabbitmq:management


**#elasticsearch**

vi /etc/sysctl.conf

vm.max_map_count=655360

sysctl -p

docker pull elasticsearch:5.6.8

docker run -p 9200:9200 -p 9300:9300 --name elasticsearch -v /mnt/docker/elasticsearch/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml -v /mnt/docker/elasticsearch/data:/usr/share/elasticsearch/data -v /mnt/docker/elasticsearch/plugins:/usr/share/elasticsearch/plugins -d elasticsearch:5.6.8

**#elasticsearch-head**

docker pull mobz/elasticsearch-head:5

docker run -p 9100:9100 --name elasticsearch-head -d mobz/elasticsearch-head:5

