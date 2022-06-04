package com.hwadee;

import org.apache.coyote.http11.Http11NioProtocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
//import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;

//@EnablePrometheusEndpoint
@SpringBootApplication(proxyBeanMethods = false)
@MapperScan("com.hwadee.core.repository")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
//    public void customize(ConfigurableServletWebServerFactory factory) {
//
//        TomcatServletWebServerFactory f = (TomcatServletWebServerFactory) factory;
//        f.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
//        f.addConnectorCustomizers(c -> {
//            Http11NioProtocol protocol = (Http11NioProtocol) c.getProtocolHandler();
//            protocol.setMaxConnections(200);
//            protocol.setMaxThreads(200);
//            protocol.setSelectorTimeout(3000);
//            protocol.setSessionTimeout(3000);
//            protocol.setConnectionTimeout(3000);
//        });
//    }
}

