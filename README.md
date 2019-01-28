# SpringCloud+SpringBoot+LCN项目骨架

　　SpringCloud（Finchley.RELEASE）+SpringBoot（2.0.7）项目骨架，eureka+config+bus+feign+ribbon+hystrix+zuul等组件支持，MyBatis+Redis+MongoDB+RabbitMQ+Elasticsearch等集群配置，LCN（5.0.0.RC2）分布式事务框架，支持Docker部署。<br>
　　作者QQ：[709931138]()

## 环境介绍
　　此项目适用于有一定开发基础的开发者使用，项目内使用的框架和中间件都是市面上非常流行的，如何搭建环境的教程不作详细介绍，请开发者自行搭建必要的环境。<br>
　　作者开发电脑局域网IP：192.168.5.20，服务器的局域网IP：192.168.0.146，要保证网络畅通，防火墙配置正确。<br>
　　这里只给出几点建议：Linux服务器作者选用CentOS版本7，JDK选用1.8，MySql数据库5.6建议直接安装在系统上。一些中间件不论单机或集群请务必安装启动：Redis, Mongodb, Rabbitmq, Elasticsearch。<br>
　　还有一个[tx-manager]()，需要redis和mysql，这个是LCN分布式事务的管理服务端，5.0以上版本是普通的SpringBoot项目，去官网下载源码，注意修改配置，mvn打包启动就行。<br>
　　下面给出Docker容器中快捷安装的方案，注意容器时区，以及目录的映射，[命令只是建议，不要照抄]()！


# 项目简介

## 模块功能
| 名称 | 介绍 | 说明 | 
| ---- | ---- | ---- |
| config-server<br> | 远程配置管理服务 | 远程配置文件地址：https://github.com/D2C-Cai/mall-config |
| erureka-server<br> | cloud微服务注册中心 | 基于REST的定位服务，以实现云端中间层服务发现和故障转移 |
| mall-portal<br> | mall商城入口服务 | 用于演示 feign+ribbon+hystrix+zuul 等组件基本的使用和配置 |
| service-member<br> | mall会员微服务(仅演示) | SpringBoot+MybatisPlus框架的业务模块微服务，可随意扩展重建，<br>附带feign+config+bus等组件以及LCN分布式事务[参与端]()的演示，<br>整合Redis+MongoDB+RabbitMQ+Elasticsearch中间件的基本使用 |
| service-order<br> | mall订单微服务(仅演示) | SpringBoot+MybatisPlus框架的业务模块微服务，可随意扩展重建，<br>附带feign+config+bus等组件以及LCN分布式事务[发起端]()的演示，<br>整合Redis+MongoDB+RabbitMQ+Elasticsearch中间件的基本使用 |
| service-product<br> | mall商品微服务(仅演示) | SpringBoot+MybatisPlus框架的业务模块微服务，可随意扩展重建，<br>附带feign+config+bus等组件以及LCN分布式事务[参与端]()的演示，<br>整合Redis+MongoDB+RabbitMQ+Elasticsearch中间件的基本使用 |

## 重点框架
| 名称 | 版本 | 说明 | 
| ---- | ---- | ---- |
| SpringCloud | Finchley.RELEASE | 与SpringBoot版本对应 |
| SpringBoot | 2.0.7 | 与SpringCloud版本对应 |
| MybatisPlus | 3.0.7.1 | Mybatis升级版，官网：https://mp.baomidou.com <br>用法详见：https://github.com/D2C-Cai/shop |
| TX-LCN | 5.0.0.RC2 | 高性能的分布式事务解决方案，官网：http://www.txlcn.org/zh-cn |

## 中间件版本
| 名称 | 版本 |
| ---- | ---- |
| MySql | 5.6 |
| Redis | 3.2 |
| Mongodb | 3.2 |
| Elasticsearch | 5.6.8 |
| Elasticsearch-ik | 5.6.x |
| Elasticsearch-head | 5 |
| Rabbitmq | 3.7.8 |


# Docker容器中间件部署

## Redis

> docker pull redis:3.2

> docker run -p 6379:6379 --name redis -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/redis/data:/data -d redis:3.2 redis-server --appendonly yes

## Mongodb

> docker pull mongo:3.2

> docker run -p 27017:27017 --name mongodb -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/mongodb/db:/data/db -d mongo:3.2


## Elasticsearch

vi /etc/sysctl.conf

vm.max_map_count=655360

sysctl -p

> docker pull elasticsearch:5.6.8

> docker run -p 9200:9200 -p 9300:9300 --name elasticsearch -v /etc/localtime:/etc/localtime:ro -v /mnt/docker/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml -v /mnt/docker/elasticsearch/data:/usr/share/elasticsearch/data -v /mnt/docker/elasticsearch/plugins:/usr/share/elasticsearch/plugins -v /mnt/docker/elasticsearch/logs:/usr/share/elasticsearch/logs -d elasticsearch:5.6.8

curl -XPUT http://192.168.0.146:9200/index

curl 'http://192.168.0.146:9200/index/_analyze?analyzer=ik_max_word&pretty=true' -d '{"text":"我们是大数据开发技术人员"}'

#### elasticsearch-ik

https://github.com/medcl/elasticsearch-analysis-ik/releases 去这里下载一个版本5.6.x版本的插件，解压到/mnt/docker/elasticsearch/plugins下重启容器即可

#### elasticsearch-head

> docker pull mobz/elasticsearch-head:5

> docker run -p 9100:9100 --name elasticsearch-head -v /etc/localtime:/etc/localtime:ro -d mobz/elasticsearch-head:5

## Rabbitmq

> docker pull rabbitmq:management

> docker run -p 5672:5672 -p 15672:15672 --name rabbitmq -v /etc/localtime:/etc/localtime:ro -d rabbitmq:management


#### 下面是我保存的一些镜像

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


## License

This project is under the Apache 2.0 license. See the [LICENSE](https://github.com/apache/incubator-dubbo/blob/master/LICENSE) file for details.