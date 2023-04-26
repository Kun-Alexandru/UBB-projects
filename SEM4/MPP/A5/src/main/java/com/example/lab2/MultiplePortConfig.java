package com.example.lab2;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class MultiplePortConfig {

    @Value("${server.port}")
    private int port;

    @Value("${server.additional-ports}")
    private String additionalPorts;

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setPort(port);

        if (StringUtils.hasText(additionalPorts)) {
            for (String port : additionalPorts.split(",")) {
                Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
                connector.setPort(Integer.valueOf(port.trim()));
                tomcat.addAdditionalTomcatConnectors(connector);
            }
        }

        return tomcat;
    }
}
