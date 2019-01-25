package com.d2c.product.config.transaction;

import com.codingapi.txlcn.client.config.EnableDistributedTransaction;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@EnableDistributedTransaction
public class TransactionConfig {

}
