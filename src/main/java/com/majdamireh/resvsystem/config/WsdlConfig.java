package com.majdamireh.resvsystem.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadRootSmartSoapEndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

@EnableWs
@Configuration
public class WsdlConfig extends WsConfigurerAdapter {
	
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(new NoContentInterceptor());
	}
	
	//MessageDispatcherServlet
	//url
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	
	@Bean
	public XsdSchema userSchema() {
		return new SimpleXsdSchema(new ClassPathResource("Hotel-Reservation-System.xsd"));
	}
	
	@Bean(name = "user")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema hotelSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://hotelreservation.com/user");
		definition.setSchema(hotelSchema);
		return definition;
	}
}




