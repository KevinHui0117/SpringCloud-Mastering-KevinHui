package com.atguigu.myrule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

public class RandomRule_ZY extends AbstractLoadBalancerRule
{

	// total = 0 // 当total==5以后，我们指针才能往下走，
	// index = 0 // 当前对外提供服务的服务器地址，
	// total需要重新置为零，但是已经达到过一个5次，我们的index = 1
	// 分析：我们5次，但是微服务只有 8001 8002 8003 三台，OK？
	// 由于只有 3台机器（下标为 0,1,2），所以，需要index<3，当index=3,需要将index重置为0
	//实现的效果：0号机调用5次，1号机调用5次，2号机调用5次
	
	
	private int total = 0; 			// 总共被调用的次数，目前要求每台被调用5次
	private int currentIndex = 0;	// 当前提供服务的机器号

	public Server choose(ILoadBalancer lb, Object key)
	{
		if (lb == null) {
			return null;
		}
		Server server = null;

		while (server == null) {
			//停止当前线程
			if (Thread.interrupted()) {
				return null;
			}
			//获取轮询得到的可用服务器的 List
			List<Server> upList = lb.getReachableServers();
			//获取轮询得到的所有服务器的 List
			List<Server> allList = lb.getAllServers();

			int serverCount = allList.size();
			if (serverCount == 0) {
				/*
				 * No servers. End regardless of pass, because subsequent passes only get more
				 * restrictive.
				 */
				return null;
			}

//			int index = rand.nextInt(serverCount);// java.util.Random().nextInt(3);
//			server = upList.get(index);

			
//			private int total = 0; 			// 总共被调用的次数，目前要求每台被调用5次
//			private int currentIndex = 0;	// 当前提供服务的机器号

            if(total < 5)
            {
	            server = upList.get(currentIndex);
	            total++;
            }else {
	            total = 0;
	            currentIndex++;
	            //当服务器调用次数大于可用服务器数量，将当前提供服务的机器号归零
	            if(currentIndex >= upList.size())
	            {
	              currentIndex = 0;
	            }
            }			
			
			
			if (server == null) {
				/*
				 * The only time this should happen is if the server list were somehow trimmed.
				 * This is a transient condition. Retry after yielding.
				 */
				Thread.yield();
				continue;
			}

			if (server.isAlive()) {
				return (server);
			}

			// Shouldn't actually happen. but must be transient or a bug.
			server = null;
			Thread.yield();
		}

		return server;

	}

	@Override
	public Server choose(Object key)
	{
		return choose(getLoadBalancer(), key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig)
	{
		// TODO Auto-generated method stub
	}

}