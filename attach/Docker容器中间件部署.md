# Mysql

> docker pull docker.io/mysql:5.7

> docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/mysql/conf:/etc/mysql -v /mnt/docker/mysql/log:/var/log/mysql -v /mnt/docker/mysql/data:/var/lib/mysql -d mysql:5.7

docker exec -it mysql mysql -uroot -p123456

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456';

FLUSH PRIVILEGES;


# Elasticsearch

vi /etc/sysctl.conf

vm.max_map_count=655360

sysctl -p

> docker pull elasticsearch:5.6.8

> docker run -p 9200:9200 -p 9300:9300 --name elasticsearch -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml -v /mnt/docker/elasticsearch/data:/usr/share/elasticsearch/data -v /mnt/docker/elasticsearch/plugins:/usr/share/elasticsearch/plugins -v /mnt/docker/elasticsearch/logs:/usr/share/elasticsearch/logs -d elasticsearch:5.6.8

curl -XPUT http://192.168.0.146:9200/index

curl 'http://192.168.0.146:9200/index/_analyze?analyzer=ik_max_word&pretty=true' -d '{"text":"我们是大数据开发技术人员"}'

## elasticsearch-ik

https://github.com/medcl/elasticsearch-analysis-ik/releases 去这里下载一个版本5.6.x版本的插件，解压到/mnt/docker/elasticsearch/plugins下重启容器即可

## elasticsearch-head

> docker pull mobz/elasticsearch-head:5

> docker run -p 9100:9100 --name elasticsearch-head -v /etc/localtime:/etc/localtime:ro -d mobz/elasticsearch-head:5


# Mongodb

> docker pull mongo:3.2

> docker run -p 27017:27017 --name mongodb -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/mongodb/db:/data/db -d mongo:3.2


# Redis

> docker pull redis:3.2

> docker run -p 6379:6379 --name redis -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/redis/data:/data -d redis:3.2 redis-server --appendonly yes


# Rabbitmq

> docker pull rabbitmq:management

> docker run -p 5672:5672 -p 15672:15672 --name rabbitmq -v /etc/localtime:/etc/localtime:ro -d rabbitmq:management


# LCN:tx-manager

> docker pull 709931138/mall:tx-manager-4.2.0

> docker run -p 7000:7000 -p 9999:9999 --net=host --name tx-manager -v /etc/localtime:/etc/localtime:ro -d 709931138/mall:tx-manager-4.2.0


**下面是我保存的一些镜像**

> 709931138/mall:mysql-5.7

> 709931138/mall:redis-3.2
 
> 709931138/mall:mongo-3.2

> 709931138/mall:elasticsearch-5.6.8

> 709931138/mall:elasticsearch-head-5

> 709931138/mall:rabbitmq-3.7.8

> 709931138/mall:tx-manager-4.2.0