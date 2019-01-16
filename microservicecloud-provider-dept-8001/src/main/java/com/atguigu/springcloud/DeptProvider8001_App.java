package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 将服务注册进入 Eureka 的步骤：
 * 1.在 pom 文件中添加
 * <dependency>
		 <groupId>org.springframework.cloud</groupId>
		 <artifactId>spring-cloud-starter-eureka</artifactId>
		 </dependency>
		 <dependency>
		 <groupId>org.springframework.cloud</groupId>
		 <artifactId>spring-cloud-starter-config</artifactId>
   </dependency>
 2.在 SpringBoot 启动类中添加注解：
	 @EnableEurekaClient //本服务启动后会自动注册进eureka服务中
	 @EnableDiscoveryClient //服务发现
 */
@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@EnableDiscoveryClient //服务发现
public class DeptProvider8001_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(DeptProvider8001_App.class, args);
	}
}
