# SpringCloud+SpringBoot+LCN项目骨架

　　SpringCloud（Finchley.RELEASE）+SpringBoot（2.0.7）项目骨架，eureka+config+bus+feign+ribbon+hystrix+zuul等组件支持，MyBatis+Redis+MongoDB+RabbitMQ+Elasticsearch等集群配置，LCN（5.0.0.RC2）分布式事务框架，支持Docker部署

# Docker容器中间件部署

## Mysql

> docker pull docker.io/mysql:5.7

> docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/mysql/conf:/etc/mysql -v /mnt/docker/mysql/log:/var/log/mysql -v /mnt/docker/mysql/data:/var/lib/mysql -d mysql:5.7

docker exec -it mysql mysql -uroot -p123456

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456';

FLUSH PRIVILEGES;


## Elasticsearch

vi /etc/sysctl.conf

vm.max_map_count=655360

sysctl -p

> docker pull elasticsearch:5.6.8

> docker run -p 9200:9200 -p 9300:9300 --name elasticsearch -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml -v /mnt/docker/elasticsearch/data:/usr/share/elasticsearch/data -v /mnt/docker/elasticsearch/plugins:/usr/share/elasticsearch/plugins -v /mnt/docker/elasticsearch/logs:/usr/share/elasticsearch/logs -d elasticsearch:5.6.8

curl -XPUT http://192.168.0.146:9200/index

curl 'http://192.168.0.146:9200/index/_analyze?analyzer=ik_max_word&pretty=true' -d '{"text":"我们是大数据开发技术人员"}'

### elasticsearch-ik

https://github.com/medcl/elasticsearch-analysis-ik/releases 去这里下载一个版本5.6.x版本的插件，解压到/mnt/docker/elasticsearch/plugins下重启容器即可

### elasticsearch-head

> docker pull mobz/elasticsearch-head:5

> docker run -p 9100:9100 --name elasticsearch-head -v /etc/localtime:/etc/localtime:ro -d mobz/elasticsearch-head:5


## Mongodb

> docker pull mongo:3.2

> docker run -p 27017:27017 --name mongodb -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/mongodb/db:/data/db -d mongo:3.2


## Redis

> docker pull redis:3.2

> docker run -p 6379:6379 --name redis -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/redis/data:/data -d redis:3.2 redis-server --appendonly yes


## Rabbitmq

> docker pull rabbitmq:management

> docker run -p 5672:5672 -p 15672:15672 --name rabbitmq -v /etc/localtime:/etc/localtime:ro -d rabbitmq:management


### 下面是我保存的一些镜像

> 709931138/mall:mysql-5.7

> 709931138/mall:redis-3.2
 
> 709931138/mall:mongo-3.2

> 709931138/mall:elasticsearch-5.6.8

> 709931138/mall:elasticsearch-head-5

> 709931138/mall:rabbitmq-3.7.8


# Service启动后的测试地址


**#erureka-server**

> http://192.168.5.20:1001/

**#config-server**

> http://192.168.5.20:2001/service-member-dev.yml

> http://192.168.5.20:2001/service-member-test.yml

> http://192.168.5.20:2001/service-member-prd.yml

> http://192.168.5.20:2001/service-product-dev.yml

> http://192.168.5.20:2001/service-product-test.yml

> http://192.168.5.20:2001/service-product-prd.yml

> http://192.168.5.20:2001/service-order-dev.yml

> http://192.168.5.20:2001/service-order-test.yml

> http://192.168.5.20:2001/service-order-prd.yml

> http://192.168.5.20:2001/actuator/bus-refresh POST

**#service-member**

> http://192.168.5.20:8001/profile

> http://192.168.5.20:8001/api/user?username=baicai

> http://192.168.5.20:8001/api/user/cache?username=baicai

> http://192.168.5.20:8001/api/user/mongo?username=baicai

> http://192.168.5.20:8001/api/user/search?username=baicai

**#service-product**

> http://192.168.5.20:8003/profile

> http://192.168.5.20:8003/api/product?sn=SN123456

> http://192.168.5.20:8003/api/product/cache?sn=SN123456

> http://192.168.5.20:8003/api/product/mongo?sn=SN123456

> http://192.168.5.20:8003/api/product/search?sn=SN123456

**#service-order**

> http://192.168.5.20:8002/profile

> http://192.168.5.20:8002/api/order?sn=Q123456

> http://192.168.5.20:8002/api/order/cache?sn=Q123456

> http://192.168.5.20:8002/api/order/mongo?sn=Q123456

> http://192.168.5.20:8002/api/order/search?sn=Q123456

> http://192.168.5.20:8002/api/order/tx?sn=Q123456&productId=1&memberId=1

**#mall-portal**

> http://192.168.5.20:9001/api/find/data?username=baicai&productSn=SN123456&orderSn=Q123456

> http://192.168.5.20:9001/apigateway/member/api/user?username=baicai

> http://192.168.5.20:9001/apigateway/product/api/product?sn=SN123456

> http://192.168.5.20:9001/apigateway/order/api/order?sn=Q123456