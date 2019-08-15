package cn.pomit.consulproxy;

import cn.pomit.consul.ConsulProxyApplication;
import cn.pomit.consul.annotation.EnableDiscovery;
import cn.pomit.consul.annotation.EnableServer;
import cn.pomit.consul.annotation.InitConfiguration;
import cn.pomit.consulproxy.config.MailConfiguration;
import cn.pomit.consulproxy.handler.EmailRestHandler;
import cn.pomit.consulproxy.handler.GetTestHandler;
import cn.pomit.consulproxy.handler.PostTestHandler;
import cn.pomit.consulproxy.handler.RibbonRestHandler;

@EnableDiscovery
@EnableServer(handler = { EmailRestHandler.class, RibbonRestHandler.class, GetTestHandler.class,
		PostTestHandler.class })
@InitConfiguration(configurations = { MailConfiguration.class })
public class ConsulApp {
	public static void main(String[] args) {
		ConsulProxyApplication.run(ConsulApp.class, args);

	}

}
