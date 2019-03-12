# SpringCloud+SpringBoot+LCN项目骨架

　　SpringCloud（Finchley.RELEASE）+SpringBoot（2.0.7）项目骨架，eureka+config+bus+feign+ribbon+hystrix+zuul等组件支持，MyBatis+Redis+MongoDB+RabbitMQ+Elasticsearch等集群配置，LCN（5.0.0.RC2）分布式事务框架，支持Docker部署。<br>
　　作者QQ：[709931138]() 作者邮箱：[709931138@qq.com]()

## 背景介绍
　　**骨架项目的精髓：框架流行，版本要新，配置清晰，代码简洁，案例完整。依赖最小化，不拖泥带水，不自以为是。**

## 环境介绍
　　此项目适用于有一定开发基础的开发者使用，项目内使用的框架和中间件都是市面上非常流行的，如何搭建环境的教程不作详细介绍，请开发者自行搭建必要的环境。<br>
　　作者开发电脑局域网IP：192.168.5.20，服务器的局域网IP：192.168.0.146，要保证网络畅通，防火墙配置正确。<br>
　　这里只给出几点建议：Linux服务器作者选用CentOS版本7，JDK选用1.8，MySql数据库5.6建议直接安装在系统上。一些中间件不论单机或集群请务必安装启动：Redis, Mongodb, Rabbitmq, Elasticsearch。
还有一个[tx-manager]()，需要redis和mysql，这个是LCN分布式事务的管理服务端，5.0以上版本是普通的SpringBoot项目，去官网下载源码，注意修改配置，mvn打包启动就行。<br>
　　下面会给出Docker容器中快捷安装的方案，注意容器时区，以及目录的映射，[命令只是建议，不要照抄]()！


# 项目简介

## 模块功能
| 名称 | 介绍 | 说明 | 
| ---- | ---- | ---- |
| admin-server<br> | 远程监控管理服务 | 监控基于SpringBoot的应用，简洁的可视化UI |
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

## 整合TX-LCN5.0

#### 客户端配置
```
    <dependency>
        <groupId>com.codingapi.txlcn</groupId>
        <artifactId>tx-client-springcloud</artifactId>
        <version>5.0.0.RC2</version>
    </dependency>
```
```
    # tx-lcn
    tx-lcn:
      client:
        manager-address: 192.168.0.146:8070
        resource-order: 0
      logger:
        enabled: false
      message:
        netty:
          wait-time: 5000
      springcloud:
        loadbalance:
          enabled: true

```
**解释：**
这里注意我们用的是 5.0.0.RC2 版本
```
    @Configuration
    @EnableDiscoveryClient
    @EnableDistributedTransaction
    public class TransactionConfig {
    
    }
```
**解释：**
每个需要的客户端，都加上这两个标签的配置@EnableDiscoveryClient，@EnableDistributedTransaction
```
    @FeignClient(name = "service-member", fallback = MemberClientFallback.class)
    public interface MemberClient {
    
        @GetMapping("/api/user/update/{id}")
        int updatePasswdById(@PathVariable(name = "id") Long id, @RequestParam(value = "password") String password);
    
    }
```
```
    @FeignClient(name = "service-product", fallback = ProductClientFallback.class)
    public interface ProductClient {
    
        @GetMapping("/api/product/update/{id}")
        int updatePriceById(@PathVariable(name = "id") Long id, @RequestParam(value = "price") BigDecimal price);
    
    }
```
**解释：**
这里是一个service-order通过FeignClient远程调用service-member和service-product的例子
```    
    @Override
    @LcnTransaction
    @Transactional
    public int doSomeThing(String sn, Long productId, Long memberId) {
        int rs1 = orderMapper.updateAmountBySn(sn, new BigDecimal((int) (Math.random() * 100 + 1)));
        int rs2 = memberClient.updatePasswdById(memberId, String.valueOf((int) (Math.random() * 100 + 1)));
        int rs3 = productClient.updatePriceById(productId, new BigDecimal((int) (Math.random() * 100 + 1)));
        //return rs1 + rs2 + rs3;
        throw new RuntimeException("doSomeThing更新失败");
    }
```
**解释：**
这里使用LCN模式，其他模式也类似，发起端@LcnTransaction，propagation默认是REQUIRED
```
    @Override
    @LcnTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    public int updatePasswdById(Long id, String password) {
        return userMapper.updatePasswdById(id, password);
    }
```
```
    @Override
    @LcnTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    public int updatePriceById(Long id, BigDecimal price) {
        return productMapper.updatePriceById(id, price);
    }

```
**解释：**
这里使用LCN模式，其他模式也类似，参与端@LcnTransaction(propagation = DTXPropagation.SUPPORTS)<br>
propagation有两种传播属性 REQUIRED（当前没有分布式事务，就创建。当前有分布式事务，就加入），SUPPORTS（当前没有分布式事务，非分布式事务运行。当前有分布式事务，就加入）

#### 服务端配置
```
spring.application.name=tx-manager
server.port=7970

spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://192.168.0.146:3306/tx-manager?characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=123456

mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.use-generated-keys=true

#tx-lcn.logger.enabled=true

# TxManager Host Ip
tx-lcn.manager.host=192.168.0.146
# TxClient连接请求端口
tx-lcn.manager.port=8070
# 心跳检测时间(ms)
tx-lcn.manager.heart-time=15000
# 分布式事务执行总时间
tx-lcn.manager.dtx-time=30000
#参数延迟删除时间单位ms
tx-lcn.message.netty.attr-delay-time=10000

#tx-lcn.manager.concurrent-level=128
# 开启日志
#tx-lcn.logger.enabled=true

#logging.level.com.codingapi=debug

#redisIp
spring.redis.host=192.168.0.146
#redis\u7AEF\u53E3
spring.redis.port=6379
#redis\u5BC6\u7801
spring.redis.password=
```
**解释：**
注意服务端需要建立的几个数据库的表，官方代码里有sql脚本，这里不做展示


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

#### Rabbitmq-delayed-message-exchange

https://dl.bintray.com/rabbitmq/community-plugins/3.7.x/rabbitmq_delayed_message_exchange 去这里下载一个版本3.7.x版本的插件，解压到/mnt/docker/rabbitmq

> docker cp /mnt/docker/rabbitmq/rabbitmq_delayed_message_exchange-20171201-3.7.x.ez (容器ID):/plugins

> docker exec -it (容器ID) /bin/bash

> rabbitmq-plugins enable rabbitmq_delayed_message_exchange

#### 下面是我保存的一些镜像

> 709931138/mall:redis-3.2
 
> 709931138/mall:mongo-3.2

> 709931138/mall:elasticsearch-5.6.8

> 709931138/mall:elasticsearch-head-5

> 709931138/mall:rabbitmq-3.7.8


# Service启动后的测试地址

**#erureka-server**

> http://192.168.0.146:1001/

**#admin-server**

> http://192.168.0.146:3001/

**#tx-manager**

> http://192.168.0.146:7970/

**#config-server**

> http://192.168.0.146:2001/service-member-dev.yml

> http://192.168.0.146:2001/service-member-test.yml

> http://192.168.0.146:2001/service-member-prd.yml

> http://192.168.0.146:2001/service-product-dev.yml

> http://192.168.0.146:2001/service-product-test.yml

> http://192.168.0.146:2001/service-product-prd.yml

> http://192.168.0.146:2001/service-order-dev.yml

> http://192.168.0.146:2001/service-order-test.yml

> http://192.168.0.146:2001/service-order-prd.yml

> http://192.168.0.146:2001/actuator/bus-refresh POST

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
