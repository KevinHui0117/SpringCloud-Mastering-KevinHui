package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 使用 Eureka 的配置步骤：
 * 1.引入 eureka-server服务端：
						 <dependency>
						 <groupId>org.springframework.cloud</groupId>
						 <artifactId>spring-cloud-starter-eureka-server</artifactId>
						 </dependency>
   2.在启动主类上面，添加 @EnableEurekaServer 注解
 */
@SpringBootApplication
@EnableEurekaServer // EurekaServer服务器端启动类,接受其它微服务注册进来
public class EurekaServer7001_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(EurekaServer7001_App.class, args);
	}
}
