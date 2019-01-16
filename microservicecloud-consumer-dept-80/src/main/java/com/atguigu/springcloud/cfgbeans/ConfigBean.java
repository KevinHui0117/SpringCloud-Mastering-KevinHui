package com.atguigu.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;

@Configuration
public class ConfigBean //boot -->spring   applicationContext.xml --- @Configuration配置   ConfigBean = applicationContext.xml
{ 
	@Bean
	/**
	 * Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。
	 * 之前使用 Nginx 配置的复杂内容，在SpringCloud中只需这个注解就可以实现。
	 * 该注解可以确保获得 Rest 时加入 Ribbon 配置
	 * Ribbon的负载均衡策略会在主启动类中，使用 @RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class) 配置
	 */
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	
//	@Bean
//	public IRule myRule()
//	{
//		//return new RoundRobinRule();
//		//return new RandomRule();//达到的目的，用我们重新选择的随机算法替代默认的轮询。
//		return new RetryRule();
//	}
}

//@Bean
//public UserServcie getUserServcie()
//{
//	return new UserServcieImpl();
//}
// applicationContext.xml == ConfigBean(@Configuration)
//<bean id="userServcie" class="com.atguigu.tmall.UserServiceImpl">